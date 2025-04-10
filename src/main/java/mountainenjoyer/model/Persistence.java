/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.model;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Writes users names, high scores, and game progress into a file and retrieves 
 * them when necessary. <p>
 * 
 * For simplicity: <ul>
 *      1. Name cannot be edited after creation. If this is attempted, it will be ignored. <p>
 *      2. Names are only 3 characters long and can only contain letters and numbers <p>
 *      3. Data will not be saved until both a score and a name are present <p>
 *      4. Player score can only increase from previous scores </ul> 
 * 
 * @author colee
 */
public class Persistence implements Serializable
{
    public static int NAME_LENGTH = 3;
    public static String DATA_FILE = "MountainEnjoyerGameData.txt";
    
    private int highScore;
    private String playerName;   
    
    private int savedScore;  // Score saved to the file
    private Boolean haveScore;  // Does the player have a score
    private Boolean haveName;  // Does the player have a name
    
    
    public Persistence()  
    {       // Creates a game data file if it hasn't been created already from a previous object
            // May not be needed, but better to be safe than sorry
        File createGameDataFile = new File(DATA_FILE);
        
        // Initialization of variables
        highScore = 0;
        playerName = "_ _ _";
        haveScore = false;
        haveName = false;
    }
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }
    
    /**
     * Sets the new player score <p>
     * Errors checked for: <ul>
     *  * Player score can only increase from previous score </ul>
     * 
     * @param newScore The new high score that is greater than 0, the default score
     */
    public void setHighScore(int newScore)
    {
        if(newScore > highScore)  // New score is an increase
        {
            highScore = newScore;
            
            haveScore = true;
            newDataToAdd();            
        }
        else if(newScore == highScore && !haveScore)  // Case for when new score is 0
        {
            haveScore = true;
            newDataToAdd();
        }
    }
    
    /**
     * Sets player's name <p>
     * Searches through stored names, and if nameGiven is 
     * included, also replaces score with recorded score <p>
     * 
     * Error Checking: <ul>
     *  * Player name has not been set yet <br>
     *  * Name given is 3 characters </ul>
     * 
     * @param nameGiven 
     */
    public void setPlayerName(String nameGiven)
    {
        if(!haveName && nameGiven.length() == NAME_LENGTH)  // No player associated with current object, and player name is of accepted length
        {
            // Set player name to nameGiven
            playerName = nameGiven;
            haveName = true;

            int foundScore = findObject(nameGiven);
            if(foundScore >= 0)  // Player name has been recorded
            {
                setHighScore(foundScore);  
            }
            else  // Player has not been recorded
            {
                newDataToAdd();
            }
            
        }
    }
    
    /**
     * ( Confusing name, but I couldn't think of what to call it, so I gave 
     * up and used this ) <p>
     * Sees if object has both a score and a name, and if so, handles adding data to file <p>
     * Is called only by the setter methods
     * 
     */
    private void newDataToAdd()
    {
        // Having both a score and a name are required to add data to file
        if(haveScore && haveName)
        {
            // If data has not been saved yet, hence getting the -1
            if(findObject(playerName) == -1)
            {
                writeObject();
            }
            // If data has been saved, but the score has changed from the saved score
            else
            {
                changeScore();
            }
            savedScore = highScore;
        }
    }
    
    /**
     * One of two methods that writes the data to the file <p>
     * This method handles the case of adding new data, so appends the data to the file
     */
    private void writeObject()
    {
        try {
            FileWriter gameDataWriter = new FileWriter(DATA_FILE, true);
            
            gameDataWriter.write(playerName);  // Puts player name to file
            
            gameDataWriter.write("\n"); 
            
            String highScoreString = Integer.toString(highScore);
            gameDataWriter.write(highScoreString);  // Puts player score to file
            
            gameDataWriter.write("\n\n");
            
            gameDataWriter.close();
        }
        catch(IOException e) {}
    }
    
    /**
     * One of two methods that writes the data to the file <p>
     * This method handles the case of changing existing data <p>
     * This is used when the score is changed
     * 
     */
    private void changeScore()
    {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader(DATA_FILE));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            
            while((line = file.readLine()) != null)
            {
                inputBuffer.append(line);
                inputBuffer.append('\n');
                
                if(line.equals(playerName))
                {
                    line = file.readLine();  // To get line after name, which is score
                    String highScoreString = Integer.toString(highScore);
                    line = highScoreString;
                    
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            file.close();
            
            FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }
        catch(Exception e)  {} 
    }
    
    /**
     * Searches for player; used for both finding out if a player 
     * already exists, and the score of the player
     * 
     * @param wantedName
     * @return score of player; -1 if player does not exist yet
     */
    public int findObject(String wantedName)
    {
        int returningScore = -1;  // If no match is found in file
        Boolean found = false;
        
        try
        {
            File gameData = new File(DATA_FILE);
            Scanner gameDataReader = new Scanner(gameData);
            
            // Runs through file, and if character found, returns score
            while(gameDataReader.hasNextLine() && !found)
            {
                String fileData = gameDataReader.nextLine();
                if(wantedName.equals(fileData))
                {
                    String foundScore = gameDataReader.nextLine();
                    returningScore = Integer.parseInt(foundScore); 
                    found = true;
                }
            }
            gameDataReader.close();
        }
        catch(FileNotFoundException e)
        {
            returningScore = -1;
        }
        
        return returningScore;
    }
}

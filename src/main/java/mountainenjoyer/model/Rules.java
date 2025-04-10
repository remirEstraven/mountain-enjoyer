/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.model;

/**
 * Calculates and manages high score and countdown, and handles 
 * win/loss conditions.
 * @author lucas
 */
public class Rules {
    
    private int levelCompleted = 0;
    private static int score = 0;
    
    Persistence persistence = new Persistence();
    
    /**
     * Saves the high score to be read later.
     * @param playerName name the score will be attributed to
     * @return true if the name is three characters, false if not
     */
    public boolean saveHighScore(String playerName) 
    {
        boolean goodName;
        if (playerName.length() == 3)
        {
            persistence.setPlayerName(playerName);
            goodName = true;
        }
        else
        {
            goodName = false;
        }
        return goodName;
    }
    
    /** 
     * Facilitates win/loss conditions of the user at game end.
     * @param timeLeft the time left when the game ended.
     * @return the calculated score after setting it to the high score.
     */
    public int gameEnd(int timeLeft)
    {
        calculateScore(timeLeft);
        persistence.setHighScore(score);
        return score;
    }
    
    /**
     * Calculates and updates the user's score on game end and after every level.
     * @param timeLeft the time left when the game ended.
     */
    private void calculateScore(int timeLeft)
    {
        if(levelCompleted == 1)
        {
            score += 50;
        }
        else if (levelCompleted == 2)
        {
            score += 100;
        }
        else if (levelCompleted == 3)
        {
            score += 150;
        }
        
        if (levelCompleted > 3)
            throw new IllegalArgumentException("Level does not exist.");
        
        score += timeLeft;
    }
    
    /**
     * Updates levelCompleted to the level the player has most recently finished.
     */
    public void updateLevelCompleted()
    {
        levelCompleted++;
    }
    
    public int getScore() {
        return score;
    }
    
    /**
     * Resets the score between games.
     */
    public void resetScore()
    {
        score = 0;
    }
}


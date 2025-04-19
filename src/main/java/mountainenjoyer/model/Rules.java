/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.model;

import java.util.Timer;
import java.util.TimerTask;
import java.io.File;

/**
 * Calculates and manages high score and countdown, and handles 
 * win/loss conditions.
 */
public class Rules {
    
    // Used for calculating score
    private int levelCompleted = 0;
    private static int score = 0;
    
    public Timer timer;
    
    // Used for the timer. Delay and period are 1 second.
    private final int delay = 1000;
    private final int period = 1000;
    private int timeLimit;
    
    // Used to help paint the timer in Game
    private boolean timerEnd = false;
    private boolean timerFlag = false;
    
    Persistence persistence = new Persistence();
    
    /**
     * Starts the countdown until game end.
     */
    public void startCountdown()
    {
        timeLimit = 20;
        timer = new Timer();
        timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timerCountdown();
                        timerFlag = true;
                        if (timeLimit == 0)
                        {
                            timerEnd = true;
                        }
                    }
                }, delay, period);
    }
    
    // Helper method that does the counting down
    private int timerCountdown() 
    {
        if (timeLimit == 1) {
            timer.cancel();
        }
        timerFlag = false;
        return --timeLimit;
    }
    
    /**
     * Stops the timer prematurely and resets the timerEnd.
     */
    public void stopTimer() 
    {
        timer.cancel();
        timerEnd = false;
    }
    
    /**
     * Gets the time from the countdown threads timer.
     * @return the time left on this thread's timer.
     */
    public int getTime()
    {
        return timeLimit;
    }
    
    /**
     * Gets the flag for if the countdown has hit its end or not.
     * @return true if countdown has finished.
     */
    public boolean getTimerEnd()
    {
        return timerEnd;
    }
    
    /**
     * Gets the flag for when the timer ticks.
     * Used to update the timer number on the screen.
     * @return true when timer needs to be updated.
     */
    public boolean getTimerFlag()
    {
        return timerFlag;
    }
    
    /**
     * Saves the high score to be read later.
     * @param playerName name the score will be attributed to
     * @return true if the name is three characters, false if not
     */
    public boolean saveHighScore(String playerName) 
    {
        boolean goodName;
        if (playerName != null && playerName.length() == 3)
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
    
    /**
     * Resets the score between games.
     */
    public void resetScore()
    {
        score = 0;
    }
    
    /**
     * Determines whether the game is being run in the IDE or JAR file and gets correct prefix for paths.
     * @return prefix string for a path to an asset.
     */
    public String getPathPrefix()
    {
        String pathPrefix = "";
        File test = new File("src/main/resources/mountain.png");
        if (test.exists())
        {
            pathPrefix = "src/main/resources/";
        }
        else
        {
            pathPrefix = "classes/";
        }
        
        return pathPrefix;
    }
}


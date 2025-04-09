/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Calculates and manages high score and countdown, and handles 
 * win/loss conditions.
 * @author lucas
 */
public class Rules {
    
    public Timer timer;
    private final int delay = 1000;
    private final int period = 1000;
    private int timeLimit;
    private boolean timerFlag = false;
    
    private static double score;

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
                    }
                }, delay, period);
    }
    
    private int timerCountdown() 
    {
        if (timeLimit == 1) {
            timer.cancel();
            gameEnd(false);
        }
        timerFlag = false;
        return --timeLimit;
    }
   
    /**
     * Returns current time.
     * @return timeLimit: the time left on the timer.
     */
    public int getTime()
    {
        return timeLimit;
    }
    
    public boolean getTimerFlag()
    {
        return timerFlag;
    }
    
    /** 
     * Facilitates win/loss conditions of the user at game end.
     * @param win the success or failure of the user that prompted the game to end.
     */
    public void gameEnd(boolean win)
    {
        
    }
    
    /**
     * Calculates and updates the user's score on game end and after every level.
     * @param timeTaken the time a player took to complete a level.
     * @param level the last level the user completed.
     */
    private void calculateScore(double timeTaken, int level)
    {
        
    }
    
    /**
     * Gets the user's score.
     * @return the user's score.
     */
    public int getScore()
    {
        return (int) score;
    }
}


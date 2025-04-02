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
    
    private double timeLimit;
    private static double score;
    
    /**
     * Starts the countdown until game end.
     */
    public void startCountdown()
    {
        
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.gui;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A small JPanel on the top left corner of the Game JFrame that counts down
 * until the player dies per level.
 */
public class Countdown extends JPanel {
    
    public Timer timer;
    
    // Used for the timer. Delay and period is 1 second.
    private final int delay = 1000;
    private final int period = 1000;
    private int timeLimit;
    private boolean timerEnd = false;
    
    Font font = new Font("countdown", Font.PLAIN, 36);
    
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
                        repaint();
                        if (timeLimit == 0)
                            timerEnd = true;
                    }
                }, delay, period);
    }
    
    private int timerCountdown() 
    {
        if (timeLimit == 1) {
            timer.cancel();
        }
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
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(String.valueOf(timeLimit), 30, 40);
    }
}

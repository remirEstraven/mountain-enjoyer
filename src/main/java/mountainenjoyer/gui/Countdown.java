/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.gui;

import java.awt.Color;
import mountainenjoyer.model.Rules;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Font;

/**
 * A small JPanel on the top left corner of the Game JFrame that counts down
 * until the player dies per level.
 */
public class Countdown extends JPanel {
    
    Rules rules = new Rules();
    
    Font font = new Font("countdown", Font.PLAIN, 36);
    
    private Timer timer;
    private final int delay = 1000;
    private final int period = 1000;
    private int timeLimit;

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
                    }
                }, delay, period);
    }
    
    private int timerCountdown() 
    {
        if (timeLimit == 1) {
            timer.cancel();
            rules.gameEnd(false);
        }
        return --timeLimit;
    }
    
    /**
     * Stops the timer prematurely.
     */
    public void stopTimer() 
    {
        timer.cancel();
    }
    
    /**
     * Returns current time.
     * Used to get time when timer stops prematurely.
     * @return timeLimit: the time left on the timer.
     */
    public int getTime()
    {
        return timeLimit;
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

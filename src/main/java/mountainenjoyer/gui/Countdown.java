/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.gui;

import java.awt.Color;
import mountainenjoyer.model.Rules;
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
    
    public void startCountdown()
    {
        rules.startCountdown();
        if(rules.getTimerFlag())
            repaint();
    }
    
    /**
     * Stops the timer prematurely.
     */
    public void stopTimer() 
    {
        rules.timer.cancel();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(String.valueOf(rules.getTime()), 30, 40);
    }
}

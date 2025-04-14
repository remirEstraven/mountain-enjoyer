/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import mountainenjoyer.model.Player;

/**
 * Handles user input for player control in the game and typing name for high
 * score.
 */
public class KeyboardInputs implements KeyListener {
    
    // Instantiate player
    Player player = new Player();
    
    // Flags for continuous left/right movement.
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    // True if the player is jumping
    private boolean jumping = false;
    
    // Vertical velocity (for jumping / gravity)
    private int velY = 0;
    
    /**
    * When a key is pressed, update the flags for the key accordingly.
    * @param e the event to be processed
    */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        // Jumping: allow a jump if not already in the air.
        if (key == KeyEvent.VK_SPACE && !jumping)
        {
            jumping = true;
            velY = player.JUMP_STRENGTH;
        }
    }

    /**
    * When a key is released, update the flags to stop continuous movement.
    * @param e the event to be processed
    */
    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }

    /**
     * Not used.
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
    
    // Getters and Setters for use in gui classes (mainly Game).
    boolean getLeftPressed() {
        return leftPressed;
    }
    
    boolean getRightPressed() {
        return rightPressed;
    }
    
    void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    void setVelY(int velY) {
        this.velY = velY;
    }
    
    int getVelY() {
        return velY;
    }
}

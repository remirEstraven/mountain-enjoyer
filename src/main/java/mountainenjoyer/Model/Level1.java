/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mountainenjoyer.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level1 extends JPanel implements Map, ActionListener, KeyListener
{
    
    // Timer for the game loop (updates every 15 ms)
    private Timer timer;
    
    // Player properties
    private int playerX = 50;
    private int playerY = 500;
    private final int playerWidth = 30;
    private final int playerHeight = 50;
    private int velY = 0;        // vertical velocity (for jumping / gravity)
    private boolean jumping = false;
    
    // Flags for continuous left/right movement.
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    // Constants for physics and movement.
    private final int GRAVITY = 1;
    private final int HORIZONTAL_SPEED = 5;
    private final int JUMP_STRENGTH = -15;
    
    // Platforms for the level: these values determine their positions and sizes.
    private Rectangle[] platforms = {
        new Rectangle(0, 550, 800, 50),    // Ground platform spanning window width
        new Rectangle(100, 500, 150, 20),
        new Rectangle(350, 400, 200, 20),
        new Rectangle(600, 400, 150, 20)
    };
    
    // Constructor: sets up the timer and key listener
    public Level1()
    {
        timer = new Timer(15, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }
    
    /**
     * Creates and displays a new window hosting this level.
     *
     * @param mapHeight Height of the playable area.
     * @param mapWidth  Width of the playable area.
     */
    @Override
    public void drawMap(int mapHeight, int mapWidth)
    {
        JFrame gameFrame = new JFrame("Level 1");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(mapWidth, mapHeight);
        gameFrame.add(this);
        gameFrame.setVisible(true);
    }
    
    // Paints the player and platforms on the panel.
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Clear the background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw the player as a blue rectangle
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);
        
        // Draw the platforms as red rectangles
        g.setColor(Color.RED);
        for (Rectangle platform : platforms)
        {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }
    
    // Called on each timer tick to update the game state (movement, gravity, collision)
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Update horizontal movement continuously based on key flags.
        if (leftPressed)
        {
            playerX -= HORIZONTAL_SPEED;
        }
        if (rightPressed)
        {
            playerX += HORIZONTAL_SPEED;
        }
        
        // Apply simple gravity and collision detection.
        boolean onPlatform = false;
        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
        for (Rectangle platform : platforms)
        {
            if (playerRect.intersects(platform))
            {
                // Check if the collision comes from above (to ensure proper landing)
                if (playerY + playerHeight - velY <= platform.y)
                {
                    playerY = platform.y - playerHeight;
                    velY = 0;
                    jumping = false;
                    onPlatform = true;
                }
            }
        }
        
        // If the player is not on a platform, apply gravity.
        if (!onPlatform)
        {
            velY += GRAVITY;
        }
        playerY += velY;
        
        repaint();
    }
    
    // When a key is pressed, update the flags accordingly.
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
            velY = JUMP_STRENGTH;
        }
    }
    
    // When a key is released, update the flags to stop continuous movement.
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
    
    @Override
    public void keyTyped(KeyEvent e) { }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mountainenjoyer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class Game extends JPanel implements ActionListener
{
    
    // Timer for the game loop (updates every 15 ms)
    private Timer timer;
    
    // Player properties
    private int playerX = 50;
    private int playerY = 500;
    private final int playerWidth = 30;
    private final int playerHeight = 50;
    
    // Constants for physics and movement.
    private final int GRAVITY = 1;
    private final int HORIZONTAL_SPEED = 5;
    
    // Keyboard Inputs
    KeyboardInputs keyInputs = new KeyboardInputs();
    
    
    // Platforms for the level: these values determine their positions and sizes.
    private Rectangle[] platformsLvl1 = {
        new Rectangle(0, 550, 800, 50),    // Ground platform spanning window width
        new Rectangle(100, 500, 150, 20),
        new Rectangle(350, 400, 200, 20),
        new Rectangle(600, 400, 150, 20)
    };
    
    // Platforms for the second level
    private Rectangle[] platformsLvl2 = {
        new Rectangle(0, 550, 800, 50),    // Ground platform spanning window width
        new Rectangle(100, 500, 150, 20),
        new Rectangle(350, 400, 200, 20),
        new Rectangle(600, 400, 150, 20)
    };
    
    // Constructor: sets up the timer and key listener
    public Game()
    {
        timer = new Timer(15, this);
        timer.start();
        setFocusable(true);
        addKeyListener(keyInputs);
    }
    
    /**
     * Creates and displays a new window hosting this level.
     *
     * @param mapHeight Height of the playable area.
     * @param mapWidth  Width of the playable area.
     */
    public void drawMap(int mapHeight, int mapWidth)
    {
        JFrame gameFrame = new JFrame("Game");
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
        
        // TODO: Make it so what rectangle array is used here depends on 
        //       which level the player is on
        // Draw the platforms as red rectangles
        g.setColor(Color.RED);
        for (Rectangle platform : platformsLvl1)
        {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }
    
    // Called on each timer tick to update the game state (movement, gravity, collision)
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Update horizontal movement continuously based on key flags.
        if (keyInputs.getLeftPressed())
        {
            playerX -= HORIZONTAL_SPEED;
        }
        if (keyInputs.getRightPressed())
        {
            playerX += HORIZONTAL_SPEED;
        }
        
        // Apply simple gravity and collision detection.
        boolean onPlatform = false;
        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
        for (Rectangle platform : platformsLvl1)
        {
            if (playerRect.intersects(platform))
            {
                // Check if the collision comes from above (to ensure proper landing)
                if (playerY + playerHeight - keyInputs.getVelY() <= platform.y)
                {
                    playerY = platform.y - playerHeight;
                    keyInputs.setVelY(0);
                    keyInputs.setJumping(false);
                    onPlatform = true;
                }
                // TODO: Make it so player can't overlap with platforms
            }
        }
        
        // TODO: Make it so player can't leave side of screen
        
        // If the player is not on a platform, apply gravity.
        if (!onPlatform)
        {
            keyInputs.setVelY(keyInputs.getVelY() + GRAVITY);
        }
        playerY += keyInputs.getVelY();
        
        repaint();
    }
}

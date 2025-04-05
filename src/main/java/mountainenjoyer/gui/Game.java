/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mountainenjoyer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import mountainenjoyer.model.Player;

public class Game extends JPanel implements ActionListener
{
    
    // Timer for the game loop (updates every 15 ms)
    private Timer timer;
    
    // Player
    Player player = new Player();
    
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
        g.fillRect(player.getPlayerX(), player.getPlayerY(), 
                   player.getPlayerWidth(), player.getPlayerHeight());
        
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
            player.moveLeft();
        }
        if (keyInputs.getRightPressed())
        {
            player.moveRight();
        }
        
        // Apply simple gravity and collision detection.
        boolean onPlatform = false;
        Rectangle playerRect = new Rectangle(player.getPlayerX(), 
                                             player.getPlayerY(), 
                                             player.getPlayerWidth(), 
                                             player.getPlayerHeight());
        for (Rectangle platform : platformsLvl1)
        {
            if (playerRect.intersects(platform))
            {
                // Check if the collision comes from above (to ensure proper landing)
                if (player.getPlayerY() + player.getPlayerHeight() - 
                    keyInputs.getVelY() <= platform.y)
                {
                    player.setPlayerY(platform.y - player.getPlayerHeight());
                    keyInputs.setVelY(0);
                    keyInputs.setJumping(false);
                    onPlatform = true;
                }
                // TODO: Make it so player can't overlap with platforms
            }
        }
        
        // Make sure player cannot go off the sides of the screen
        if (player.getPlayerX() < 0)
        {
            player.setPlayerX(0);
        }
        if (player.getPlayerX() + player.getPlayerWidth() > 800)
        {
            player.setPlayerX(800 - player.getPlayerWidth());
        }
        
        // If the player is not on a platform, apply gravity.
        if (!onPlatform)
        {
            keyInputs.setVelY(keyInputs.getVelY() + player.GRAVITY);
        }
        player.setPlayerY(player.getPlayerY() + keyInputs.getVelY());
        
        repaint();
    }
}

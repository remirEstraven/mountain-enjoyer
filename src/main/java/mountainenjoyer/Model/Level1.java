/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mountainenjoyer.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Level1 extends JPanel implements Map, ActionListener, KeyListener {

    // Timer for the game loop (updates every 15 ms)
    private Timer timer;
    
    // The player instance from your Player class.
    private Player player;
    
    // Flags for continuous left/right movement.
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    // Platforms for the level: these values determine their positions and sizes.
    private Rectangle[] platforms = {
        new Rectangle(0, 550, 800, 50),    // Ground platform spanning window width.
        new Rectangle(100, 500, 150, 20),
        new Rectangle(350, 400, 200, 20),
        new Rectangle(600, 400, 150, 20)
    };
    
    // Constructor: set up the timer, instantiate the player, and add the key listener.
    public Level1() {
        player = new Player();  // The starting position is defined in Player.reset()
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
    public void drawMap(int mapHeight, int mapWidth) {
        JFrame gameFrame = new JFrame("Level 1");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(mapWidth, mapHeight);
        gameFrame.add(this);
        gameFrame.setVisible(true);
    }
    
    /**
     * Paints the player and platforms on the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Clear the background.
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // Draw the player using the loaded sprite; fall back to a blue rectangle if needed.
        if (player.getSprite() != null) {
            g.drawImage(
                player.getSprite(),
                (int) player.getX(),
                (int) player.getY(),
                player.getWidth(),
                player.getHeight(),
                null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect((int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
        }
        
        // Draw the platforms as red rectangles.
        g.setColor(Color.BLUE);
        for (Rectangle platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }
    
    /**
     * Called on each timer tick to update the game state: movement, gravity, and collisions.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update horizontal movement based on key flags.
        if (leftPressed) {
            player.moveLeft();
        } else if (rightPressed) {
            player.moveRight();
        } else {
            player.stopMoving();
        }
        
        // Store player's bottom position before updating.
        double previousBottom = player.getY() + player.getHeight();
        
        // Update the player's state (gravity and movement).
        player.update();
        
        // Check collisions with the platforms.
        boolean onPlatform = false;
        for (Rectangle platform : platforms) {
            // Check that player's horizontal range overlaps the platform's horizontal range.
            if (player.getX() + player.getWidth() > platform.x &&
                player.getX() < platform.x + platform.width) {
                // Check if the player's bottom has just crossed the platform's top.
                if (previousBottom <= platform.y && (player.getY() + player.getHeight()) >= platform.y) {
                    // "Land" the player on the platform.
                    player.setY(platform.y - player.getHeight());
                    player.setVelocityY(0);
                    player.setOnGround(true);
                    onPlatform = true;
                }
            }
        }
        if (!onPlatform) {
            player.setOnGround(false);
        }
        
        // Redraw the updated game state.
        repaint();
    }
    
    /**
     * Handles key pressed events: updating movement flags and triggering a jump.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            if (player.isOnGround()) {
                player.jump();
            }
        }
    }
    
    /**
     * Handles key released events to stop continuous movement.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
}

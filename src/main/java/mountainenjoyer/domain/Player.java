/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.domain;

/**
 * Represents the player character in the game.
 * Handles player movement, interactions, and state management.
 * The player moves using arrow keys or WASD and jumps using the spacebar.
 * The goal is to navigate through platforms and reach the top of the level.
 * 
 * Part of the Mountain Enjoyer game.
 * 
 * @author Team 1
 */
public class Player {
    
    // Player's position on the screen.
    private double x, y;
    // Current velocities along x and y.
    private double velocityX, velocityY;
    // True if the player is currently standing on a platform.
    private boolean onGround;
    
    // Constants for physics and movement.
    private static final double GRAVITY = 0.5;
    private static final double MOVE_SPEED = 5;
    private static final double JUMP_STRENGTH = -15;

    // Constructor: initialize the player.
    public Player()
    {
        reset();
    }
    
    /**
     * Updates the player's position based on its current velocity.
     */
    public void move()
    {
        x += velocityX;
        y += velocityY;
    }
    
    /**
     * Makes the player jump if they are currently on a platform.
     */
    public void jump()
    {
        if (onGround)
        {
            velocityY = JUMP_STRENGTH;  // Negative for upward movement.
            onGround = false;
        }
    }
    
    /**
     * Updates the player's state on each game tick.
     * Applies gravity if the player is in the air and updates the position.
     */
    public void update()
    {
        if (!onGround)
        {
            velocityY += GRAVITY;
        }
        move();
    }
    
    /**
     * Checks if the player has reached the top of the level.
     *
     * 
     * @return true if the player's y position is above a threshold.
     */
    public boolean hasReachedTop()
    {
        return y < 50;
    }
    
    /**
     * Resets the player's position and state to default values at the start of a level.
     */
    public void reset()
    {
        x = 50;
        y = 500;
        velocityX = 0;
        velocityY = 0;
        onGround = false;
    }
    
    // --- Helper methods for horizontal movement ---
    
    /**
     * Moves the player to the left by setting the horizontal velocity.
     */
    public void moveLeft()
    {
        velocityX = -MOVE_SPEED;
    }
    
    /**
     * Moves the player to the right by setting the horizontal velocity.
     */
    public void moveRight()
    {
        velocityX = MOVE_SPEED;
    }
    
    /**
     * Stops horizontal movement by zeroing out the horizontal velocity.
     */
    public void stopMoving()
    {
        velocityX = 0;
    }
    
    // --- Getters and setters for integration with level logic ---
    
    /**
     * Sets whether the player is on a platform.
     * Typically, the collision detection in your level code will call this. 
     * @param onGround true if the player is on a platform.
     */
    public void setOnGround(boolean onGround)
    {
        this.onGround = onGround;
        if (onGround)
        {
            velocityY = 0;
        }
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
}

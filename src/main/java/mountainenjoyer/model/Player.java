/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.model;

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
    private int playerX = 50;
    private int playerY = 500;
    
    // Player properties
    private final int playerWidth = 30;
    private final int playerHeight = 50;
     
    // Constants for physics and movement.
    public final int GRAVITY = 1;
    public final int MOVE_SPEED = 5;
    public final int JUMP_STRENGTH = -15;
    
    /**
     * Checks if the player has reached the top of the level.
     *
     * @return true if the player's y position is above a threshold.
     */
    public boolean hasReachedTop()
    {
        return playerY < 50;
    }
    
    // Helper methods for horizontal movement 
    
    /**
     * Moves the player to the left by setting the player's x value.
     */
    public void moveLeft()
    {
        playerX -= MOVE_SPEED;
    }
    
    /**
     * Moves the player to the right by setting the player's x value.
     */
    public void moveRight()
    {
        playerX += MOVE_SPEED;
    }
    
    // Getters and setters for integration with game logic
    
    public int getPlayerX()
    {
        return playerX;
    }
    
    public int getPlayerY()
    {
        return playerY;
    }
    
    /**
     * Sets the player's Y value to a value within the bounds of the window.
     * @param playerY The players vertical position on the screen.
     */
    public void setPlayerY(int playerY) {
    if (playerY > 600) {
        throw new IllegalArgumentException("PlayerY out of bounds");
    } else {
        this.playerY = playerY;
    }
}

    
    /**
     * Sets the player's X value to a value within the bounds of the window.
     * @param playerX The players horizontal position on the screen.
     */
    public void setPlayerX(int playerX)
    {
        if(playerX >= 0 && playerX <= 800)
        {
            this.playerX = playerX;
        }
        else
        {
            throw new IllegalArgumentException("PlayerX out of bounds");
        }
    }
    
    public int getPlayerWidth()
    {
        return playerWidth;
    }
    
    public int getPlayerHeight()
    {
        return playerHeight;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.Model;


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
    
    private double x, y; // player's position on the screen
    private double velocityX, velocityY; // movement speed and direction
    private boolean onGround; // indicates if the player is on a platform
    
    /**
     * moves the player based on user input.
     * Updates the player's position according to velocity and game physics.
     */
    public void move() {
        
    }
    
    /**
     * makes the player jump if they are on a platform.
     */
    public void jump() {
        
    }
    
    /**
     * updates the player's state each game tick.
     * handles gravity, collision detection, and movement restrictions.
     */
    public void update() {
        
    }
    
    /**
     * Checks if the player has reached the top of the level.
     * 
     * @return true if the player has reached the goal, false otherwise.
     */
    public boolean hasReachedTop() {
        return true; // can be either true or false
    }
    
    /**
     * resets the player's position and state at the start of a level.
     */
    public void reset() {
        
    }
}


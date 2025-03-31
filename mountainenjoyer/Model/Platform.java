/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.Model;

/**
 * This class handles the platforms the player jumps on
 * Determines which kind of platform it is
 */
public class Platform {
    
    private double platPosX, platPosY;
    private double platWidth, platHeight;
    private PlatformType platType;
    private PlatformState platState;
    
    /**
     * Types of platforms available.
     * NORMAL is a regular, immovable platform.
     * BREAKABLE is a platform that will break after the player jumps on it.
     */
    public enum PlatformType {
        NORMAL,
        BREAKABLE
    }
    
    /**
     * States of platforms available.
     * SOLID is a platform that can be jumped on and does not change state.
     * BROKEN is a transparent platform that can be jumped and fallen through.
     */
    public enum PlatformState {
        SOLID,
        BROKEN
    }
    
    /**
     * Constructor for platform.
     * @param platType Type of the platform
     * @param platState State of the platform
     * @param platPosX X position of the platform
     * @param platPosY Y position of the platform
     * @param platWidth Width of the platform
     * @param platHeight Height of the platform
     */
    public Platform(PlatformType platType, PlatformState platState, 
            double platPosX, double platPosY, double platWidth, 
            double platHeight)
    { 
        this.platPosX = platPosX;
        this.platPosY = platPosY;
        this.platWidth = platWidth;
        this.platHeight = platHeight;
        this.platType = platType;
        this.platState = platState;
    }
    
    /**
     * Draws a platform with the given specifications.
     */
    public void drawPlatform()
    {
    }
}

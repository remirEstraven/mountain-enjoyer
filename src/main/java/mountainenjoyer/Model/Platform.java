/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.Model;

/**
 * This class handles the platforms the player jumps on, including the type,
 * state, size, and where it is located on the level.
 * Create an instance of this class in a level class to generate a platform in
 * the level.
 */
public class Platform {
    
    private int platPosX, platPosY;
    private int platWidth, platHeight;
    private PlatformType platType;
    private PlatformState platState;
    
    /**
     * Types of platforms available.
     * NORMAL is a regular, immovable platform that the player can jump on.
     * BREAKABLE is a platform that will break after the player jumps on it.
     */
    public enum PlatformType {
        NORMAL,
        BREAKABLE
    }
    
    /**
     * States of platforms available.
     * SOLID is a platform that can be jumped on.
     * BROKEN is a transparent platform that the player will fall through and
     * cannot jump on.
     */
    public enum PlatformState {
        SOLID,
        BROKEN
    }
    
    /**
     * Constructor for platform.
     * @param platType Type of the platform
     * @param platPosX X position of the platform
     * @param platPosY Y position of the platform
     * @param platWidth Width of the platform
     * @param platHeight Height of the platform
     */
    public Platform(PlatformType platType, int platPosX, int platPosY, 
            int platWidth, int platHeight)
    { 
        this.platPosX = platPosX;
        this.platPosY = platPosY;
        this.platWidth = platWidth;
        this.platHeight = platHeight;
        this.platType = platType;
    }
    
    /**
     * Returns the platform's X position.
     * @return platPoxX
     */
    public int getPlatPosX()
    {
        return platPosX;
    }
    
    /**
     * Returns the platform's Y position.
     * @return platPoxY
     */
    public int getPlatPosY()
    {
        return platPosY;
    }
    
    /**
     * Returns the platform's width.
     * @return platWidth
     */
    public int getPlatWidth()
    {
        return platWidth;
    }
    
    /**
     * Returns the platform's height.
     * @return platHeight
     */
    public int getPlatHeight()
    {
        return platHeight;
    }
    
    /**
     * Returns the platform's type.
     * @return platType
     */
    public PlatformType getPlatType()
    {
        return platType;
    }
    
    /**
     * Returns the platform's state.
     * @return platState
     */
    public PlatformState getPlatState()
    {
        return platState;
    }
    
    /**
     * Changes the platforms state to broken for 3 seconds if breakable.
     */
    public void breakPlatform()
    {
        if(platType == PlatformType.BREAKABLE)
        {
            platState = PlatformState.BROKEN;
            try {
                Thread.sleep(3000);
                platState = PlatformState.SOLID;
            } catch (InterruptedException e) {
                platState = PlatformState.SOLID;
                System.out.println("Interrupted");
            }
        }
    }
}

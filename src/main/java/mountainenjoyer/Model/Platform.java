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
    
    public double platPosX, platPosY;
    public double platWidth, platHeight;
    
    public enum PlatformType {
        NORMAL,
        BREAKABLE
    }
    public enum PlatformState {
        SOLID,
        BROKEN
    }
    
    /**
     * Draws a platform with the given specifications.
     * @param type the type of platform it is.
     * @param state the state the platform is in.
     * @param platPosX the X position of the platform.
     * @param platPosY the Y position of the platform.
     * @param platWidth the width of the platform.
     * @param platHeight the height of the platform.
     */
    public void drawPlatform(PlatformType type, PlatformState state, 
            double platPosX, double platPosY, double platWidth, 
            double platHeight)
    {
        
    }
}

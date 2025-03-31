/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.Model;

/**
 * This interface is the base that level classes will implement to make up
 * the playable area.
 * @author lucas
 */
public interface Map {
    
    /**
     * Draws the playable area.
     * @param mapHeight Height of the playable area.
     * @param mapWidth Width of the playable area.
     */
    public void drawMap(int mapHeight, int mapWidth);
}

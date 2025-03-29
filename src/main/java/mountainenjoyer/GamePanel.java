/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;

public class GamePanel {
    
    public static void displayGamePanel() {
        //Makes GUI
        JFrame frame = new JFrame("Game Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,650); //window size, not sure if it should differ from menu size
        
        JPanel panel = new JPanel(); // this is a panel to hold all of the things
        panel.setLayout(new BorderLayout()); // make a layout
        
        // add the panel to the frame
        frame.add(panel);

        // make it visible
        frame.setVisible(true);
    }
    
   public void displayPlayer() {
    
    //Player is represented by a blue circle as a placeholder
    public void paintComponent(Graphics player) {
        super.paintComponent(player); // Call parent class method
        player.setColor(Color.BLUE); // Make blue
        player.fillOval(50, 50, 50, 50); // Draw a filled circle (x = 50, y = 50, width = 50, height = 50)
        //Not sure how, but display player to the game panel
        }
    }    
   
   public void drawPlatform() {
    //Draws platforms to game panel
   }
   
   
   
   public void levelScroll() {
    //Scrolls level as player progresses up past a certain threshold
    //until the end is displayed
   }
   
   if (Player.y < -100) {
     //end game with a loss
    }
   
   if (//Player.x == flag x coordinate && Player.y == flag y coordinate) {
     //end game with a win
    }
   
}


package mountainenjoyer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import mountainenjoyer.model.Persistence;
import mountainenjoyer.model.Rules;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Menu class to construct and show the starting menu of the game.
 * Closes the menu and opens the game once play button is pressed.
 * Also shows previous users high scores.
 */
public class Menu {

    public static void showMenu()
    {
        // Create the main menu GUI
        final JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 650);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        // Place the components into the panel
        placeComponents(panel, frame);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel, final JFrame frame)
    {
        Rules rules = new Rules();
        
        // Header label
        JLabel welcomeLabel = new JLabel("Welcome to Mountain Enjoyer!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Center image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        String pathPrefix = rules.getPathPrefix();
        File imagePath = new File(pathPrefix + "mountain.png");
        ImageIcon imageIcon = new ImageIcon(imagePath.getPath());
        System.out.println(imagePath.getAbsolutePath());
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        panel.add(imagePanel, BorderLayout.CENTER);
        
        // Retrieves data from data file
        Persistence persistence = new Persistence();
        ArrayList<String[]> scoreInfo = persistence.readScoreInfoSorted();

        String[] column = {"Player", "Score"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (String[] row : scoreInfo) 
        {
            model.addRow(row);
        }

        // Makes the high score table
        JTable highScoreTable = new JTable(model);
        highScoreTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(highScoreTable);
        JPanel highScorePanel = new JPanel(new BorderLayout());
        highScorePanel.add(scrollPane, BorderLayout.CENTER);
        highScorePanel.setVisible(false);

        // Places the table east. I couldn't figure out how to have it display
        // on top of the background image  unfortunately :(
        panel.add(highScorePanel, BorderLayout.EAST);

        // Bottom button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Play Button: when clicked, close the menu and launch level 1
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();  // Close the menu.
                Game level = new Game();
                // You can adjust the size of the playable area (width, height) as desired.
                level.drawMap(800, 615);
            }
        });
        buttonPanel.add(playButton);

        // Quit Button: exit the application
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);
        
        // Makes button for high score display
        JButton toggleScoreButton = new JButton("High Score");
        toggleScoreButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                highScorePanel.setVisible(!highScorePanel.isVisible());
            }
        });
        buttonPanel.add(toggleScoreButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }
}


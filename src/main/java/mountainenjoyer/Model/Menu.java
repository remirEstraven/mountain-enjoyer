
package mountainenjoyer.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        // Header label
        JLabel welcomeLabel = new JLabel("Welcome to Mountain Enjoyer!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Center image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        String imagePath = "images/mountain.png";  // Adjust the path as necessary.
        ImageIcon imageIcon = new ImageIcon(Menu.class.getResource("/" + imagePath));
        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        panel.add(imagePanel, BorderLayout.CENTER);

        // Bottom button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Play Button: when clicked, close the menu and launch level 1
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();  // Close the menu.
                Level1 level = new Level1();
                // You can adjust the size of the playable area (height, width) as desired.
                level.drawMap(600, 800);
            }
        });
        buttonPanel.add(playButton);

        // Quit Button: exit the application
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }
}


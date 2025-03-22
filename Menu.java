package mountainenjoyer.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    public static void showMenu() {
        // make the GUI
        JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 650); // big window

        JPanel panel = new JPanel(); // this is a panel to hold all of the things
        panel.setLayout(new BorderLayout()); // make a layout

        // add the panel to the frame
        frame.add(panel);

        // place the components into the panel
        placeComponents(panel);

        // make it visible
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        JLabel welcomeLabel = new JLabel("Welcome to Mountain Enjoyer!", JLabel.CENTER); // center the welcome label to the top
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24)); // make it bigger and bold

        panel.add(welcomeLabel, BorderLayout.NORTH); // set north so it's at the top

        // make a panel to hold the image in the center
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout()); // make a layout

        String imagePath = "images/mountain.png";  // get path
        ImageIcon imageIcon = new ImageIcon(Menu.class.getResource("/" + imagePath));
        JLabel imageLabel = new JLabel(imageIcon); // make a label for the image
        imagePanel.add(imageLabel, BorderLayout.CENTER); // add the image to the center of the panel

        // add the image panel to the center of the main panel
        panel.add(imagePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(); // make another panel to hold the buttons
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // center the buttons at the bottom and make them horizontal

        JButton playButton = new JButton("Play"); // make the play button
        playButton.addActionListener(new ActionListener() // make it do something when it is clicked
        {
            public void actionPerformed(ActionEvent e) // on action
            {
                // Map.showMap(); // call the showMap function from the map class
            }
        });
        buttonPanel.add(playButton); // add the play button to the panel

        // do the same stuff to the quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // exit
            }
        });
        buttonPanel.add(quitButton); // add the quit button to the panel

        panel.add(buttonPanel, BorderLayout.SOUTH); // add this panel to the bottom of the GUI
    }
}

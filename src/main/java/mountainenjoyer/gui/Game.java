package mountainenjoyer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import mountainenjoyer.model.Player;
import mountainenjoyer.model.Rules;

/**
 * Game Panel that assembles the map the player plays on.
 */
public class Game extends JPanel implements ActionListener 
{
    // Our game loop timer - it ticks every 15 ms
    private final Timer timer;

    // The player (our hero) in the game
    Player player = new Player();

    // Used to capture keyboard input for movement
    KeyboardInputs keyInputs = new KeyboardInputs();
    
    // Used to update level
    Rules rules = new Rules();
    
    // Used to set player name
    String playerName;
    
    // Flag for game over
    boolean gameOver = false;
    
    // Font for the countdown timer
    Font countdownFont = new Font("countdown", Font.PLAIN, 35);

    // Level 1 platforms - arranged to create a fun, challenging climb.
    private Rectangle[] platformsLvl1 = 
    {
        new Rectangle(0, 550, 150, 50),     // This is the ground spanning the full width.
        new Rectangle(80, 500, 120, 20),      // Platform 1 
        new Rectangle(250, 450, 150, 20),     // Platform 2
        new Rectangle(450, 400, 120, 20),     // Platform 3
        new Rectangle(300, 350, 150, 20),     // Platform 4
        new Rectangle(550, 300, 120, 20),     // Platform 5
        new Rectangle(200, 250, 150, 20)      // The highest platform in level 1
    };

    // Level 2 platforms - a different layout for variety.
    private Rectangle[] platformsLvl2 = 
    {
        new Rectangle(0, 550, 150, 50),     // Ground platform stays the same.
        new Rectangle(150, 480, 100, 20),    // Platform 1
        new Rectangle(500, 430, 120, 20),    // Platform 2
        new Rectangle(250, 380, 140, 20),    // Platform 3
        new Rectangle(600, 330, 100, 20),    // Platform 4
        new Rectangle(100, 280, 120, 20),    // Platform 5
        new Rectangle(350, 230, 130, 20),    // Platform 6
        new Rectangle(550, 180, 100, 20),    // Platform 7
        new Rectangle(200, 130, 140, 20),    // Platform 8
        new Rectangle(400, 80, 120, 20)      // Top platform in level 2 (we place a checkpoint here)
    };

    // Level 3 platforms - another new layout as you approach the end.
    private Rectangle[] platformsLvl3 = 
    {
        new Rectangle(0, 550, 150, 50),     // Ground platform remains unchanged.
        new Rectangle(200, 500, 130, 20),
        new Rectangle(400, 450, 120, 20),
        new Rectangle(100, 400, 100, 20),
        new Rectangle(500, 350, 100, 20),
        new Rectangle(300, 300, 150, 20),
        new Rectangle(150, 250, 120, 20),
        new Rectangle(450, 200, 120, 20),
        new Rectangle(250, 150, 130, 20),
        new Rectangle(550, 100, 100, 20)     // This is the highest platform in level 3 (game end object goes here)
    };

    // Tracks the current level (starting at level 1)
    private int currentLevel = 1;
    // Holds the set of active platforms for the current level
    private Rectangle[] currentPlatforms;

    // Level 1 checkpoint (placed on the highest platform)
    private Rectangle checkpoint;
    private boolean checkpointHit = false;

    // Level 2 checkpoint (placed on the top platform of level 2)
    private Rectangle checkpoint2;
    private boolean checkpoint2Hit = false;

    // The game-end object for level 3 (on its highest platform)
    private Rectangle gameEnd;
    private boolean gameEndHit = false;
    
    private Image playerImage; // holds the player character image


    // I want images not blocks
    private Image checkpointImage;  // Image for checkpoints
    private Image gameEndImage;    // Image for game end object
    
    private Image backgroundImage;
    
    private Image platformImage;



    // Constructor: sets up our timer, listener, and initial interactive items for level 1.
    public Game() 
    {
        timer = new Timer(15, this);
        timer.start();
        setFocusable(true);
        addKeyListener(keyInputs);

        // Start out with the Level 1 platforms.
        currentPlatforms = platformsLvl1;

        // For Level 1, we place a checkpoint on the highest platform in the array.
        int checkpointWidth = 20;
        int checkpointHeight = 20;
        Rectangle highestPlatformLvl1 = platformsLvl1[platformsLvl1.length - 1];
        int checkpointX = highestPlatformLvl1.x + (highestPlatformLvl1.width - checkpointWidth) / 2;
        int checkpointY = highestPlatformLvl1.y - checkpointHeight - 10;
        checkpoint = new Rectangle(checkpointX, checkpointY, checkpointWidth, checkpointHeight);

        // For Level 2, we pre-calculate a checkpoint using the top platform.
        int checkpoint2Width = 20;
        int checkpoint2Height = 20;
        Rectangle highestPlatformLvl2 = platformsLvl2[platformsLvl2.length - 1];
        int checkpoint2X = highestPlatformLvl2.x + (highestPlatformLvl2.width - checkpoint2Width) / 2;
        int checkpoint2Y = highestPlatformLvl2.y - checkpoint2Height - 10;
        checkpoint2 = new Rectangle(checkpoint2X, checkpoint2Y, checkpoint2Width, checkpoint2Height);

        // For Level 3, figure out where to place the game-end object.
        int gameEndWidth = 30;
        int gameEndHeight = 20;
        Rectangle highestPlatformLvl3 = platformsLvl3[platformsLvl3.length - 1];
        int gameEndX = highestPlatformLvl3.x + (highestPlatformLvl3.width - gameEndWidth) / 2;
        int gameEndY = highestPlatformLvl3.y - gameEndHeight - 24;
        gameEnd = new Rectangle(gameEndX, gameEndY, gameEndWidth, gameEndHeight);
        
        // Starts the countdown until end of game
        rules.startCountdown();
        if (rules.getTimerFlag())
        {
            repaint();
        }
        
                // Load player sprite
        File imagePath = new File("src/main/resources/enjoyer.png");
        ImageIcon imageIcon = new ImageIcon(imagePath.getPath());
        playerImage = imageIcon.getImage().getScaledInstance(30, 50, Image.SCALE_SMOOTH);
        
        
        // added this to have checkpoints be images
        File checkpointPath = new File("src/main/resources/bluecheckpoint.png");
        ImageIcon checkpointIcon = new ImageIcon(checkpointPath.getPath());
        // Scale images to match original checkpoint dimensions
        File gameEndPath = new File("src/main/resources/gameEnd.png");
        ImageIcon gameEndIcon = new ImageIcon(gameEndPath.getPath());
        
        checkpointImage = checkpointIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        gameEndImage = gameEndIcon.getImage().getScaledInstance(30, 40, Image.SCALE_SMOOTH);
        
        // Adding background image
        File backgroundPath = new File("src/main/resources/background.png");
        ImageIcon backgroundIcon = new ImageIcon(backgroundPath.getPath());
        backgroundImage = backgroundIcon.getImage();
        
        // Adding platform 
        File platformPath = new File("src/main/resources/lessTextured.png");
        ImageIcon platformIcon = new ImageIcon(platformPath.getPath());
        platformImage = platformIcon.getImage();
    }

    /**
     * Displays the game window and countdown timer.
     * 
     * @param mapWidth  The width of the playing area.
     * @param mapHeight The height of the playing area.
     */
    public void drawMap(int mapWidth, int mapHeight) 
    {
        JFrame gameFrame = new JFrame("Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(mapWidth, mapHeight);
        gameFrame.add(this);
        this.setBounds(0, 0, mapWidth, mapHeight);
        this.setOpaque(true);
        gameFrame.setVisible(true);
    }

    // This method draws everything on the screen.
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Paint a white background to clear the previous frame.
//        g.setColor(Color.GRAY);
//        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.drawImage(playerImage, player.getPlayerX(), player.getPlayerY(), null);

        // Draw the player (our blue hero).
       // g.setColor(Color.BLUE);
       // g.fillRect(player.getPlayerX(), player.getPlayerY(),
        //           player.getPlayerWidth(), player.getPlayerHeight());

        // Draw all of the current platforms in red.
        g.setColor(Color.BLACK);
        for (Rectangle platform : currentPlatforms) 
        {
            //g.fillRect(platform.x, platform.y, platform.width, platform.height);
            g.drawImage(platformImage, platform.x, platform.y -10, platform.width, platform.height + 10, null);

        }

        // Draw the interactive object for the current level.
        if (currentLevel == 1) 
        {
            g.drawImage(checkpointImage, checkpoint.x, checkpoint.y, null);
        } 
        else if (currentLevel == 2) 
        {
            g.drawImage(checkpointImage, checkpoint2.x, checkpoint2.y, null);
        } 
        else if (currentLevel == 3) 
        {
            g.drawImage(gameEndImage, gameEnd.x, gameEnd.y, null);
        }
        
        // Paint the countdown timer on the top left of the screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 100, 50);
        
        g.setColor(Color.WHITE);
        g.setFont(countdownFont);
        g.drawString(String.valueOf(rules.getTime()), 30, 40);
    }

    // This method updates the game state. It is called repeatedly by the timer.
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // End the game if timer runs out or the player falls off the map
        if(rules.getTimerEnd() || player.getPlayerY() > 600)
        {
            if (!gameOver)
            {
                rules.stopTimer();
                repaint(); // repaint one last time to show the 0 on the countdown
                playerName = JOptionPane.showInputDialog(this, "You Lose! Your score is " + rules.gameEnd(rules.getTime()) + 
                                                             ". Enter a 3 character name.", "Game End", 
                                                             JOptionPane.INFORMATION_MESSAGE);
                
                // Make sure the name exists and is 3 chars long
                boolean goodName = rules.saveHighScore(playerName); 
                while (!goodName)
                {
                    playerName = JOptionPane.showInputDialog(this, "Make sure your name is 3 characters!", "Game End", 
                                                             JOptionPane.INFORMATION_MESSAGE);
                    goodName = rules.saveHighScore(playerName);
                }
                rules.resetScore();
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) 
                {
                    window.dispose();
                }
                Menu.showMenu();
            }
            gameOver = true;
        }
            
            
        // Move the player left or right based on keyboard input.
        if (keyInputs.getLeftPressed()) 
        {
            player.moveLeft();
        }
        if (keyInputs.getRightPressed()) 
        {
            player.moveRight();
        }

        // Create a rectangle representing the player's current position to check collisions.
        Rectangle playerRect = new Rectangle(player.getPlayerX(), 
                                             player.getPlayerY(), 
                                             player.getPlayerWidth(), 
                                             player.getPlayerHeight());

        // Apply gravity and check whether the player is landing on any platform.
        boolean onPlatform = false;
        for (Rectangle platform : currentPlatforms) 
        {
            if (playerRect.intersects(platform)) 
            {
                // Only register a landing if the player is coming from above.
                if (player.getPlayerY() + player.getPlayerHeight() - keyInputs.getVelY() <= platform.y) 
                {
                    player.setPlayerY(platform.y - player.getPlayerHeight());
                    keyInputs.setVelY(0);
                    keyInputs.setJumping(false);
                    onPlatform = true;
                }
            }
        }

        // If the player hits the Level 1 checkpoint, transition to Level 2.
        if (currentLevel == 1 && playerRect.intersects(checkpoint)) 
        {
            if (!checkpointHit) 
            {
                System.out.println("Great job! You reached the checkpoint! Moving to level 2...");
                checkpointHit = true;
                player.setPlayerX(50);
                player.setPlayerY(500);
                currentPlatforms = platformsLvl2;
                currentLevel = 2;
                rules.updateLevelCompleted();
            }
        }

        // If the player hits the Level 2 checkpoint, transition to Level 3.
        if (currentLevel == 2 && playerRect.intersects(checkpoint2)) 
        {
            if (!checkpoint2Hit) 
            {
                System.out.println("Awesome! Level 2 checkpoint reached! Moving to level 3...");
                checkpoint2Hit = true;
                player.setPlayerX(50);
                player.setPlayerY(500);
                currentPlatforms = platformsLvl3;
                currentLevel = 3;
                rules.updateLevelCompleted();
            }
        }

        // In Level 3, if the player reaches the game-end object, show the win dialog and return to the menu.
        if (currentLevel == 3 && playerRect.intersects(gameEnd)) 
        {
            if (!gameEndHit && !gameOver) 
            {
                System.out.println("Congratulations, you reached the end!");
                gameEndHit = true;
                rules.stopTimer();
                rules.updateLevelCompleted();
                playerName = JOptionPane.showInputDialog(this, "You Win! Your score is " + rules.gameEnd(rules.getTime()) + 
                                                         ". Enter a 3 character name.", "Game End", 
                                                         JOptionPane.INFORMATION_MESSAGE);
                boolean goodName = rules.saveHighScore(playerName);
                while (!goodName)
                {
                    playerName = JOptionPane.showInputDialog(this, "Make sure your name is 3 characters!", "Game End", 
                                                             JOptionPane.INFORMATION_MESSAGE);
                    goodName = rules.saveHighScore(playerName);
                }
                rules.resetScore();
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) 
                {
                    window.dispose();
                }
                Menu.showMenu();
            }
            gameOver = true;
        }

        // Prevent the player from moving off the left or right edges.
        if (player.getPlayerX() < 0) 
        {
            player.setPlayerX(0);
        }
        if (player.getPlayerX() + player.getPlayerWidth() > 800) 
        {
            player.setPlayerX(800 - player.getPlayerWidth());
        }

        // If the player isn’t standing on a platform, simulate gravity.
        if (!onPlatform) 
        {
            keyInputs.setVelY(keyInputs.getVelY() + player.GRAVITY);
        }
        player.setPlayerY(player.getPlayerY() + keyInputs.getVelY());

        // Refresh the display.
        repaint();
    }
}
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
    private final Timer timer;
    Player player = new Player();
    KeyboardInputs keyInputs = new KeyboardInputs();
    Rules rules = new Rules();
    String playerName;
    boolean gameOver = false;
    Font countdownFont = new Font("countdown", Font.PLAIN, 35);

    private Rectangle[] platformsLvl1 = {
        new Rectangle(0, 550, 150, 50),
        new Rectangle(80, 500, 120, 20),
        new Rectangle(250, 450, 150, 20),
        new Rectangle(450, 400, 120, 20),
        new Rectangle(300, 350, 150, 20),
        new Rectangle(550, 300, 120, 20),
        new Rectangle(200, 250, 150, 20)
    };

    private Rectangle[] platformsLvl2 = {
        new Rectangle(0, 550, 150, 50),
        new Rectangle(150, 480, 100, 20),
        new Rectangle(500, 430, 120, 20),
        new Rectangle(250, 380, 140, 20),
        new Rectangle(600, 330, 100, 20),
        new Rectangle(100, 280, 120, 20),
        new Rectangle(350, 230, 130, 20),
        new Rectangle(550, 180, 100, 20),
        new Rectangle(200, 130, 140, 20),
        new Rectangle(400, 80, 120, 20)
    };

    private Rectangle[] platformsLvl3 = {
        new Rectangle(0, 550, 150, 50),
        new Rectangle(200, 500, 130, 20),
        new Rectangle(400, 450, 120, 20),
        new Rectangle(100, 400, 100, 20),
        new Rectangle(500, 350, 100, 20),
        new Rectangle(300, 300, 150, 20),
        new Rectangle(150, 250, 120, 20),
        new Rectangle(450, 200, 120, 20),
        new Rectangle(250, 150, 130, 20),
        new Rectangle(550, 100, 100, 20)
    };

    private int currentLevel = 1;
    private Rectangle[] currentPlatforms;

    private Rectangle checkpoint;
    private boolean checkpointHit = false;

    private Rectangle checkpoint2;
    private boolean checkpoint2Hit = false;

    private Rectangle gameEnd;
    private boolean gameEndHit = false;

    // Image for player sprite
    private Image playerImage;

    public Game() 
    {
        timer = new Timer(15, this);
        timer.start();
        setFocusable(true);
        addKeyListener(keyInputs);
        currentPlatforms = platformsLvl1;

        int checkpointWidth = 20;
        int checkpointHeight = 20;
        Rectangle highestPlatformLvl1 = platformsLvl1[platformsLvl1.length - 1];
        int checkpointX = highestPlatformLvl1.x + (highestPlatformLvl1.width - checkpointWidth) / 2;
        int checkpointY = highestPlatformLvl1.y - checkpointHeight;
        checkpoint = new Rectangle(checkpointX, checkpointY, checkpointWidth, checkpointHeight);

        int checkpoint2Width = 20;
        int checkpoint2Height = 20;
        Rectangle highestPlatformLvl2 = platformsLvl2[platformsLvl2.length - 1];
        int checkpoint2X = highestPlatformLvl2.x + (highestPlatformLvl2.width - checkpoint2Width) / 2;
        int checkpoint2Y = highestPlatformLvl2.y - checkpoint2Height;
        checkpoint2 = new Rectangle(checkpoint2X, checkpoint2Y, checkpoint2Width, checkpoint2Height);

        int gameEndWidth = 30;
        int gameEndHeight = 20;
        Rectangle highestPlatformLvl3 = platformsLvl3[platformsLvl3.length - 1];
        int gameEndX = highestPlatformLvl3.x + (highestPlatformLvl3.width - gameEndWidth) / 2;
        int gameEndY = highestPlatformLvl3.y - gameEndHeight;
        gameEnd = new Rectangle(gameEndX, gameEndY, gameEndWidth, gameEndHeight);

        rules.startCountdown();

        // Load player sprite
        File imagePath = new File("src/main/resources/enjoyer.png");
        ImageIcon imageIcon = new ImageIcon(imagePath.getPath());
        playerImage = imageIcon.getImage().getScaledInstance(30, 50, Image.SCALE_SMOOTH);
    }

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

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw player sprite
        g.drawImage(playerImage, player.getPlayerX(), player.getPlayerY(), null);

        // Draw platforms
        g.setColor(Color.BLACK);
        for (Rectangle platform : currentPlatforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }

        // Draw interactive objects
        if (currentLevel == 1) {
            g.setColor(Color.GREEN);
            g.fillRect(checkpoint.x, checkpoint.y, checkpoint.width, checkpoint.height);
        } else if (currentLevel == 2) {
            g.setColor(Color.GREEN);
            g.fillRect(checkpoint2.x, checkpoint2.y, checkpoint2.width, checkpoint2.height);
        } else if (currentLevel == 3) {
            g.setColor(Color.MAGENTA);
            g.fillRect(gameEnd.x, gameEnd.y, gameEnd.width, gameEnd.height);
        }

        // Draw countdown
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 100, 50);
        g.setColor(Color.WHITE);
        g.setFont(countdownFont);
        g.drawString(String.valueOf(rules.getTime()), 30, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (rules.getTimerEnd() || player.getPlayerY() > 600) 
        {
            if (!gameOver) 
            {
                rules.stopTimer();
                repaint();
                playerName = JOptionPane.showInputDialog(this, "You Lose! Your score is " + rules.gameEnd(rules.getTime()) +
                        ". Enter a 3 character name.", "Game End", JOptionPane.INFORMATION_MESSAGE);
                boolean goodName = rules.saveHighScore(playerName);
                while (!goodName) 
                {
                    playerName = JOptionPane.showInputDialog(this, "Make sure your name is 3 characters!", "Game End",
                            JOptionPane.INFORMATION_MESSAGE);
                    goodName = rules.saveHighScore(playerName);
                }
                rules.resetScore();
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) window.dispose();
                Menu.showMenu();
            }
            gameOver = true;
        }

        if (keyInputs.getLeftPressed()) player.moveLeft();
        if (keyInputs.getRightPressed()) player.moveRight();

        Rectangle playerRect = new Rectangle(player.getPlayerX(), player.getPlayerY(), player.getPlayerWidth(), player.getPlayerHeight());

        boolean onPlatform = false;
        for (Rectangle platform : currentPlatforms) 
        {
            if (playerRect.intersects(platform)) 
            {
                if (player.getPlayerY() + player.getPlayerHeight() - keyInputs.getVelY() <= platform.y) 
                {
                    player.setPlayerY(platform.y - player.getPlayerHeight());
                    keyInputs.setVelY(0);
                    keyInputs.setJumping(false);
                    onPlatform = true;
                }
            }
        }

        if (currentLevel == 1 && playerRect.intersects(checkpoint)) 
        {
            if (!checkpointHit) 
            {
                checkpointHit = true;
                player.setPlayerX(50);
                player.setPlayerY(500);
                currentPlatforms = platformsLvl2;
                currentLevel = 2;
                rules.updateLevelCompleted();
            }
        }

        if (currentLevel == 2 && playerRect.intersects(checkpoint2)) 
        {
            if (!checkpoint2Hit) 
            {
                checkpoint2Hit = true;
                player.setPlayerX(50);
                player.setPlayerY(500);
                currentPlatforms = platformsLvl3;
                currentLevel = 3;
                rules.updateLevelCompleted();
            }
        }

        if (currentLevel == 3 && playerRect.intersects(gameEnd)) 
        {
            if (!gameEndHit && !gameOver) 
            {
                gameEndHit = true;
                rules.stopTimer();
                rules.updateLevelCompleted();
                playerName = JOptionPane.showInputDialog(this, "You Win! Your score is " + rules.gameEnd(rules.getTime()) +
                        ". Enter a 3 character name.", "Game End", JOptionPane.INFORMATION_MESSAGE);
                boolean goodName = rules.saveHighScore(playerName);
                while (!goodName) 
                {
                    playerName = JOptionPane.showInputDialog(this, "Make sure your name is 3 characters!", "Game End",
                            JOptionPane.INFORMATION_MESSAGE);
                    goodName = rules.saveHighScore(playerName);
                }
                rules.resetScore();
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) window.dispose();
                Menu.showMenu();
            }
            gameOver = true;
        }

        if (player.getPlayerX() < 0) player.setPlayerX(0);
        if (player.getPlayerX() + player.getPlayerWidth() > 800) player.setPlayerX(800 - player.getPlayerWidth());

        if (!onPlatform) 
        {
            keyInputs.setVelY(keyInputs.getVelY() + player.GRAVITY);
        }
        player.setPlayerY(player.getPlayerY() + keyInputs.getVelY());

        repaint();
    }
}

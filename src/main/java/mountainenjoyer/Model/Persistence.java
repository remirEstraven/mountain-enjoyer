/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountainenjoyer.Model;

import java.io.Serializable;

/**
 * Saves the hiscores and associated names of users and gives them to the menu when prompted
 * 
 * @author colee
 */
public class Persistence implements Serializable
{
    public int highScore;
    public String usersName;
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public String getUsersName()
    {
        return usersName;
    }
    
    public void setHighScore(int givenHS)
    {
        highScore = givenHS;
    }
    
    public void setUsersName(String givenName)
    {
        usersName = givenName;
    }
}

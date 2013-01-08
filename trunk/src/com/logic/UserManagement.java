/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import java.io.File;

import com.config.Config;
import com.db.logic.UserManager;
import com.db.model.Users;

/**
 *
 * @author agata
 */
public class UserManagement 
{
    public static long getAvailableSpace(String username) 
    {
	return UserManager.getAvailableSpace(UserManager.getUserID(username));
    }
    
    public static boolean addUser(String username, String password) 
    {
        new File(Config.path + username).mkdir(); 
        return UserManager.addUser(username, password);
    }
    
    public static boolean changeUserPassword(String username, String password) 
    {
        Users user = UserManager.getUserByID(UserManager.getUserID(username));
        user.password = password; 
        return UserManager.updateUser(user);
    }
}

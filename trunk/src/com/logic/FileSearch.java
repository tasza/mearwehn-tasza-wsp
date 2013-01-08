/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import java.util.List;

import com.db.logic.FileManager;
import com.db.logic.TagManager;
import com.db.logic.UserManager;
import com.db.model.Files;

/**
 *
 * @author agata
 */
public class FileSearch 
{
    public static String listAllUserFiles(String username)
    {
        List<Files> files = FileManager.getFilesByUser(UserManager.getUserID(username)); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.path + file.name; 
        }
        return fileNames; 
    }
    
    public static String listAllUserTagedFiles(String username)
    {
        List<Files> files = FileManager.getUserFilesTaged(UserManager.getUserID(username)); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.path + file.name; 
        }
        return fileNames; 
    }
    
    public static String listAllUserSharedFiles(String username)
    {
        List<Files> files = FileManager.getUserFilesShared(UserManager.getUserID(username)); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.path + file.name; 
        }
        return fileNames; 
    }
    
    public static String searchFilesByName(String username, String filename)
    {
        List<Files> files = FileManager.getFilesByName(UserManager.getUserID(username), filename); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.owner + "/" + file.path + file.name; 
        }
        return fileNames; 
    } 
    
    public static String searchFilesByTag(String username, String tag)
    {
        List<Files> files = FileManager.getFilesByTag(UserManager.getUserID(username), TagManager.getTagID(tag)); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.owner + "/" + file.path + file.name; 
        }
        return fileNames; 
    } 
    
    public static String searchFilesByType(String username, String type)
    {
        List<Files> files = FileManager.getFilesByType(UserManager.getUserID(username), type); 
        String fileNames = ""; 
        for(Files file : files)
        {
            fileNames += file.owner + "/" + file.path + file.name; 
        }
        return fileNames; 
    } 
}

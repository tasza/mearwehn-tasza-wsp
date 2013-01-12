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
import com.db.model.SharedFile;
import com.db.model.TagedFile;

/**
 *
 * @author agata
 */
public class FileSearch 
{
    public static String listAllUserFiles(String username)
    {
        List<Files> files = FileManager.getFilesByUser(UserManager.getUserID(username)); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(Files file : files)
        {
        	if(file.type.equals("folder")) fileNames += file.path + file.name + "/\n"; 
        	else fileNames += file.path + file.name + "\n"; 
        }
        return fileNames; 
    }
    
    public static String listAllUserTagedFiles(String username)
    {
        List<TagedFile> files = FileManager.getUserFilesTaged(UserManager.getUserID(username)); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(TagedFile file : files)
        {
        	if(!file.path.equals("") && file.path != null) fileNames += "tag: " + file.tag + " file: " + file.path + "/" + file.filename; 
        	else fileNames += "tag: " + file.tag + " file: " + file.filename; 
        	if(file.type.equals("folder")) fileNames += "/\n"; 
        	else fileNames += "\n"; 
        }
        return fileNames; 
    }
    
    public static String listAllUserSharedFiles(String username)
    {
        List<SharedFile> files = FileManager.getUserFilesShared(UserManager.getUserID(username)); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(SharedFile file : files)
        {
        	if(!file.path.equals("") && file.path != null) fileNames += "shared for: " + file.user + " file: " + file.path + "/" + file.filename; 
        	else fileNames += "shared for: " + file.user + " file: " + file.filename; 
        	if(file.type.equals("folder")) fileNames += "/\n"; 
        	else fileNames += "\n"; 
        }
        return fileNames; 
    }
    
    public static String searchFilesByName(String username, String filename)
    {
        List<TagedFile> files = FileManager.getFilesByName(UserManager.getUserID(username), filename); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(TagedFile file : files)
        {
        	if(!file.path.equals("") && file.path != null) fileNames += "owner: " + file.owner + " file: " + file.path + "/" + file.filename; 
        	else fileNames += "owner: " + file.owner + " file: " + file.filename; 
        	if(file.type.equals("folder")) fileNames += "/\n"; 
        	else fileNames += "\n"; 
        }
        return fileNames; 
    } 
    
    public static String searchFilesByTag(String username, String tag)
    {
        List<TagedFile> files = FileManager.getFilesByTag(UserManager.getUserID(username), TagManager.getTagID(tag)); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(TagedFile file : files)
        {
        	if(!file.path.equals("") && file.path != null) fileNames += "owner: " + file.owner + " file: " + file.path + "/" + file.filename; 
        	else fileNames += "owner: " + file.owner + " file: " + file.filename; 
        	if(file.type.equals("folder")) fileNames += "/\n"; 
        	else fileNames += "\n"; 
        }
        return fileNames; 
    } 
    
    public static String searchFilesByType(String username, String type)
    {
        List<TagedFile> files = FileManager.getFilesByType(UserManager.getUserID(username), type); 
        if(files == null) return ""; 
        String fileNames = ""; 
        for(TagedFile file : files)
        {
        	if(!file.path.equals("") && file.path != null) fileNames += "owner: " + file.owner + " file: " + file.path + "/" + file.filename; 
        	else fileNames += "owner: " + file.owner + " file: " + file.filename; 
        	if(file.type.equals("folder")) fileNames += "/\n"; 
        	else fileNames += "\n"; 
        }
        return fileNames; 
    } 
}

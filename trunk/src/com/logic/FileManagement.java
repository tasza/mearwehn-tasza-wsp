/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import java.io.File;
import java.util.List;

import com.config.Config;
import com.db.logic.*;
import com.db.model.Files;

/**
 *
 * @author agata
 */
public class FileManagement 
{    
    public static boolean createFolder(String username, String filename, String path) 
    {
	String wholePath; 
        int owner = UserManager.getUserID(username); 
	if(!(UserManager.getAvailableSpace(owner) > 0)) return false;
	try
	{
		wholePath = Config.path + username + "/" + path;
		new File(wholePath + filename).mkdir(); 
                if(FileManager.addFile(filename, path, "folder", owner)) return true; 
		new File(wholePath + filename).delete();
                return false; 
	}
	catch (Exception e)
	{
		return false; 
	}
    }
    
    public static boolean deleteFileOrFolder(String username, String path, String filename) 
    {
        try
        {
            int fileId = FileManager.getFileID(path, filename, UserManager.getUserID(username)); 
            if(!SharedFilesManager.deleteSharedFileByFile(fileId)) return false; 
            if(!TagedFilesManager.deleteTagedFileByFile(fileId)) return false; 
            new File(Config.path + username + "/" + path + filename).delete();
            if(!FileManager.deleteFile(fileId)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean tagFile(String tag, String filename, String path, String username) 
    {
        try
        {
            int tagID = TagManager.getTagID(tag); 
            int fileID = FileManager.getFileID(path, filename, UserManager.getUserID(username)); 
            if(!TagedFilesManager.addTagedFile(tagID, fileID)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean removeTagFromFile(String tag, String filename, String path, String username) 
    {
        try
        {
            int fileID = FileManager.getFileID(path, filename, UserManager.getUserID(username)); 
            int tagID = TagManager.getTagID(tag); 
            if(!TagedFilesManager.deleteTagedFileByFileTag(fileID, tagID)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean shareFile(String filename, String path, String owner, String username) 
    {
        try
        {
            int ownerID = UserManager.getUserID(owner); 
            int userID = UserManager.getUserID(username); 
            int fileID = FileManager.getFileID(path, filename, ownerID); 
            Files file = FileManager.getFileByID(fileID); 
            if("folder".equals(file.type))
            {
                List<Files> files = FileManager.getAllFiles(); 
                for(Files f : files)
                {
                    if(f.path.startsWith(file.path + file.name) && f.owner == ownerID) 
                        SharedFilesManager.addSharedFile(f.id, userID);
                }
            }
            SharedFilesManager.addSharedFile(fileID, userID);
            return true;
        }
	catch (Exception e)
	{
            return false; 
	}
    }
    
    public static boolean unshareFile(String filename, String path, String owner, String username) 
    {
        try
        {
            int ownerID = UserManager.getUserID(owner); 
            int userID = UserManager.getUserID(username); 
            int fileID = FileManager.getFileID(path, filename, ownerID); 
            Files file = FileManager.getFileByID(fileID); 
            if("folder".equals(file.type))
            {
                List<Files> files = FileManager.getAllFiles(); 
                for(Files f : files)
                {
                    if(f.path.startsWith(file.path + file.name) && f.owner == ownerID) 
                        SharedFilesManager.deleteSharedFileByFileUser(f.id, userID);
                }
            }
            SharedFilesManager.deleteSharedFileByFileUser(fileID, userID);
            return true;
        }
	catch (Exception e)
	{
            return false; 
	}
    }
}
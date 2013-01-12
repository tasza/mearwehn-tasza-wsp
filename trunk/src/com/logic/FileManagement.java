/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
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
        if(owner == -1) return false; 
        if(!(UserManager.getAvailableSpace(owner) > 0)) return false;
        if(FileManager.getFileID(path, filename, "folder", owner) != -1) return false; 
        try
        {
        	wholePath = Config.path + username + "/" + path;
        	new File(wholePath + "/" + filename).mkdir(); 
            if(FileManager.addFile(filename, path, "folder", owner)) return true; 
            new File(wholePath + "/" + filename).delete();
            return false; 
        }
        catch (Exception e)
        {
        	return false; 
        }
    }
    
    public static boolean uploadFile(InputStream uploadedInputStream, String filename, String path, String username) 
    {
    	int owner = UserManager.getUserID(username); 
        if(owner == -1) return false; 
        
    	String wholePath = Config.path + username + "/" + path;
    	if (!FileManagement.writeToFile(uploadedInputStream, wholePath + "/" + filename)) 
		{
			new File(wholePath + "/" + filename).delete(); 
			return false; 
		}
		
		File file = new File(wholePath + "/" + filename);
		
		String mimeType = "";
		try 
		{
			mimeType = Magic.getMagicMatch(file, false).getMimeType();
		} 
		catch (MagicParseException | MagicMatchNotFoundException
					| MagicException e) 
		{
			e.printStackTrace();	
		}
		if(mimeType.equals("")) 
		{
			new File(wholePath + "/" + filename).delete(); 
			return false; 
		}
		if(FileManager.getFileID(path, filename, mimeType, owner) != -1) 
		{
			new File(wholePath + "/" + filename).delete(); 
			return false; 
		} 
		long space = UserManager.getAvailableSpace(owner); 
		if(space < file.length())
		{
			new File(wholePath + "/" + filename).delete();
			return false; 
		}
		if(!UserManager.updateAvailableSpace(owner, space - file.length()))
		{
			new File(wholePath + "/" + filename).delete();
			return false; 
		}
		if(!FileManager.addFile(filename, path, mimeType, owner))
		{
			UserManager.updateAvailableSpace(owner, space); 
			new File(wholePath + "/" + filename).delete();
			return false; 
		}
		return true; 	
    }
    
    public static File downloadFile(String username, String filename, String path, String type) 
    {
    	String wholePath; 
        int owner = UserManager.getUserID(username); 
        if(owner == -1) 
        	return null; 
        if(FileManager.getFileID(path, filename, type, owner) == -1) return null; 
        wholePath = Config.path + username + "/" + path;
        return new File(wholePath + "/" + filename);
    }
    
    private static boolean writeToFile(InputStream uploadedInputStream,
 		String uploadedFileLocation) {
 		try 
 		{
 			OutputStream out = new FileOutputStream(new File(
 					uploadedFileLocation));
 			int read = 0;
 			byte[] bytes = new byte[1024];
  
 			out = new FileOutputStream(new File(uploadedFileLocation));
 			while ((read = uploadedInputStream.read(bytes)) != -1) 
 			{
 				out.write(bytes, 0, read);
 			}
 			out.flush();
 			out.close();
 		} 
 		catch (IOException e) 
 		{
 			return false; 
 		}
 		return true;
 	}
    
    public static boolean deleteFileOrFolder(String username, String path, String filename, String type) 
    {
        try
        {
        	int owner = UserManager.getUserID(username); 
        	if(owner == -1) return false; 
            int fileId = FileManager.getFileID(path, filename, type, owner); 
            if(fileId == -1) return false; 
            if(!SharedFilesManager.deleteSharedFileByFile(fileId)) return false; 
            if(!TagedFilesManager.deleteTagedFileByFile(fileId)) return false; 
            if(!UserManager.updateAvailableSpace(owner, UserManager.getAvailableSpace(owner) + new File(Config.path + username + "/" + path + "/" + filename).length())) return false; 
            if(!new File(Config.path + username + "/" + path + "/" + filename).delete()) return false;
            if(!FileManager.deleteFile(fileId)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean tagFile(String tag, String filename, String path, String type, String username) 
    {
        try
        {
            int tagID = TagManager.getTagID(tag); 
            int owner = UserManager.getUserID(username); 
            if(tagID == -1 || owner == -1) return false; 
            int fileID = FileManager.getFileID(path, filename, type, owner); 
            if(fileID == -1) return false; 
            if(TagedFilesManager.getTagedFileID(fileID, tagID) != -1) return false; 
            if(!TagedFilesManager.addTagedFile(tagID, fileID)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean removeTagFromFile(String tag, String filename, String path, String type, String username) 
    {
        try
        {
        	int owner = UserManager.getUserID(username); 
        	if(owner == -1) return false; 
            int fileID = FileManager.getFileID(path, filename, type, owner); 
            int tagID = TagManager.getTagID(tag); 
            if(fileID == -1 || tagID == -1) return false; 
            if(!TagedFilesManager.deleteTagedFileByFileTag(fileID, tagID)) return false; 
        }
	catch (Exception e)
	{
            return false; 
	}
	return true;
    }
    
    public static boolean shareFile(String filename, String path, String type, String owner, String username) 
    {
        try
        {
            int ownerID = UserManager.getUserID(owner); 
            int userID = UserManager.getUserID(username); 
            if(ownerID == -1 || userID == -1) return false; 
            int fileID = FileManager.getFileID(path, filename, type, ownerID); 
            if(fileID == -1) return false; 
            if(SharedFilesManager.getSharedFileID(fileID, userID) != -1) return false; 
            Files file = FileManager.getFileByID(fileID); 
            if("folder".equals(file.type))
            {
                List<Files> files = FileManager.getAllFiles(); 
                for(Files f : files)
                {
                    if(f.path.startsWith(file.path + file.name) && f.owner == ownerID) 
                    {
                    	if(SharedFilesManager.getSharedFileID(f.id, userID) != -1) continue; 
                        SharedFilesManager.addSharedFile(f.id, userID);
                    }
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
    
    public static boolean unshareFile(String filename, String path, String type, String owner, String username) 
    {
        try
        {
            int ownerID = UserManager.getUserID(owner); 
            int userID = UserManager.getUserID(username); 
            if(ownerID == -1 || userID == -1) return false;
            int fileID = FileManager.getFileID(path, filename, type, ownerID); 
            if(fileID == -1) return false;
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
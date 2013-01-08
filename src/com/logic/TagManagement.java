/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.db.logic.TagManager;
import com.db.logic.TagedFilesManager;

/**
 *
 * @author agata
 */
public class TagManagement 
{
    public static boolean deleteTag(String tag) 
    {
        try
        {
            int tagId = TagManager.getTagID(tag); 
            if(!TagedFilesManager.deleteTagedFileByTag(tagId)) return false; 
            if(!TagManager.deleteTag(tagId)) return false; 
        }
	catch (Exception e)
	{
		System.out.println(e);
            return false; 
	}
	return true;
    }
    
    public static boolean addTag(String tag) 
    {
        try
        {
            if(!TagManager.addTag(tag)) return false; 
        }
	catch (Exception e)
	{
		System.out.println(e);
            return false; 
	}
	return true;
    }
}

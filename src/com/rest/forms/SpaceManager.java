package com.rest.forms;

import java.io.File;

import com.config.Config;

public class SpaceManager 
{
	private long space = 500000; 
	public long getAvailableSpace(String username) 
	{
		String directory; 
		directory = Config.path + username;
		File userFile = new File(directory); 
	    long usedSpace = this.folderSize(userFile); 
		return this.space - usedSpace;
	}
	
	public long folderSize(File directory) 
    {
        long length = 0;
        for (File file : directory.listFiles()) 
        {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}

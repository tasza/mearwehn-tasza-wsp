package com.rest.forms;

import java.io.File;

public class FileReader
{
	public String listFiles(String path)
    {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        return listOfFiles.toString(); 
    }
}

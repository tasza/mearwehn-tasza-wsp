package com.db.model;

public class TagedFile 
{
	public String tag; 
    public String filename; 
    public String path; 
    public String type; 
    public String owner; 
    
    public TagedFile(String tag, String filename, String path, String type, String owner) 
    {
        this.tag = tag;
        this.filename = filename;
        this.path = path;
        this.type = type; 
        this.owner = owner; 
    }
}

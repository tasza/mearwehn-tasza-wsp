package com.db.model;

public class SharedFile 
{
	public String user; 
    public String filename; 
    public String path; 
    public String type; 
    public String owner; 
    
    public SharedFile(String user, String filename, String path, String type, String owner) 
    {
        this.user = user;
        this.filename = filename;
        this.path = path;
        this.type = type; 
        this.owner = owner; 
    }
}

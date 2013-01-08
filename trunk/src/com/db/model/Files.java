/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.model;

/**
 *
 * @author agata
 */
public class Files 
{
    public int id; 
    public String name; 
    public String path; 
    public String type; 
    public int owner; 
    
    public Files(int id, String name, String path, String type, int owner) 
    {
        this.id = id;
        this.name = name;
        this.path = path; 
        this.type = type; 
        this.owner = owner; 
    }
}

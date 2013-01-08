/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.model;

/**
 *
 * @author agata
 */
public class SharedFiles 
{
    public int id; 
    public int file;
    public int user; 
    
    public SharedFiles(int id, int file, int user) 
    {
        this.id = id;
        this.file = file; 
        this.user = user;
    }
}

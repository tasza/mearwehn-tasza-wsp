/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.model;

/**
 *
 * @author agata
 */
public class Users 
{
    public int id;
    public String name;
    public String password;
    public long availableStorageSpace; 
    
    public Users(int id, String name, String password, long availableStorageSpace) 
    {
        this.id = id;
        this.name = name;
        this.password = password; 
        this.availableStorageSpace = availableStorageSpace; 
    }
}

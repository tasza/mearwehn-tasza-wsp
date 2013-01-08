/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.model;

/**
 *
 * @author agata
 */
public class TagedFiles 
{
    public int id; 
    public int tag; 
    public int file; 
    
    public TagedFiles(int id, int tag, int file) 
    {
        this.id = id;
        this.tag = tag;
        this.file = file; 
    }
}

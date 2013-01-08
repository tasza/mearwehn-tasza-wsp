/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.logic;

import com.db.model.TagedFiles;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agata
 */
public class TagedFilesManager 
{
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ws";
    private static String uid = "root";
    private static String pwd = "lamora";
    public static boolean addTagedFile(int tag, int file)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("INSERT INTO tagedFiles(file, user) VALUES(?,?)");
            ps.setInt(2, tag);
            ps.setInt(1, file); 
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } 
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteTagedFile(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tagedFiles WHERE id = ?");
            ps.setInt(1, ID);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteTagedFileByFile(int file)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tagedFiles WHERE file = ?");
            ps.setInt(1, file);
            ps.executeUpdate(); 
            con.close();
            return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteTagedFileByTag(int tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tagedFiles WHERE tag = ?");
            ps.setInt(1, tag);
            ps.executeUpdate(); 
            con.close();
            return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteTagedFileByFileTag(int file, int tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tagedFiles WHERE file = ? AND tag = ?");
            ps.setInt(1, file);
            ps.setInt(2, tag);
            ps.executeUpdate(); 
            con.close();
            return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static TagedFiles getTagedFileByID(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tagedFiles WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return new TagedFiles(rs.getInt("id"), rs.getInt("tag"), rs.getInt("file")); 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<TagedFiles> getAllTagedFiles()
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tagedFiles");
            ResultSet rs = ps.executeQuery();
            con.close();
            List<TagedFiles> tagedFiles = new ArrayList<TagedFiles>(); 
            while(rs.next())
                tagedFiles.add(new TagedFiles(rs.getInt("id"), rs.getInt("tag"), rs.getInt("file"))); 
            return tagedFiles; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static boolean updateTagedFiles(TagedFiles tagedFile)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("UPDATE tagedFiles SET tag = ?, file = ? WHERE id = ?");
            ps.setInt(1, tagedFile.tag);
            ps.setInt(2, tagedFile.file);
            ps.setInt(4, tagedFile.id);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
}

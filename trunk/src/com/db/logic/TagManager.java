/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.logic;

import com.db.model.Tags;
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
public class TagManager 
{
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ws";
    private static String uid = "root";
    private static String pwd = "lamora";
    public static boolean addTag(String tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("INSERT INTO tags(tag) VALUES(?)");
            ps.setString(1, tag);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
    
    public static boolean deleteTag(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tags WHERE id = ?");
            ps.setInt(1, ID);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteTagByFile(int file)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM tags WHERE file = ?");
            ps.setInt(1, file);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static Tags getTagByID(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tags WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return new Tags(rs.getInt("id"), rs.getString("tag")); 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static int getTagID(String tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT id FROM tags WHERE tag = ?");
            ps.setString(1, tag);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return rs.getInt("id"); 
        } catch (Exception ex) {}
        return -1; 
    }
    
    public static List<Tags> getAllTags()
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tags");
            ResultSet rs = ps.executeQuery();
            con.close();
            List<Tags> tags = new ArrayList<Tags>(); 
            while(rs.next())
                tags.add(new Tags(rs.getInt("id"), rs.getString("tag"))); 
            return tags; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static boolean updateTags(Tags tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("UPDATE tags SET tag = ? WHERE id = ?");
            ps.setString(1, tag.tag);
            ps.setInt(4, tag.id);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
}

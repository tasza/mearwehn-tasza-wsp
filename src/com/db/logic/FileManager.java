/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.logic;

import com.db.model.Files;
import com.db.model.SharedFile;
import com.db.model.TagedFile;
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
public class FileManager 
{
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ws";
    private static String uid = "root";
    private static String pwd = "lamora";
    public static boolean addFile(String name, String path, String type, int owner)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("INSERT INTO files(name, path, type, owner) VALUES(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, path);
            ps.setString(3, type);
            ps.setInt(4, owner);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {System.out.println(ex); }
        return false; 
    }
    
    public static boolean deleteFile(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM files WHERE id = ?");
            ps.setInt(1, ID);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static Files getFileByID(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM files WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            Files f = null; 
            if(rs.next())
                f =  new Files(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getInt("owner"));
            con.close();
            return f; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static int getFileID(String path, String filename, String type, int user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT id FROM files WHERE path = ? AND name = ? AND owner = ? AND type = ?");
            ps.setString(1, path);
            ps.setString(2, filename);
            ps.setInt(3, user);
            ps.setString(4, type);
            ResultSet rs = ps.executeQuery();
            int result = -1; 
            if(rs.next())
                result = rs.getInt("id"); 
            con.close();
            return result; 
        } catch (Exception ex) {}
        return -1; 
    }
    
    public static List<Files> getAllFiles()
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM files");
            ResultSet rs = ps.executeQuery();
            List<Files> files = new ArrayList<Files>(); 
            while(rs.next())
                files.add(new Files(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"),rs.getInt("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<Files> getFilesByUser(int owner)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM files WHERE owner = ?");
            ps.setInt(1, owner);
            ResultSet rs = ps.executeQuery();
            List<Files> files = new ArrayList<Files>(); 
            while(rs.next())
                files.add(new Files(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getInt("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<Files> getFilesSharedForUser(int user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM files f INNER JOIN sharedFiles sf ON f.ID = sf.file WHERE sf.user = ?");
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();
            List<Files> files = new ArrayList<Files>(); 
            while(rs.next())
                files.add(new Files(rs.getInt("id"), rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getInt("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<SharedFile> getUserFilesShared(int owner)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT u.name AS username, f.name, f.path, f.type FROM files f INNER JOIN sharedFiles sf ON f.id = sf.file INNER JOIN users u ON sf.user = u.id WHERE f.owner = ?");
            ps.setInt(1, owner);
            ResultSet rs = ps.executeQuery();
            List<SharedFile> files = new ArrayList<SharedFile>(); 
            while(rs.next())
                files.add(new SharedFile(rs.getString("username"), rs.getString("name"), rs.getString("path"), rs.getString("type"), UserManager.getUserByID(owner).name)); 
            con.close();
            return files; 
        } catch (Exception ex) {System.out.println(ex); }
        return null; 
    }
    
    public static List<TagedFile> getUserFilesTaged(int owner)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT t.tag, f.name, f.path, f.type FROM files f INNER JOIN tagedFiles tf ON tf.file = f.id INNER JOIN tags t ON t.id = tf.tag WHERE owner = ?");
            ps.setInt(1, owner);
            ResultSet rs = ps.executeQuery();
            List<TagedFile> files = new ArrayList<TagedFile>(); 
            while(rs.next())
                files.add(new TagedFile(rs.getString("tag"), rs.getString("name"), rs.getString("path"), rs.getString("type"), UserManager.getUserByID(owner).name)); 
            con.close();
            return files; 
        } catch (Exception ex) {System.out.println(ex); }
        return null; 
    }
    
    public static List<TagedFile> getFilesByName(int user, String name)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT f.name, f.path, f.type, u.name AS owner FROM files f LEFT JOIN sharedFiles sf ON f.ID = sf.file INNER JOIN users u ON u.id = f.owner WHERE f.name = ? AND (f.owner = ? OR  sf.user= ?)");
            ps.setString(1, name);
            ps.setInt(2, user);
            ps.setInt(3, user);
            ResultSet rs = ps.executeQuery();
            List<TagedFile> files = new ArrayList<TagedFile>(); 
            while(rs.next())
                files.add(new TagedFile(null, rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getString("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<TagedFile> getFilesByTag(int user, int tag)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT t.tag, f.name, f.path, f.type, u.name AS owner FROM files f LEFT JOIN sharedFiles sf ON f.ID = sf.file INNER JOIN tagedFiles tf ON f.ID = tf.file INNER JOIN users u ON u.id = f.owner INNER JOIN tags t ON t.id = tf.tag WHERE tf.tag = ? AND (f.owner = ? OR  sf.user= ?)");
            ps.setInt(1, tag);
            ps.setInt(2, user);
            ps.setInt(3, user);
            ResultSet rs = ps.executeQuery();
            List<TagedFile> files = new ArrayList<TagedFile>(); 
            while(rs.next())
                files.add(new TagedFile(rs.getString("tag"), rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getString("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<TagedFile> getFilesByType(int user, String type)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT f.name, f.path, f.type, u.name AS owner FROM files f LEFT JOIN sharedFiles sf ON f.ID = sf.file INNER JOIN users u ON u.id = f.owner WHERE f.type = ? AND (f.owner = ? OR  sf.user= ?)");
            ps.setString(1, type);
            ps.setInt(2, user);
            ps.setInt(3, user);
            ResultSet rs = ps.executeQuery();
            List<TagedFile> files = new ArrayList<TagedFile>(); 
            while(rs.next())
                files.add(new TagedFile(null, rs.getString("name"), rs.getString("path"), rs.getString("type"), rs.getString("owner"))); 
            con.close();
            return files; 
        } catch (Exception ex) {System.out.println(ex); }
        return null; 
    }
    
    public static boolean updateFile(Files file)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("UPDATE files SET name = ?, path = ?, type = ?, owner = ? WHERE id = ?");
            ps.setString(1, file.name);
            ps.setString(2, file.path);
            ps.setString(3, file.type);
            ps.setInt(4, file.owner);
            ps.setInt(5, file.id);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.logic;

import com.db.model.SharedFiles;
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
public class SharedFilesManager 
{
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ws";
    private static String uid = "root";
    private static String pwd = "lamora";
    public static boolean addSharedFile(int file, int user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("INSERT INTO sharedFiles(file, user) VALUES(?,?)");
            ps.setInt(1, file);
            ps.setInt(2, user);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
    
    public static boolean deleteSharedFile(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM sharedFiles WHERE id = ?");
            ps.setInt(1, ID);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteSharedFileByFile(int file)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM sharedFiles WHERE file = ?");
            ps.setInt(1, file);
            ps.executeUpdate(); 
            con.close();
            return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static boolean deleteSharedFileByFileUser(int file, int user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM sharedFiles WHERE file = ? AND user = ?");
            ps.setInt(1, file);
            ps.setInt(2, user);
            ps.executeUpdate(); 
            con.close();
            return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static SharedFiles getSharedFileByID(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sharedFiles WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            SharedFiles sf = null; 
            if(rs.next())
                sf =  new SharedFiles(rs.getInt("id"), rs.getInt("file"), rs.getInt("user")); 
            con.close();
            return sf; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static int getSharedFileID(int file, int user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT id FROM sharedFiles WHERE file = ? AND user = ?");
            ps.setInt(1, file);
            ps.setInt(2, user);
            ResultSet rs = ps.executeQuery();
            int id = -1; 
            if(rs.next())
                id = rs.getInt("id"); 
            con.close();
            return id; 
        } catch (Exception ex) {}
        return -1; 
    }
    
    public static List<SharedFiles> getAllSharedFiles()
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM sharedFiles");
            ResultSet rs = ps.executeQuery();
            List<SharedFiles> sharedFiles = new ArrayList<SharedFiles>(); 
            while(rs.next())
                sharedFiles.add(new SharedFiles(rs.getInt("id"), rs.getInt("file"), rs.getInt("user"))); 
            con.close();
            return sharedFiles; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static boolean updateSharedFiles(SharedFiles sharedFile)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("UPDATE sharedFiles SET file = ?, user = ? WHERE id = ?");
            ps.setInt(1, sharedFile.file);
            ps.setInt(2, sharedFile.user);
            ps.setInt(4, sharedFile.id);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
}

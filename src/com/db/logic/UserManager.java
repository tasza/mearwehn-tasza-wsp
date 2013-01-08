/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.logic;

import com.db.model.Users;
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
public class UserManager 
{
    private static long storageSpace = 50000000; 
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ws";
    private static String uid = "root";
    private static String pwd = "lamora";
    
    public static boolean addUser(String name, String password)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, password, availableStorageSpace) VALUES(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setLong(3, storageSpace);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {
        	System.out.println(ex.toString());
        }
        return false; 
    }
    
    public static boolean deleteUser(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setInt(1, ID);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0)  
                return true; 
        } catch (Exception ex) {}
        return false;
    }
    
    public static int getUserID(String name)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT id FROM users WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return rs.getInt("id"); 
        } catch (Exception ex) {}
        return -1;
    }
    
    public static int getAvailableSpace(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT availableStorageSpace FROM users WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return rs.getInt("availableStorageSpace"); 
        } catch (Exception ex) {}
        return -1;
    }
    
    public static Users getUserByID(int ID)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            con.close();
            if(rs.next())
                return new Users(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getLong("availableStorageSpace")); 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static List<Users> getAllUsers()
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            con.close();
            List<Users> users = new ArrayList<Users>(); 
            while(rs.next())
                users.add(new Users(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getLong("availableStorageSpace"))); 
            return users; 
        } catch (Exception ex) {}
        return null; 
    }
    
    public static boolean updateUser(Users user)
    {
        try 
        {
        	Class.forName(driverName);
        	Connection con = (Connection) DriverManager.getConnection(url, uid, pwd);
            PreparedStatement ps = con.prepareStatement("UPDATE users SET name = ?, password = ?, availableStorageSpace = ? WHERE id = ?");
            ps.setString(1, user.name);
            ps.setString(2, user.password);
            ps.setLong(3, user.availableStorageSpace);
            ps.setInt(4, user.id);
            int statement = ps.executeUpdate(); 
            con.close();
            if (statement != 0) 
                return true; 
        } catch (Exception ex) {}
        return false; 
    }
}

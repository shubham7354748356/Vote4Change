/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author india
 */
public class RegistrationDao 
{
    private static PreparedStatement ps , ps1 , ps2,ps3 , ps4,ps5;
    static
    {
        try
        {
            ps = DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
            ps1 = DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?,?,?)");
            ps2 = DBConnection.getConnection().prepareStatement("select adhar_no from user_details where user_type != 'admin'");
            ps3 = DBConnection.getConnection().prepareStatement("select username,city ,mobile_no , email,address from user_details where adhar_no=?");
            ps4 = DBConnection.getConnection().prepareStatement("update user_details set city=?,email=?,mobile_no=?,address=? where adhar_no=?");
            ps5 = DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
        }
        catch(SQLException sql)
        {
            sql.printStackTrace();;
        }
                 
    }
    public static boolean searchUser(String userId)throws SQLException
    {
        ps.setString(1, userId);
        return ps.executeQuery().next();
    }
    public static boolean registerUser(UserDetails ur)throws SQLException
    {
        ps1.setString(1, ur.getUserid());
        ps1.setString(2, ur.getUsername());
        ps1.setString(5, ur.getCity());
        ps1.setString(6, ur.getEmail());
        ps1.setLong(7, ur.getMobile());
        ps1.setString(4, ur.getAddress());
        ps1.setString(3, ur.getPassword());
        ps1.setString(8, "voter");
        ps1.setString(9, ur.getGender());
        System.out.println(ur.getGender());
       return  ps1.executeUpdate()==1;
    }
    
    
    public static ArrayList<String> getAllUserName()throws SQLException
    {
        ResultSet rs = ps2.executeQuery();
        ArrayList<String> al = new ArrayList<>();
        while(rs.next())
        {
            al.add(rs.getString(1));
        }
        return al;
    }
    
    public static UserDetails getUserByUsername(String adhar_no)throws SQLException
    {
        ps3.setString(1, adhar_no);
        ResultSet rs = ps3.executeQuery();
        UserDetails user = new UserDetails();
        if(rs.next())
        {
            user.setUsername(rs.getString(1));
            user.setCity(rs.getString(2));
            user.setMobile(rs.getLong(3));
            user.setEmail(rs.getString(4));
            user.setAddress(rs.getString(5));
        }
        return user;
        
    }
    
    
    public static boolean updateUser(UserDetails user)throws SQLException
    {
        ps4.setString(1, user.getCity());
        ps4.setString(2, user.getEmail());
        ps4.setLong(3, user.getMobile());
        ps4.setString(4, user.getAddress());
        ps4.setString(5, user.getUserid());
        return ps4.executeUpdate()==1;
    }
    public static boolean deleteUser(String adhar_no)throws SQLException
    {
        ps5.setString(1,adhar_no);
        System.out.println("registration dao the string setted is "+adhar_no);
        int i = ps5.executeUpdate();
        System.out.println("in registration dao we get value "+i);
        return i!=0;
    }
    
}

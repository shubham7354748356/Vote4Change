/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import evoting.dto.User;

/**
 *
 * @author india
 */
public class LoginDao 
{
    private static PreparedStatement ps;
    static
    {
        try
        {
        ps = DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
        }
        catch(SQLException sq)
        {
            sq.printStackTrace();
        }
    }
    public static String validateUser(User user)throws SQLException
    {
        ps.setString(1, user.getUsername());
        ps.setString(2 ,user.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            return rs.getString(1);
        }
        return null;
        
    }
    
}

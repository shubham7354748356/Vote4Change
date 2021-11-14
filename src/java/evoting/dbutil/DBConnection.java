/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author india
 */
public class DBConnection 
{
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-KPOQTE2:1521/XE" , "evoting" , "evoting");
        }
          catch(ClassNotFoundException cnf)
          {
                        cnf.printStackTrace();
                                }
         catch(SQLException sql)
           {
                        sql.printStackTrace();
                    }
        
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        if(conn!=null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException sql)
            {
                sql.printStackTrace();
            }
        }
    }
    
}

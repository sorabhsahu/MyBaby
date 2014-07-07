

package com.connection;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectDB
{
    static Connection con;
    public static Connection connect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://112.196.38.250:3306/api", "arcadian1", "mysql");
        }
        catch(Exception e)
        {
            System.out.println("Exception 1 "+e);
        }
        return con;
    }
}


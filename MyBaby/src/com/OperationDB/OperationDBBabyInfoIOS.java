package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBBabyInfoIOS {
	Connection con;
    Statement stm;
    ResultSet re;
    public OperationDBBabyInfoIOS() {
    	try
        {
            con=ConnectDB.connect();
            stm=con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("Exception 2 "+e);
        }
	}
    public BabyBean babyInfo(BabyBean bean)
    {
 	   try
 	   	{	 	   
	   		 PreparedStatement ps=con.prepareStatement("insert into babyinfo( name, age, relation, image, email) values(?,?,?,?,?)");
	   		 ps.setString(1, bean.getBname());
	   		 ps.setString(2, bean.getAge());
	   		 ps.setString(3, bean.getRelation());
	         ps.setString(4, bean.getImagename());
	         ps.setString(5, bean.getEmail());
	         int k=ps.executeUpdate();
	         if(k>0)
	         {
	        	 
	        	 bean.setStatus(k);
	         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("ios babyinfo connection failed"+e);
 		  bean.setStatus(0);
 	   }
 	  finally
	   {
         if(con!=null)
         {
           try 
           {
             con.close();
           } catch (SQLException e)
           {
             e.printStackTrace();
           }
         }
       }
 	   return bean;
    }

}

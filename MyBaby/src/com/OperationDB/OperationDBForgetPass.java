package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.bean.BabyBean;
import com.connection.ConnectDB;
public class OperationDBForgetPass {

    Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBForgetPass() {
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
    public BabyBean forgetPass(BabyBean bbean)
    {
 	   int i=0;
 	   String uuid = UUID.randomUUID().toString();
 	   String randm=uuid.substring(uuid.lastIndexOf("-") + 1);
 	   bbean.setRandom(randm);
 	   bbean.setPassword(randm);
 	   try{
 		   PreparedStatement ps=con.prepareStatement("update register set password=? where email=?");
 		   ps.setString(1, bbean.getPassword());
		   ps.setString(2, bbean.getEmail());
 		   i=ps.executeUpdate();
 		   if(i>0)
 		   {
 			   bbean.setStatus(i);  
 		   }
 		   else
 		   {
 			   bbean.setStatus(i);
 		   }
 	   }
 	   catch(Exception e)
 	   {
 		   System.out.print(e);
 	   }
 	   finally
  	   {
 		   
 		   if(con!=null)
 		   {
 			   try 
 			   {
 				   con.close();
 			   }
 			   catch (SQLException e)
 			   {
 				   e.printStackTrace();
 			   }
 		   }
  	   }
 	   return bbean;
    }
}

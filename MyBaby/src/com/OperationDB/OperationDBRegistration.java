package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBRegistration {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBRegistration() {
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
    public BabyBean getRegister(BabyBean bean)
    {
    	BabyBean bean2=new BabyBean();
    	try
    	{
    		int i=0;
    		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?)");
    		ps.setString(1, bean.getName());
    		ps.setString(2, bean.getEmail());
    		ps.setString(3, bean.getPassword());
    		i=ps.executeUpdate();
    		if(i>0)
    		{
    			bean2.setStatus(1);
    			bean2.setStatusmsg("Registration successful");
    			return bean2;
    		}
    		else
    		{
    			bean2.setStatus(0);
    			bean2.setStatusmsg("Registration unsuccessful");
    		}
    	}
    	catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
    	{
    		bean2.setStatus(0);
			bean2.setStatusmsg("already exist");
    	}
    	catch(SQLTimeoutException e)
    	{
    		bean2.setStatus(0);
			bean2.setStatusmsg("Registration Failed TimeOut");
    	}
    	catch(SQLException e)
    	{
    		bean2.setStatus(0);
			bean2.setStatusmsg(" Registration Failed Syntex in correct");
			System.out.println("Exception in register "+e);
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
    	return bean2;
    }
}

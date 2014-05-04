package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBBabyUpdateIOS {
	Connection con;
    Statement stm;
    ResultSet re;
    public OperationDBBabyUpdateIOS() {
	
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
	   		 PreparedStatement ps=con.prepareStatement("update babyinfo,newstoragedb  set  babyinfo.name=?, babyinfo.age=?, babyinfo.dob=?,"
	   		 											+ "babyinfo.relation=?, babyinfo.image=?,newstoragedb.bname=? where babyinfo.baby_id=?"
	   		 											+ "and newstoragedb.baby_id=?");
	   		 ps.setString(1, bean.getBname());
	   		 ps.setString(2, bean.getAge());
	   		 ps.setString(3, bean.getDob());
	   		 ps.setString(4, bean.getRelation());
	         ps.setString(5, bean.getImagename());
	         ps.setString(6, bean.getBname());
	         ps.setString(7,bean.getBaby_id()+"");
	         ps.setString(8, bean.getBaby_id()+"");
	         int k=ps.executeUpdate();
	         if(k>0)
	         {
	        	 bean.setStatus(1);
	         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("ios babyinfo update "+e);
 	   }
 	   
 	   
 	   return bean;
    }
    
    
    
    
    public BabyBean babyUpdate(BabyBean bean)
    {
 	   try
 	   	{	 	   
	   		 PreparedStatement ps=con.prepareStatement("update babyinfo set name=?, age=?, dob=?, relation=? where baby_id=?");
	   		 ps.setString(1, bean.getBname());
	   		 ps.setString(2, bean.getAge());
	   		 ps.setString(3, bean.getDob());
	   		 ps.setString(4, bean.getRelation());
	         ps.setInt(5, bean.getBaby_id());
	         int k=ps.executeUpdate();
	         if(k>0)
	         {
	        	 bean.setStatus(k);
	         }	
 	   	}
 	   catch(Exception e)
 	   {
 		   System.out.print("ios babyinfo update "+e);
 	   }
 	   return bean;
    }

}

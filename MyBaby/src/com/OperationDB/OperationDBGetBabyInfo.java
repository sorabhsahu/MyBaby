package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Contexts.AllPath;
import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBGetBabyInfo 
{
	 Connection con;
	    Statement stm;
	    ResultSet re,re1;
	    public OperationDBGetBabyInfo() 
	    {
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
	    
	    public BabyBean getIn(BabyBean bean)
	    {
	    	int status=0;
	        try
	        {
	            String query="select babyinfo.name, babyinfo.age,babyinfo.dob, babyinfo.relation, babyinfo.image,"
	            		+ " register.name, babyinfo.baby_id from babyinfo , register"
	            		+ " where babyinfo.email='"+bean.getEmail()+"'  and  register.email='"+bean.getEmail()+"'";                         
	            re=stm.executeQuery(query);
	            while(re.next())
	            {
	            	status=1;
	            	bean.setBname(re.getString(1));
	            	bean.setAge(re.getString(2));
	            	bean.setDob(re.getString(3));
	            	bean.setRelation(re.getString(4));
	                bean.setImgpath( AllPath.getFinalURL()+re.getString(5));
	            	bean.setName(re.getString(6));
	            	bean.setBaby_id(re.getInt(6));
	            	bean.setStatus(status);
	            }   
	        }
	        catch(Exception e)
	        {
	            System.out.println("Exception 3 "+e);
	        }
	        return bean;
	    }
}

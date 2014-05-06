package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBUsersBaby {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    int count=0;
    public OperationDBUsersBaby() 
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
    public JSONObject getAll(BabyBean bean)
    {
    	try
        {  
    		String query= "select name from babyinfo where  email='"+bean.getEmail()+"'";
    		re=stm.executeQuery(query);
            while(re.next())
            {     
            	count++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception users baby count "+e);
        }
    	JSONObject ab = new JSONObject();
    	BabyBean beanarray[]=new BabyBean[count];
    	int i=0;
    	try
    	{
    		String query= "select name, baby_id from babyinfo where  email='"+bean.getEmail()+"'";
    		re1=stm.executeQuery(query);
            while(re1.next())
            {
            	int status=1;
            	beanarray[i]=new BabyBean();
            	beanarray[i].setBname(re1.getString(1));
            	beanarray[i].setBaby_id(re1.getInt(2));
            	beanarray[i].setStatus(status);
            	i++;
            } 
            ab.put("result", beanarray);
            if(count>0)
            {
            		ab.put("Status",1);
            }
            else
            {
            	ab.put("Status",0);
            }
		}
    	catch (Exception e) 
    	{
    		
    		System.out.println(" exceptions in all baby get "+ e);
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
    	return ab;
    }

}

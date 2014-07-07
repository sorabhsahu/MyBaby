package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import com.Contexts.AllPath;
import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBGetAllData {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    int count=0;
    public OperationDBGetAllData()
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
    public JSONObject getIn(BabyBean bean)
    {
    	try
        {  
            String query="select * from newstorageDB where email='"+bean.getEmail()+"'";
            //String query="select * from newstorageDB where email='"+bean.getEmail()+"' and bname ='"+bean.getBname()+"'";
            re=stm.executeQuery(query);
            while(re.next())
            {     
            	count++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception getAllData new count "+e);
        }
    	JSONObject ab = new JSONObject();
    	BabyBean beanarray[]=new BabyBean[count];

    	int i=0;
        try
        {
        	String query="select movement, image, atribute, type, date,bname from newstorageDB where email='"+bean.getEmail()+"'";
            re=stm.executeQuery(query);
            while(re.next())
            {
            	beanarray[i]=new BabyBean();
            	beanarray[i].setMoments(re.getString(1));
            	if(re.getString(2).contains("."))
            	{
            		beanarray[i].setImgpath( AllPath.getFinalURL()+re.getString(2));
            	}
            	else
            	{
            		beanarray[i].setImgpath(AllPath.getFinalURL()+"text_icon.png");
            	}
            	beanarray[i].setDesc(re.getString(3));
            	beanarray[i].setType(re.getString(4));
            	beanarray[i].setDate(re.getString(5));
            	beanarray[i].setBname(re.getString(6));
            	beanarray[i].setStatus(1);
            	i++;
            }   
            ab.put("result", beanarray);
            if(count>0)
            {
           		ab.put("status",1);
            }
            else
            {
            	ab.put("status",0);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in get image "+e);
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
        return ab;
    }

}

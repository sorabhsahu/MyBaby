package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import com.Contexts.AllPath;
import com.bean.BabyBean;
import com.connection.ConnectDB;
public class OperationDBGetAllBabay {

    Connection con;
    Statement stm;
    ResultSet re,re1;
    int count=0;
    public OperationDBGetAllBabay()
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
    		String query= "select count(name) from babyinfo where  email='"+bean.getEmail()+"'";
    		re=stm.executeQuery(query);
            while(re.next())
            {     
            	count=re.getInt(1);
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception searchidea coun getall baby "+e);
        }
    	JSONObject ab = new JSONObject();
    	BabyBean beanarray[]=new BabyBean[count];
    	int i=0;
    	try
    	{
    		String query= "select babyinfo.name, babyinfo.age,babyinfo.dob, babyinfo.relation,"
    				+ " babyinfo.image, register.name , babyinfo.baby_id from babyinfo , register where"
    				+ " babyinfo.email='"+bean.getEmail()+"'  and  register.email='"+bean.getEmail()+"' ";
    		re1=stm.executeQuery(query);
            while(re1.next())
            {
            	int status=1;
            	beanarray[i]=new BabyBean();
            	beanarray[i].setBname(re1.getString(1));
            	beanarray[i].setAge(re1.getString(2));
            	beanarray[i].setDob(re1.getString(3));
            	beanarray[i].setRelation(re1.getString(4));
            	beanarray[i].setImgpath( AllPath.getFinalURL()+re1.getString(5));
            	beanarray[i].setName(re1.getString(6));
            	beanarray[i].setBaby_id(re1.getInt(7));
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

package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

import com.Contexts.AllPath;
import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBSearchMovements {

	
	Connection con;
    Statement stm;
    ResultSet re;
    int count=0;
    public OperationDBSearchMovements() {
		
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
    public JSONObject select(BabyBean bean)
    {
    	try 
    	{
    		String query="select  image, atribute,type from newstorageDB where (email='"+bean.getEmail()+"' and bname='"+bean.getBname()+"') and ( date='"+bean.getDate()+"' and movement='"+bean.getMoments()+"')";                         
            re=stm.executeQuery(query);
            while(re.next())
            {
            	count++;
            }  
		} 
    	catch (Exception e)
    	{
    		System.out.println("exception in count search movement "+e);
		}
    	JSONObject ab = new JSONObject();
    	BabyBean beanarray[]=new BabyBean[count];
    	int i=0;
        try
        {
            String query="select  image, atribute,type from newstorageDB where (email='"+bean.getEmail()+"' and bname='"+bean.getBname()+"') and ( date='"+bean.getDate()+"' and movement='"+bean.getMoments()+"')";
            re=stm.executeQuery(query);
            while(re.next())
            {
            	beanarray[i]=new BabyBean();
            	beanarray[i].setImgpath( AllPath.getFinalURL()+re.getString(1));
            	beanarray[i].setDesc(re.getString(2));
            	beanarray[i].setType(re.getString(3));
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
              } catch (SQLException e)
              {
                e.printStackTrace();
              }
            }
    	  }
        return ab;

    
    }
}

package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONObject;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class TempGetFiles {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    int count;
    public TempGetFiles() {
		
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
    	JSONObject ab = new JSONObject();
    	ArrayList<String> arraylist=new ArrayList<String>();
    	try
        {  
            String query=" select date from imagestore where email ='"+bean.getEmail()+"' group by date;";     
           
            re=stm.executeQuery(query);
            while(re.next())
            {     
            	arraylist.add(re.getString(1));
            	
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in select date coun "+e);
        }
    	for(int loop =0; loop<arraylist.size();loop++)
    	{
    		int i=0;
    		count=0;
    		System.out.print(arraylist.get(loop));
    		try
            {  
                String query="select * from imagestore where email='"+bean.getEmail()+"'  and date='"+arraylist.get(loop)+"' ";     
                re=stm.executeQuery(query);
                while(re.next())
                {     
                	count++;
                }
            }
            catch(Exception e)
            {
                System.out.println("Exception imageget coun "+e);
            }
        	
    		BabyBean beanarray[]=new BabyBean[count];
    		try
	        {   
	            String query="select * from imagestore where email='"+bean.getEmail()+"' and date='"+arraylist.get(loop)+"'";                         
	            re=stm.executeQuery(query);
	            while(re.next())
	            {
	            	beanarray[i]=new BabyBean();
	            	beanarray[i].setImgpath("http://112.196.38.250:8080/MyBaby/image/"+re.getString(1));
	            	beanarray[i].setDesc(re.getString(2));
	            	beanarray[i].setType(re.getString(3));
	            	beanarray[i].setDate(re.getString(4));
	            	beanarray[i].setEmail(re.getString(5));
	            	beanarray[i].setStatus(1);
	            	i++;
	            }   
	            ab.put(loop+"", beanarray);
	        }
	        catch(Exception e)
	        {
	            System.out.println("Exception in get image "+e);
	        }
    	}
    	
    	try
    	{
    		if(count>0)
            {
            		ab.put("Status",1);
            		ab.put("count", arraylist.size());
            }
            else
            {
            	ab.put("Status",0);
            }
		}
    	catch (Exception e) 
    	{
		
		}
        return ab;
    }
}
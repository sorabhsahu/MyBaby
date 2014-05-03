package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.codehaus.jettison.json.JSONObject;

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
        	String query="select movement, image, atribute, type, date from newstorageDB where email='"+bean.getEmail()+"'";
            //String query="select movement, image, atribute, type, date from newstorageDB where email='"+bean.getEmail()+"' and bname='"+bean.getBname()+"'";                         
            re=stm.executeQuery(query);
            while(re.next())
            {
            	beanarray[i]=new BabyBean();
            	beanarray[i].setMoments(re.getString(1));
            	beanarray[i].setImgpath("http://112.196.38.250:8080/MyBaby/image/"+re.getString(2));
            	beanarray[i].setDesc(re.getString(3));
            	beanarray[i].setType(re.getString(4));
            	beanarray[i].setDate(re.getString(5));
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
        return ab;
    }

}

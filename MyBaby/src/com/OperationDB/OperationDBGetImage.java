package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.codehaus.jettison.json.JSONObject;
import com.bean.BabyBean;
import com.connection.ConnectDB;
public class OperationDBGetImage {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    int count=0;
    public OperationDBGetImage() {
		// TODO Auto-generated constructor stub
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
            String query="select * from imagestore where email='"+bean.getEmail()+"'";     
            re=stm.executeQuery(query);
            while(re.next())
            {     
            	count++;
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception searchidea coun "+e);
        }
    	JSONObject ab = new JSONObject();
    	BabyBean beanarray[]=new BabyBean[count];

    	int i=0;
        try
        {
        	
            String query="select * from imagestore where email='"+bean.getEmail()+"'";                         
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

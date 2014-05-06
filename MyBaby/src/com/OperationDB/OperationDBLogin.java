package com.OperationDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBLogin {
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBLogin()
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
    public BabyBean getIn(String email,String password)
    {
    	BabyBean bean=new BabyBean();
    	int status=0;
        try
        {
            String query="select name, email from register where (email='"+email+"'or name='"+email+"')and password='"+password+"'";
            re=stm.executeQuery(query);
            while(re.next())
            {
            	status++;
            	bean.setStatus(status);
            	bean.setName(re.getString(1));
            	bean.setEmail(re.getString(2));
            	bean.setBabycheck("no");
            }
            if(status>=1)
            {
            	String query1="select email from babyinfo where email='"+email+"'";
                re=stm.executeQuery(query1);
                while(re.next())
                {
                	bean.setBabycheck("yes");
                }
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Exception 3 "+e);
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
        return bean;
    }
   
}

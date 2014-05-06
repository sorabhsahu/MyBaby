package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBPaypal {
	
	Connection con;
    Statement stm;
    ResultSet re;
    public OperationDBPaypal() {
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
    
    public BabyBean insert(BabyBean bean)
    {
    	 try
  	   	{	 	   
 	   		 PreparedStatement ps=con.prepareStatement("insert into paypal_db values(?,?,?)");
 	   		 ps.setString(1, bean.getBname());
 	   		 ps.setString(2, bean.getEmail());
 	   		 ps.setString(3, bean.getConform() );
 	         int k=ps.executeUpdate();
 	         if(k>0)
 	         {
 	        	 
 	        	 bean.setStatus(k);
 	         }	
  	   	}
  	   catch(Exception e)
  	   {
  		   System.out.print("paypal connection failed"+e);
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
    
    public BabyBean check(BabyBean bean )
    {
    	
    	try
    	{
    		String query="select conform from paypal_db where name='"+bean.getBname()+"' and email='"+bean.getEmail()+"'";
    		re=stm.executeQuery(query);
    		 while(re.next())
             {
             	bean.setStatus( Integer.parseInt(  re.getString(1)));
             	
             }
    		
		}
    	catch (Exception e) 
		{
    		System.out.print("paypal select failed"+e);
		
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

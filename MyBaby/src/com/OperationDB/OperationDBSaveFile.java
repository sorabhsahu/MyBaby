package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBSaveFile {
	
	Connection con;
    Statement stm;
    ResultSet re,re1;
    public OperationDBSaveFile() {
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
	 public BabyBean save(BabyBean bean)
	    { String stt="";
		 BabyBean bean1=new BabyBean();
		
	 	   try
	 	   	{
	 		   
	 		  String query="select desc from imagestore where date='"+bean.getDate() +"' and email='"+bean.getEmail()+"' ";
	            re=stm.executeQuery(query);
	            while(re.next())
	            {
	            	if(re.getString(1)!=bean.getDesc())
	            	{
	            		stt="yes";
	            	}
	            	
	            }
	           
	 		   
	 		   if(stt =="yes")
	 		   {
	 			   
	 			  bean1.setStatus(0);
	 			  return bean;
	 			   
	 		   }
	 		   else
	 		   {
	 			     PreparedStatement pstm=con.prepareStatement("insert into imagestore values(?,?,?,?,?)");
	 	    	     pstm.setString(1, bean.getImagename());
	 		         pstm.setString(2, bean.getDesc());
			         pstm.setString(3, bean.getType());
			         pstm.setString(4,bean.getDate());
			         pstm.setString(5,bean.getEmail());
			         int k=pstm.executeUpdate();
			         if(k>0)
			         {
			        	 bean1.setStatus(1);
			         }	
	 		   }
	 		       
	 	   	}
	 	   catch(Exception e)
	 	   {
	 		   System.out.print("exception in uploadimg"+e);
	 	   }
	 	   return bean1;
	    }
}

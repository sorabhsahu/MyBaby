package com.OperationDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bean.BabyBean;
import com.connection.ConnectDB;

public class OperationDBAddTest {
	 Connection con;
	    Statement stm;
	    ResultSet re;
	    
	    public OperationDBAddTest() {
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
	    public BabyBean set(BabyBean bean)
	    {
	    	BabyBean bean1=new BabyBean();
	    	int count=0;
	    	try
	    	{
				String query="select count(bname) from newstorageDB;";
				re=stm.executeQuery(query);
				while(re.next())
				{
					count=count+re.getInt(1);
				}
				
				
			} 
	    	catch (Exception e) 
	    	{
	    		System.out.println("Exception in count text "+e);
			
			}
	    	
	    	try 
	    	{
	    		PreparedStatement pstm=con.prepareStatement("insert into newstorageDB values(?,?,?,?,?,?,?)");
	    	     pstm.setString(1, bean.getBname());
		         pstm.setString(2, bean.getMoments());
		         pstm.setString(3, "no image"+count);
		         pstm.setString(4,bean.getDesc());
		         pstm.setString(5,bean.getType());
		         pstm.setString(6,bean.getDate());
		         pstm.setString(7,bean.getEmail());
		         int k=0;
		         k=pstm.executeUpdate();
		         if(k>0)
		         {
		        	 bean1.setStatus(1);
		         }	
		    	
			} catch (Exception e) {
			}
	    	return bean1;
	    }
	    
}

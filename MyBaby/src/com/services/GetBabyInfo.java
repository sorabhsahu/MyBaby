package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBGetBabyInfo;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/getkid")
public class GetBabyInfo {
	@POST   //by sorabh 
	@Path("/getbabyinfo")  
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getbabay(@FormParam("email")String email)
	{
		Gson gson=new Gson();
		OperationDBGetBabyInfo op=new OperationDBGetBabyInfo(); 
		BabyBean bean=new BabyBean();
		BabyBean bean2=new BabyBean();
		bean.setEmail(email);
		bean2= op.getIn(bean);
		return gson.toJson(bean2);
	}
	
}

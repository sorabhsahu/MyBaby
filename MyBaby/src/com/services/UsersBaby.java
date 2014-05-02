package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBUsersBaby;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/selectbaby")
public class UsersBaby {
	@POST
	@Path("/moments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String allBaby(@FormParam("email") String email)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		OperationDBUsersBaby op=new OperationDBUsersBaby();
		
		return gson.toJson(op.getAll(bean));
	}
	

}

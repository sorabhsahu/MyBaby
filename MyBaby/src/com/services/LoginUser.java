package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBLogin;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/login")
public class LoginUser {
	@POST
	@Path("/checklogin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String selectData(@FormParam("email")String email,@FormParam("password")String password)
	{
		Gson gson=new Gson();
		OperationDBLogin op=new OperationDBLogin();
		BabyBean bean=new BabyBean();
		if(email.isEmpty()||password.isEmpty())
		{
			bean.setStatus(0);
			bean.setStatusmsg("please enter all values");
			return gson.toJson(bean);
		}
		else
		{
			bean= op.getIn(email,password);
		}
		return gson.toJson(bean);
	}
}

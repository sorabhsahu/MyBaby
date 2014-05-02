package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBRegistration;
import com.bean.BabyBean;
import com.google.gson.Gson;


@Path("/newuser")
public class Registration {
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getReg(@FormParam("name") String name,@FormParam("email") String email,@FormParam("password") String password,@FormParam("cpassword") String cpassword)
	{    
		BabyBean bean=new BabyBean();
		BabyBean bean2=new BabyBean();
		Gson gson=new Gson();
		bean.setName(name);
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setCpassword(cpassword);
		OperationDBRegistration db=new OperationDBRegistration();
		bean2=db.getRegister(bean);
		return gson.toJson(bean2);
	}
}

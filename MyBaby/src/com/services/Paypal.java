package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBPaypal;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/pay")
public class Paypal {
	@POST
	@Path("/pal")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String pay(@FormParam("email")String email,@FormParam("name")String name,@FormParam("conform")String conform)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setBname(name);
		bean.setEmail(email);
		bean.setConform(conform);
		OperationDBPaypal op=new OperationDBPaypal();
		BabyBean bean1=new BabyBean();
		bean1=op.insert(bean);
		BabyBean bean2=new BabyBean();
		bean2.setStatus(bean1.getStatus());
		return gson.toJson(bean2);
	}
	@POST
	@Path("/check")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String cheack(@FormParam("email")String email,@FormParam("name")String name)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setBname(name);
		bean.setEmail(email);
		OperationDBPaypal op=new OperationDBPaypal();
		BabyBean bean1=new BabyBean();
		bean1=op.check(bean);
		BabyBean bean2=new BabyBean();
		bean2.setStatus(bean1.getStatus());
		return gson.toJson(bean2);
	}


}

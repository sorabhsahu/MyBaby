package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.Mail;
import com.OperationDB.OperationDBForgetPass;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/forget")
public class FogetPassword {
	@POST
	@Path("/pass")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String forgetPassword(@FormParam("email")String email)
	{
		Gson gson=new Gson();
		OperationDBForgetPass op=new OperationDBForgetPass();
		BabyBean bean=new BabyBean();
		BabyBean bean2=new BabyBean();
		BabyBean bean3=null;
		BabyBean bean4=null;
		bean.setEmail(email);
		bean2= op.forgetPass(bean);
		if(bean2.getStatus()>=1)
		{
			Mail mail=new Mail();
			bean3=new BabyBean();
			bean3=mail.get(bean2);
			bean4=new BabyBean();
			bean4.setStatus(bean3.getStatus());
		}
		return gson.toJson(bean4);
	}

}

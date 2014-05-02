package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.OperationDB.OperationDBAddTest;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/babytext")
public class BabyText 
{
	@POST
	@Path("/data")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String Data(@FormParam("email")String email,
					   @FormParam("bname")String bname,
					   @FormParam("date")String  date1,
					   @FormParam("moments")String moment,
			           @FormParam("type")String type,
			           @FormParam("desc")String desc)
	{
	 	Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		bean.setType(type);
		bean.setMoments(moment);
		bean.setBname(bname);
		bean.setDesc(desc);
		bean.setDate(date1);
		OperationDBAddTest op=new  OperationDBAddTest();
		BabyBean bean1=new BabyBean();
		bean1=op.set(bean);
		BabyBean bean2=new BabyBean();
		bean2.setStatus(bean1.getStatus());
		return gson.toJson(bean1);
	}

}

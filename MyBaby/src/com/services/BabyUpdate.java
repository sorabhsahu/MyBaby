package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.OperationDB.OperationDBBabyUpdate;
import com.bean.BabyBean;
import com.google.gson.Gson;
@Path("/oldbaby")
public class BabyUpdate {
	@POST
	@Path("/babyupdate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String babyInformation(@FormParam("name")String name,
								 @FormParam("age")String age,
								 @FormParam("dob")String dob,
								 @FormParam("relation")String relation,
								 @FormParam("path")String path,
								 @FormParam("baby_id")String baby_id)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setBname(name);
		bean.setAge(age);
		bean.setDob(dob);
		bean.setRelation(relation);
		bean.setPath(path);
		bean.setBaby_id(Integer.parseInt(baby_id));
		OperationDBBabyUpdate op=new OperationDBBabyUpdate();
		if(path.matches(""))
		{

			bean= op.babyupdate(bean);
		}
		else
		{

			bean= op.babyInfo(bean);
		}
		BabyBean bean3=new BabyBean();
		bean3.setStatus(bean.getStatus());
		return gson.toJson(bean3);
		
	}
}

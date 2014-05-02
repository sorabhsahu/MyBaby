package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.OperationDB.OperationDBGetImage;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/getData")
public class GetImage
{
	@POST
	@Path("/allfile")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String get(@FormParam("email")String email)
	{
		
		Gson gson=new Gson();
		OperationDBGetImage op=new OperationDBGetImage();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		JSONObject aa = new JSONObject();
		aa=op.getIn(bean);
		return gson.toJson(aa);
		
	}
}

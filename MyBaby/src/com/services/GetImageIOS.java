package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import com.OperationDB.TempGetFiles;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/getDataIos")
public class GetImageIOS
{
	@POST
	@Path("/allfileIos")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String get(@FormParam("email")String email)
	{
		Gson gson=new Gson();
		TempGetFiles op=new TempGetFiles();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		JSONObject aa = new JSONObject();
		aa=op.getIn(bean);
		return gson.toJson(aa);
		
	}
}

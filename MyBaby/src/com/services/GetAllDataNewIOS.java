package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import com.OperationDB.OperationDBGetAllDataNewIOS;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/getmomentsIOS")
public class GetAllDataNewIOS {
	@POST
	@Path("/allfileIOS")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String get(@FormParam("email")String email)
	{
		Gson gson=new Gson();
		OperationDBGetAllDataNewIOS op=new OperationDBGetAllDataNewIOS();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		JSONObject aa = new JSONObject();
		aa=op.getIn(bean);
		return gson.toJson(aa);
		
	}
	
}

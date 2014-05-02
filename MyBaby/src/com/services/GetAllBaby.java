package com.services;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.OperationDB.OperationDBGetAllBabay;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/get")
public class GetAllBaby {
	@POST   
	@Path("/allbaby")  
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getAll(@FormParam("email") String email)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		OperationDBGetAllBabay op=new OperationDBGetAllBabay();
		JSONObject jsonObject = new JSONObject();
		jsonObject=op.getAll(bean);		
		return gson.toJson(jsonObject);
	}

}

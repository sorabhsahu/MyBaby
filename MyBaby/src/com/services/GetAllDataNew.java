package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONObject;
import com.OperationDB.OperationDBGetAllData;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/getmoments")
public class GetAllDataNew {
	@POST
	@Path("/allfile")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String get(@FormParam("email")String email,@FormParam("bname") String bname)
	{
		
		Gson gson=new Gson();
		OperationDBGetAllData op= new OperationDBGetAllData();
		BabyBean bean=new BabyBean();
		bean.setEmail(email);
		bean.setBname(bname);
		JSONObject aa = new JSONObject();
		aa=op.getIn(bean);
		return gson.toJson(aa);
	}
}
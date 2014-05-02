package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.OperationDB.OperationDBSearchMovements;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/searchfor")
public class SearchMovements {
	@POST
	@Path("/moments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String movements(@FormParam("bname") String bname,
							@FormParam("moments") String movements, 
							@FormParam("date") String date,
							@FormParam("email") String email)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		bean.setBname(bname);
		bean.setMoments(movements);
		bean.setDate(date);
		bean.setEmail(email);
		OperationDBSearchMovements op=new OperationDBSearchMovements();
		return gson.toJson(op.select(bean));
	}
}

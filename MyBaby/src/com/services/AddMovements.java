package com.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.OperationDB.OperationDBAddMovements;
import com.bean.BabyBean;
import com.google.gson.Gson;

@Path("/addspecial")
public class AddMovements {
	@POST
	@Path("/moments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String movements(@FormParam("bname") String bname,
							@FormParam("moments") String movements,
							@FormParam("mediapath")String imgpath,
							@FormParam("desc") String desc,
							@FormParam("email") String email,
							@FormParam("type")String type,
							@FormParam("date")String date)
	{
		Gson gson=new Gson();
		BabyBean bean=new BabyBean();
		BabyBean bean1=new BabyBean();
		bean.setBname(bname);
		bean.setMoments(movements);
		bean.setImgpath(imgpath);
		bean.setDesc(desc);
		bean.setEmail(email);
		bean.setType(type);
		bean.setDate(date);
		OperationDBAddMovements op=new OperationDBAddMovements();
		bean1=op.addSpecial(bean);
		return gson.toJson(bean1);
	}

}

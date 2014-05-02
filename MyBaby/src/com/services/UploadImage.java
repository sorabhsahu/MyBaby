package com.services;

import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.OperationDB.OperationDBUploadImage;
import com.bean.BabyBean;
import com.google.gson.Gson;


@Path("/upload")
public class UploadImage {
	@POST
	@Path("/androidfile")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String upldImage(@FormParam("mediapath")String imgpath,
							@FormParam("desc") String desc,
							@FormParam("email") String email,
							@FormParam("type")String type)
	{
	    Calendar cal=Calendar.getInstance();
	 	String date=cal.get(Calendar.MONDAY)+"-"+cal.get(Calendar.DATE)+"-"+cal.get(Calendar.YEAR);
	 	BabyBean bean=new BabyBean();
	 	BabyBean bean2=new BabyBean();
		Gson gson=new Gson();
		bean.setImgpath(imgpath);
		bean.setDesc(desc);
		bean.setType(type);
		bean.setDate(date);
		bean.setEmail(email);
		OperationDBUploadImage oDb=new OperationDBUploadImage();
		bean2=oDb.uploadImg(bean);
		return gson.toJson(bean2);
	}
}

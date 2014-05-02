package com.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.OperationDB.OperationDBUploadImageIOS;
import com.bean.BabyBean;
import com.google.gson.Gson;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/iosfile")
public class UploadImageIOS {
	@POST 
	@Path("/iosuploadimage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(
			@FormDataParam("desc")String desc,
			@FormDataParam("type")String type,
			@FormDataParam("email")String email,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
	{
		Calendar cal=Calendar.getInstance();
	 	String time= cal.get(Calendar.HOUR)+""+cal.get(Calendar.MINUTE)+""+cal.get(Calendar.SECOND)+""+cal.get(Calendar.MILLISECOND);
	 	String date=cal.get(Calendar.MONDAY)+"-"+cal.get(Calendar.DATE)+"-"+cal.get(Calendar.YEAR);
 		 String uploadedFileLocation = "/usr/local/apache-tomcat-7.0.53/webapps/MyBaby/image/"+date+time
 				+ fileDetail.getFileName();
 	 	writeToFile(uploadedInputStream, uploadedFileLocation);
 	 	Gson gson=new Gson();
 	 	BabyBean bean=new BabyBean();
	 	bean.setImagename(date+time+fileDetail.getFileName());
		bean.setDesc(desc);
		bean.setType(type);
		bean.setDate(date);
		bean.setEmail(email);
		OperationDBUploadImageIOS op=new OperationDBUploadImageIOS();
		BabyBean bean2=new BabyBean();
		bean2=op.uploadImg(bean);
	    return gson.toJson(bean2);
	}
	
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	
}
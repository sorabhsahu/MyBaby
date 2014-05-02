package com.OperationDB;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bean.BabyBean;

public class Mail 
{
	public BabyBean get(BabyBean bean)
	{
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	            "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	
	    Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() 
	    {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("mybabayorg@gmail.com","arcadian123");
            }
        });

    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("mybabayorg@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(bean.getEmail()));
	        message.setSubject("SFTS MAIL TESING");
	        message.setText("Dear MyBaby account holder," +
	                "\n\n This is an automated email, please don’t reply"
	                + " \n\n To recover your sign-in details for MyBaby"
	                + " \n\n please enter this password  "+bean.getRandom()+"   !"
	                + "  ");
	
	        Transport.send(message);
	        bean.setStatus(1);
    }
    catch (MessagingException e) 
    {
    	  bean.setStatus(0);
       // throw new RuntimeException(e);
    }
    return bean;
}
}
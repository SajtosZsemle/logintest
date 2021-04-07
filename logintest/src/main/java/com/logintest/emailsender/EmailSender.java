package com.logintest.emailsender;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {

	

	public static void sendEmail(String to) throws AddressException, MessagingException {
		
		/** Mail info */
	//	String to = "sajtoszsemle0@gmail.com";
		String from = "admin@sajt.hu";
		String host = "localhost";
		
	
		/** SMTP Server */
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getInstance(properties, null);
		
		
		/** Message */
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Registration");
		message.setText("Sikeres regisztráció");
		
		
		/** Send email */
		Transport.send(message);
		
		
	}
	  }	

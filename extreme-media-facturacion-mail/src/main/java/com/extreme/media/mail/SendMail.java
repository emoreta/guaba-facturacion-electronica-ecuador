package com.extreme.media.mail;



import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {
	
	public void SendMailDocumento(String rutaPdf,String nombrePdf,String rutaXml,String nombrexml,String correo,String usernameE,String passwordE,String host,String port,String correoSubject) throws MessagingException{
		/*final String username = "edison.moreta@guaba.ec";
	    final String password = "edison.moreta";*/

		final String username = usernameE;
	    final String password = passwordE;
	    
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    //props.put("mail.smtp.host", "mail.guaba.ec");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    //props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	  

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(correoSubject));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(correo));
	        message.setSubject("FACTURACION ELECTRONICA");
	        message.setText("Facturación Electronica");

	       MimeBodyPart messageBodyPart = new MimeBodyPart();

	        Multipart multipart = new MimeMultipart();

	        messageBodyPart = new MimeBodyPart();
	        
	        
	        
	        addAttachment(multipart,rutaPdf);
	        addAttachment(multipart,rutaXml);
	        
	        message.setContent(multipart);

	        System.out.println("Sending");

	        Transport.send(message);

	        System.out.println("Done");

	    
	}
	
	private static void addAttachment(Multipart multipart, String filename) throws MessagingException
	{
	    DataSource source = new FileDataSource(filename);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}

}

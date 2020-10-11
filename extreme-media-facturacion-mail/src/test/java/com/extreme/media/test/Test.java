package com.extreme.media.test;

import javax.mail.MessagingException;

import com.extreme.media.mail.SendMail;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendMail sm=new SendMail();
		//sm.SendMailDocumento("C:\\XMLPRUEBA\\0607201801171892136200110010010000002041234567817.pdf","0607201801171892136200110010010000002041234567817.pdf","C:\\XMLPRUEBA\\0607201801171892136200110010010000002041234567817_Sign.xml","0607201801171892136200110010010000002041234567817_Sign.xml","","");
		try {
			sm.SendMailDocumento("C:\\FAC\\xml\\res\\1010201801171892136200110010010000060091234567813.pdf","1010201801171892136200110010010000060061234567817.pdf","C:\\FAC\\xml\\res\\1010201801171892136200110010010000060391234567817_Sign.xml","1010201801171892136200110010010000060061234567817_Sign.xml","edixavi@hotmail.com","edison.moreta@guaba.ec","edison.moreta","mail.guaba.ec","587","edixavi@hotmail.com");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//sm.SendMailDocumento(rutaPdf, nombrePdf, rutaXml, nombrexml, correo, usernameE, passwordE, host, port, correoSubject);
	}

}

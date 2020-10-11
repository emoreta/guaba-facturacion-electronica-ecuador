package com.extreme.media;

import java.io.IOException;

import com.extreme.media.ftp.Client;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c=new Client();
		try {
			//c.ConexionFtp("C:\\firmasEnviar\\pdf\\facturaPdf.pdf","EDISON","pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

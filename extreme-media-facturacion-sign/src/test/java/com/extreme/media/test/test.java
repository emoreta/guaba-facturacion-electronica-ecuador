package com.extreme.media.test;



import core.XAdESBESSignature;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String xmlPath = "C:\\firmasEnviar\\XML_nuevaestructura_actual6.xml";
		String pathSignature = "C:\\FIRMA ELECTRONICA\\edison_xavier_moreta_escobar.p12";
		String passSignature = "Sistemas2011";
		String pathOut = "C:\\firmasEnviar";
		String nameFileOut = "factura_sign_nueva_7.xml";

//		String pathSignature = args[0];
//		String passSignature = args[1];
//		String xmlPath = args[2];
//		String pathOut = args[3];
//		String nameFileOut = args[4];

		System.out.println("Ruta del XML de entrada: " + xmlPath);
		System.out.println("Ruta Certificado: " + pathSignature);
		System.out.println("Clave del Certificado: " + passSignature);
		System.out.println("Ruta de salida del XML: " + pathOut);
		System.out.println("Nombre del archivo salido: " + nameFileOut);

		try {
			XAdESBESSignature.firmar(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		

	}

}

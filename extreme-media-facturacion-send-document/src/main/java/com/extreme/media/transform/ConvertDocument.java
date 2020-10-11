package com.extreme.media.transform;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.extreme.media.util.Common;

public class ConvertDocument {
	 Common ut=new Common();
	public String transformarDocumento(String path, String sec,String nombreArchivo,long secGr) throws ParserConfigurationException, SAXException, IOException{
					
		org.w3c.dom.Document doc = ut.getDoc(path);
		
		return nombreArchivo;
		 
	 }
	
	

}

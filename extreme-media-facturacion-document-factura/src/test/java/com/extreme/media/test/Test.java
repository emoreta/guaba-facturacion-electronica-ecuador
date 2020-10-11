package com.extreme.media.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.extreme.media.struct.Struct;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Struct s=new Struct();
		//s.StructFactura();
		
		//File file = new File("c:\\newfile.xml");
		BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\XMLPRUEBA\\newfile.xml"));
	    
		
		//writer.write(s.StructFactura());
	     
	    writer.close();
	}

}

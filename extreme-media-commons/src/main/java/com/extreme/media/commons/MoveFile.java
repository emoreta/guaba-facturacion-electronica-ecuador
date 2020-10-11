package com.extreme.media.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveFile {
	
	public void FileTranslate(String pathOrigen,String fichero,String pathDestino) throws IOException{
		//String pathOrigen ="C:\\XMLPRUEBA\\";
	    //String fichero = args[1];
	    //String pathDestino = pathOrigen + "backup\\"; // Define aqui tu directorio destino
	    File ficheroCopiar = new File(pathOrigen, fichero);
	    File ficheroDestino = new File(pathDestino, fichero);
	    if (ficheroCopiar.exists()) {
	        Files.copy(Paths.get(ficheroCopiar.getAbsolutePath()), Paths.get(ficheroDestino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
	    } else {
	        System.out.println("El fichero "+fichero+" no existe en el directorio "+pathOrigen);
	    }
		
	}

}

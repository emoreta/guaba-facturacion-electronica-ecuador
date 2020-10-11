package com.extreme.media.ftp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.w3c.dom.Document;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.entity.FileTranferNcE;
import com.extreme.media.entity.FilesTranfer;

public class Client {

public void ConexionFtp(List<FilesTranfer> infoArchivo) throws IOException
{
	
	Map<String, String> pathConfig = new HashMap<String, String>();
	Document ed;
	ReadXml rx = new ReadXml();
	Constants constants = new Constants();
	ed = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
    pathConfig=rx.readXmlMapAppConfig(ed);
	String cadenaftp = pathConfig.get("ftp");
	
	String string = cadenaftp;
	String[] parts = string.split(";");
	String part0 = parts[0]; 
	String part1 = parts[1]; 
	String part2 = parts[2];
	
	FTPClient ftpClient = new FTPClient();
    ftpClient.connect(part0);
    ftpClient.login(part1,part2);
    
    //Verificar conexión con el servidor.
    
    int reply = ftpClient.getReplyCode();
    
    System.out.println("Respuesta recibida de conexión FTP:" + reply);
    
    /*if(FTPReply.isPositiveCompletion(reply))
    {
        System.out.println("Conectado Satisfactoriamente");
      //Verificar si se cambia de direcotirio de trabajo
        
        ftpClient.changeWorkingDirectory("/web/staging");//Cambiar directorio de trabajo
        System.out.println("Se cambió satisfactoriamente el directorio");
        //Activar que se envie cualquier tipo de archivo
        
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
        BufferedInputStream buffIn = null;
        
        buffIn = new BufferedInputStream(new FileInputStream(pathDocument));//Ruta del archivo para enviar
        //ftpClient.enterLocalPassiveMode();
        ftpClient.storeFile(nombreArchivo+"."+extension, buffIn);//Ruta completa de alojamiento en el FTP
        //ftpClient.completePendingCommand();
        System.out.println("FIN SUBIDA A FTP");
        System.out.println("DESCONECTANDO FTP..");
        buffIn.close(); //Cerrar envio de arcivos al FTP
        //ftpClient.logout(); //Cerrar sesión
        ftpClient.disconnect();//Desconectarse del servidor
        
        
    }
    else
        {
            System.out.println("Imposible conectarse al servidor");
        }*/
    
    FTPClient ftp = new FTPClient();
    ftp.connect(part0);
    ftp.login(part1, part2);
    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
    
    for(int i=0;i<infoArchivo.size();i++)
    {
    	InputStream in = new FileInputStream(infoArchivo.get(i).path);
        ftp.storeFile(infoArchivo.get(i).nombre, in);
        in.close();
    }
    
   
    ftp.disconnect();
   
    

}
public void ConexionFtpNc(List<FileTranferNcE> infoArchivo) throws IOException
{
	
	Map<String, String> pathConfig = new HashMap<String, String>();
	Document ed;
	ReadXml rx = new ReadXml();
	Constants constants = new Constants();
	ed = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
    pathConfig=rx.readXmlMapAppConfig(ed);
	String cadenaftp = pathConfig.get("ftp");
	
	String string = cadenaftp;
	String[] parts = string.split(";");
	String part0 = parts[0]; 
	String part1 = parts[1]; 
	String part2 = parts[2];
	
	FTPClient ftpClient = new FTPClient();
    ftpClient.connect(part0);
    ftpClient.login(part1,part2);
    
    //Verificar conexión con el servidor.
    
    int reply = ftpClient.getReplyCode();
    
    System.out.println("Respuesta recibida de conexión FTP:" + reply);
    
    /*if(FTPReply.isPositiveCompletion(reply))
    {
        System.out.println("Conectado Satisfactoriamente");
      //Verificar si se cambia de direcotirio de trabajo
        
        ftpClient.changeWorkingDirectory("/web/staging");//Cambiar directorio de trabajo
        System.out.println("Se cambió satisfactoriamente el directorio");
        //Activar que se envie cualquier tipo de archivo
        
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
        BufferedInputStream buffIn = null;
        
        buffIn = new BufferedInputStream(new FileInputStream(pathDocument));//Ruta del archivo para enviar
        //ftpClient.enterLocalPassiveMode();
        ftpClient.storeFile(nombreArchivo+"."+extension, buffIn);//Ruta completa de alojamiento en el FTP
        //ftpClient.completePendingCommand();
        System.out.println("FIN SUBIDA A FTP");
        System.out.println("DESCONECTANDO FTP..");
        buffIn.close(); //Cerrar envio de arcivos al FTP
        //ftpClient.logout(); //Cerrar sesión
        ftpClient.disconnect();//Desconectarse del servidor
        
        
    }
    else
        {
            System.out.println("Imposible conectarse al servidor");
        }*/
    
    FTPClient ftp = new FTPClient();
    ftp.connect(part0);
    ftp.login(part1, part2);
    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
    
    for(int i=0;i<infoArchivo.size();i++)
    {
    	InputStream in = new FileInputStream(infoArchivo.get(i).path);
        ftp.storeFile(infoArchivo.get(i).nombre, in);
        in.close();
    }
    
   
    ftp.disconnect();
   
    

}
}

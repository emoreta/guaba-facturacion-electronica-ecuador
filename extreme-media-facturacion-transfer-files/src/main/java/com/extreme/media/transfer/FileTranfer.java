package com.extreme.media.transfer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;


import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.conexion.BdCon;
import com.extreme.media.conexion.BdTypes;
import com.extreme.media.entity.FilesTranfer;
import com.extreme.media.entityes.ExternalData;
import com.extreme.media.ftp.Client;
import com.extreme.media.mail.SendMail;
import com.extreme.media.postgress.InformacionFactura;
import com.extreme.media.postgress.Querys;

public class FileTranfer {
	//static final Logger log = Logger.getLogger(FileTranfer.class);
	public static String getFechaActualFormatoUno()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha=String.format("%02d",c1.get(Calendar.DATE))+"/"+String.format("%02d", c1.get(Calendar.MONTH)+1)+"/"+annio;
		return fecha;
	}
	public static String getFechaActualFormatoCuatro()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		//String fecha=String.format("%02d",c1.get(Calendar.DATE))+"/"+String.format("%02d", c1.get(Calendar.MONTH)+1)+"/"+annio;
		String fecha=annio+"-"+String.format("%02d", c1.get(Calendar.MONTH)+1)+"-"+String.format("%02d",c1.get(Calendar.DATE));
		return fecha;
	}
	public static String getFechaActualFormatoTres()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha=annio+"-"+String.format("%02d",c1.get(Calendar.DATE))+"-"+String.format("%02d", c1.get(Calendar.MONTH)+1);
		return fecha;
	}
	public String getFechaActualFormatoDos()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fechaFormat=String.format("%02d",c1.get(Calendar.DATE))+String.format("%02d", c1.get(Calendar.MONTH)+1)+annio;
		return fechaFormat;
	}

	public static void main(String[] args) {
		DOMConfigurator.configure("C:\\FAC\\log\\LogPropertiesTransfer.xml");
		Log log = LogFactory.getLog(FileTranfer.class);
		// TODO Auto-generated method stub
		//String fecha="2018-09-10";
		String fecha="";
		
        if (args.length == 0) {
			
			//fecha = getFechaActualFormatoCuatro();
			// fecha = "2018-09-10";

		} else {
			if( args[0]=="GETDATENOW"  )
			{
			fecha = getFechaActualFormatoCuatro();
			}
			else
			{
			fecha = args[0];
			}
		}
		
		//String fecha=args[0];
		//informacion a consultar
		Connection con = null;
		InformacionFactura ifactura = new InformacionFactura();

		System.out.println("****INICIANDO VALIDADOR****");
		System.out.println("**CARGANDO CONFIGURACIONES**");
		System.out.println("**OBTENIENDO CONEXION A POSTGRESS**");
		
		log.info("*******INICIANDO TRANSFERENCIA DE ARCHIVOS******");
		
		List<ExternalData> listCheckTransfer = new ArrayList<ExternalData>();
		
		Map<String, String> pathFiles = new HashMap<String, String>();
		Document d;
		ReadXml rx = new ReadXml();
		Constants constants = new Constants();
		d = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);
		pathFiles=rx.readXmlDocument(d);
		String valuePath = pathFiles.get("filesFolderValidated");
		
		Map<String, String> pathConfig = new HashMap<String, String>();
		Document e;
		
		

		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		pathConfig=rx.readXmlMapAppConfig(e);
		
		String pathLogo=pathConfig.get("ImagenPdf");
		String ambiente=pathConfig.get("ambiente");
		String tipoEmision=pathConfig.get("tipoEmision");
		String correo=pathConfig.get("correoNotificacion");
		String[] partsCorreo = correo.split(";");
		String username="";
		String contrasena="";
		String host="";
		String port="";
		String correoTema="";
		try{
			 username=partsCorreo[0];
			 contrasena=partsCorreo[1];
			 host=partsCorreo[2];
			 port=partsCorreo[3];
			 correoTema=partsCorreo[4];
			
		}
		catch(Exception ex)
		{
			log.error("ERROR:SIN CONFIGURACION DE CORREO");
		}
		

		
		

		BdCon bd;
		try {
			bd = new BdCon(BdTypes.POSTGRESS);
			con = bd.GetConnectionPostgress();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			log.error("ERROR:",e3);
		}
		// creando coneccion a postgress
		
		//ReadXml rx = new ReadXml();

		// instancia de consulta para obtener informacion de base de datos
		Querys q = new Querys();
		
		Client cftp=new Client();
		Map<String, String> infoFiles = new HashMap<String, String>();
		List<FilesTranfer> listCheckFileTransfer = new ArrayList<FilesTranfer>();
		
		try {
			listCheckTransfer=q.QueryInfoFactTranfer(con, fecha,args[1]);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			log.error("ERROR:",e2);
			e2.printStackTrace();
		}
		System.out.println("TAMAÑO:"+listCheckTransfer.size());
		for (int y = 0; y < listCheckTransfer.size(); y++) {
			
			try {
				FilesTranfer f=new FilesTranfer();
				f.path=valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+"_AUTORIZADO.xml";
				f.nombre=listCheckTransfer.get(y).CLAVE_ACCESO+"_AUTORIZADO.xml";
				
				listCheckFileTransfer.add(f);
				FilesTranfer pdf=new FilesTranfer();
				pdf.path=valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+".pdf";
				pdf.nombre=listCheckTransfer.get(y).CLAVE_ACCESO+".pdf";
				
				listCheckFileTransfer.add(pdf);
				//infoFiles.put(valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+".xml", listCheckTransfer.get(y).CLAVE_ACCESO+"xml");
				//infoFiles.put(valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+".pdf", listCheckTransfer.get(y).CLAVE_ACCESO+"pdf");
				
				//subida xml
				//cftp.ConexionFtp(valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+".xml", listCheckTransfer.get(y).CLAVE_ACCESO, "xml");
				
				//Thread.sleep(3000);
				//subida ftp
				//cftp.ConexionFtp(valuePath.trim()+listCheckTransfer.get(y).CLAVE_ACCESO+".pdf", listCheckTransfer.get(y).CLAVE_ACCESO, "pdf");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:",e1);
				
			}
			
			String cadUno = valuePath.trim() + listCheckTransfer.get(y).CLAVE_ACCESO + ".pdf";
			String cadDos = "";
			String cadTres = valuePath.trim() + listCheckTransfer.get(y).CLAVE_ACCESO + "_AUTORIZADO.xml";

			// envio correo electronico
			SendMail sm = new SendMail();
			try {
				sm.SendMailDocumento(cadUno, cadDos, cadTres, cadDos,listCheckTransfer.get(y).CORREO_CLIENTE,username,contrasena,host,port,correoTema);
				log.info("CORREO ENVIADO:"+listCheckTransfer.get(y).CORREO_CLIENTE+"  "+listCheckTransfer.get(y).CLAVE_ACCESO+"_AUTORIZADO.xml");
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR AL ENVIAR CORREO:",e1);
			}
			
			
			
		}
		try {
			log.info("SUBIENDO  ARCHIVOS:");
			cftp.ConexionFtp(listCheckFileTransfer);//a subir archivos
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.error("ERROR:",e1);
		}
		
		
		

	}

}

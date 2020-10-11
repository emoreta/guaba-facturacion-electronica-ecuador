package com.extreme.media.send;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.transform.Nodos;
import com.extreme.media.util.Common;

public class SendDocument {
	
	Nodos nod = new Nodos();
	Map<String, String> pathConfig = new HashMap<String, String>();
	 Document e;
	 ReadXml rx = new ReadXml();
	 Constants constants = new Constants();
	 //
	
	public String EnvioInfoDocumento(String path, String sec) 
    {
		return sec;
    
    }
	
	
	public void comprobarConexion() throws InterruptedException
	{
		 Common ut = new Common();

		 String URL_RECEPCION_PRUEBAS="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";
         String URL_AUTORIZACION_PRUEBAS="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
         //String URL_RECEPCION_PRODUCCION="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl";
         //String URL_AUTORIZACION_PRODUCCION="https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
         String HOST_PRUEBAS="celcer.sri.gob.ec";
         //String HOST_PRODUCCION="cel.sri.gob.ec";
         String ambiente="1";//1 PRUEBAS 2 PRODUCCION
         
        String directorioComprobantes="";
        int intervaloEjecucion;
        final String method="POST";
        
        //ut.sendPostSoap(URL_RECEPCION_PRUEBAS,"POST",HOST_PRUEBAS);
        
        
	}
	public StringBuilder consultaComprobantesWebServices(String claveAcceso) throws InterruptedException, IOException
	{
		
		StringBuilder sbS=new StringBuilder();
		
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
        pathConfig=rx.readXmlMapAppConfig(e);
		String ambiente = pathConfig.get("ambiente");
		String urlRecepcion;
		String urlAutorizacion;
		String host;
		
		System.out.println(ambiente+"     CONSULTANDO DOCUMENTOS CON CLAVE ACCESSO");
		
		if(ambiente.equals("1"))
			{
			
			urlAutorizacion= pathConfig.get("URL_AUTORIZACION_PRUEBAS");
			host= pathConfig.get("HOST_PRUEBAS");
			Common ut = new Common();
			sbS=ut.sendPostSoapConsulta(urlAutorizacion,"POST",host,ut.formatSendPostAutorizacion(claveAcceso));
			
			}
		else if(ambiente.equals("2"))
		{
			urlAutorizacion= pathConfig.get("URL_AUTORIZACION_PRODUCCION");
			host= pathConfig.get("HOST_PRODUCCION");
			Common ut = new Common();
			sbS=ut.sendPostSoapConsulta(urlAutorizacion,"POST",host,ut.formatSendPostAutorizacion(claveAcceso));
		}
		  
		  //String URL_AUTORIZACION_PRUEBAS="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";
		  //String HOST_PRUEBAS="celcer.sri.gob.ec";
          //String HOST_PRODUCCION="cel.sri.gob.ec";
          //String ambiente="1";//1 PRUEBAS 2 PRODUCCION
          return sbS;
          
	}
	public StringBuilder enviarComprantesWebServices(String pathComprobante, int i) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		
		StringBuilder sb=new StringBuilder();
		
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
        pathConfig=rx.readXmlMapAppConfig(e);
		String ambiente = pathConfig.get("ambiente");
		String urlRecepcion;
		String urlAutorizacion;
		String host;
		
		
		System.out.println(ambiente+"  METODO ENVIAR COMMPROBANTES WEB SERVICES");
        if (pathComprobante == "") {

        }

        String codigoAcceso;
        String cedulaRUC;
        String fecha;
          
         String directorioComprobantes="";
         int intervaloEjecucion;
         final String method="POST";
         
      
        
        	if(ambiente.equals("1"))
			{
			
        	urlRecepcion= pathConfig.get("URL_RECEPCION_PRUEBAS");
			host= pathConfig.get("HOST_PRUEBAS");
			
        	
            Common ut = new Common();
            org.w3c.dom.Document doc = ut.getDoc(pathComprobante);
            codigoAcceso = nod.getNodes("infoTributaria", "claveAcceso", doc);
            cedulaRUC = nod.getNodes("infoFactura", "identificacionComprador", doc);;
            fecha = nod.getNodes("infoFactura", "fechaEmision", doc);
            
            System.out.println("DATOS XML    CODIGO ACCESO:"+codigoAcceso+"   CEDULA/RUC:"+cedulaRUC+"   FECHA:"+fecha);

           
            //ut.sendPostSoap(URL_RECEPCION_PRUEBAS, "POST",
            //		HOST_PRUEBAS, ut.formatSendPost(ut.converBase64(doc)),
            //        null, "test", ambiente, codigoAcceso, cedulaRUC, fecha, arregloComprobantes.get(i), pathComprobante, null, "sesion");
            
            sb=ut.sendPostSoap(urlRecepcion,"POST",host,ut.formatSendPost(ut.converBase64(doc)));
			}
        	else if(ambiente.equals("2"))
        	{
        		urlRecepcion= pathConfig.get("URL_RECEPCION_PRODUCCION");
    			host= pathConfig.get("HOST_PRODUCCION");
    			
            	
                Common ut = new Common();
                org.w3c.dom.Document doc = ut.getDoc(pathComprobante);
                codigoAcceso = nod.getNodes("infoTributaria", "claveAcceso", doc);
                cedulaRUC = nod.getNodes("infoFactura", "identificacionComprador", doc);;
                fecha = nod.getNodes("infoFactura", "fechaEmision", doc);
                
                System.out.println("DATOS XML    CODIGO ACCESO:"+codigoAcceso+"   CEDULA/RUC:"+cedulaRUC+"   FECHA:"+fecha);

               
                //ut.sendPostSoap(URL_RECEPCION_PRUEBAS, "POST",
                //		HOST_PRUEBAS, ut.formatSendPost(ut.converBase64(doc)),
                //        null, "test", ambiente, codigoAcceso, cedulaRUC, fecha, arregloComprobantes.get(i), pathComprobante, null, "sesion");
                
                sb=ut.sendPostSoap(urlRecepcion,"POST",host,ut.formatSendPost(ut.converBase64(doc)));
        	}

       

        return sb;
    }
	

}

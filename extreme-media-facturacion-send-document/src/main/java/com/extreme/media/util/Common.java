package com.extreme.media.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;

public class Common {

	Map<String, String> pathConfig = new HashMap<String, String>();
	Document e;
	ReadXml rx = new ReadXml();
	Constants constants = new Constants();

	public Document getDoc(String dir) throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File(dir);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		return doc;
	}

	public String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
			// "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			return doc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public String converBase64(Document doc) throws UnsupportedEncodingException {
		String str = convertDocumentToString(doc);
		String bytesEncoded = DatatypeConverter.printBase64Binary(str.getBytes("UTF-8"));
		return bytesEncoded;
	}

	public String formatSendPost(String bytesEncodeBase64) {

		System.out.println("FORMATO EN POST FACTURA");
		String xml = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ec='http://ec.gob.sri.ws.recepcion'>"
				+ "<soapenv:Header/>" + "<soapenv:Body>" + "<ec:validarComprobante>" + "<xml>" + bytesEncodeBase64
				+ "</xml>" + "</ec:validarComprobante>" + "</soapenv:Body>" + "</soapenv:Envelope>";
		return xml;
	}

	public java.net.Proxy setProxy(String ip, int port) {
		// System.out.println("SET PROXY : "+ip+" "+port);
		java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(ip, port));
		return proxy;
	}

	/* FUNCION PARA CONSUMIR WEB SERVICE */
	private HttpURLConnection conectarWebService(String urlWebServices, String method, String host) throws IOException

	{
		HttpURLConnection con;
		URL oURL = new URL(urlWebServices);
		con = (HttpURLConnection) oURL.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod(method);
		con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
		con.setRequestProperty("SOAPAction", "");
		con.setRequestProperty("Host", host);
		return con;
	}

	public StringBuilder enviarDocumentoConsulta(HttpURLConnection con, String getEncodeXML) {

		StringBuilder sbS=new StringBuilder();
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		pathConfig = rx.readXmlMapAppConfig(e);
		String ambiente = pathConfig.get("ambiente");
		String urlRecepcion;
		String urlAutorizacion;
		String host;

		if (ambiente.equals("1")) {

			urlAutorizacion = pathConfig.get("URL_AUTORIZACION_PRUEBAS");
			host = pathConfig.get("HOST_PRUEBAS");

			// String
			// URL_AUTORIZACION_PRUEBAS="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl";
			// String HOST_PRUEBAS="celcer.sri.gob.ec";
			// String HOST_PRODUCCION="cel.sri.gob.ec";
			// String ambiente="1";//1 PRUEBAS 2 PRODUCCION

			String directorioComprobantes = "";
			int intervaloEjecucion;
			final String method = "POST";
			HttpURLConnection conn = null;
			try {
				conn = conectarWebService(urlAutorizacion, method, host);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				System.out.println("DENTRO DE CONSULTA");

				OutputStream reqStreamOut = conn.getOutputStream();
				// System.out.println(reqStreamOut.toString());
				reqStreamOut.write(getEncodeXML.getBytes());

				System.out.println("RESPUESTA CONSULTA:" + reqStreamOut.toString());
				InputStream is = null;
				try {
					is = conn.getInputStream();
					sbS=leerRespuesta(is);
				} catch (IOException ex) {
					is = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error:" + e.toString());
				sbS=null;
			}

		} else if (ambiente.equals("2"))

		{
			urlAutorizacion = pathConfig.get("URL_AUTORIZACION_PRODUCCION");
			host = pathConfig.get("HOST_PRODUCCION");

			// String
			// URL_AUTORIZACION_PRUEBAS="https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl";
			// String HOST_PRUEBAS="celcer.sri.gob.ec";
			// String HOST_PRODUCCION="cel.sri.gob.ec";
			// String ambiente="1";//1 PRUEBAS 2 PRODUCCION

			String directorioComprobantes = "";
			int intervaloEjecucion;
			final String method = "POST";
			HttpURLConnection conn = null;
			try {
				conn = conectarWebService(urlAutorizacion, method, host);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				sbS=null;
			}
			
			try {
				System.out.println("DENTRO DE CONSULTA");

				OutputStream reqStreamOut = conn.getOutputStream();
				// System.out.println(reqStreamOut.toString());
				reqStreamOut.write(getEncodeXML.getBytes());

				System.out.println("RESPUESTA CONSULTA:" + reqStreamOut.toString());
				InputStream is = null;
				try {
					is = conn.getInputStream();
					sbS=leerRespuesta(is);
				} catch (IOException ex) {
					is = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error:" + e.toString());
				sbS=null;
			}
		}

		return sbS;

	}

	private StringBuilder enviarDocumento(HttpURLConnection con, String getEncodeXML) {
		
		StringBuilder sb=new StringBuilder();
		
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		pathConfig = rx.readXmlMapAppConfig(e);
		String ambiente = pathConfig.get("ambiente");
		String urlRecepcion;
		String urlAutorizacion;
		String host;
		
		if (ambiente.equals("1")) {
			urlRecepcion = pathConfig.get("URL_RECEPCION_PRUEBAS");
			host = pathConfig.get("HOST_PRUEBAS");
			
			String directorioComprobantes = "";
			int intervaloEjecucion;
			final String method = "POST";
			HttpURLConnection conn = null;
			try {
				conn = conectarWebService(urlRecepcion, method, host);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				System.out.println("ENVIANDO DOCUMENTO");

				OutputStream reqStreamOut = conn.getOutputStream();

				reqStreamOut.write(getEncodeXML.getBytes());

				InputStream is = null;
				try {
					is = conn.getInputStream();
					sb=leerRespuesta(is);
				} catch (IOException ex) {
					is = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error:" + e.toString());
			}

			
		}
		else if (ambiente.equals("2")) {
			urlRecepcion = pathConfig.get("URL_RECEPCION_PRODUCCION");
			host = pathConfig.get("HOST_PRODUCCION");
			
			String directorioComprobantes = "";
			int intervaloEjecucion;
			final String method = "POST";
			HttpURLConnection conn = null;
			try {
				conn = conectarWebService(urlRecepcion, method, host);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				System.out.println("ENVIANDO DOCUMENTO");

				OutputStream reqStreamOut = conn.getOutputStream();

				reqStreamOut.write(getEncodeXML.getBytes());

				InputStream is = null;
				try {
					is = conn.getInputStream();
					sb=leerRespuesta(is);
				} catch (IOException ex) {
					is = null;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error:" + e.toString());
			}
			
			
		}
		
	return sb;
		
	}

	private InputStream recibirRespuesta(HttpURLConnection con) {
		InputStream is = null;
		try {
			is = con.getInputStream();
		} catch (IOException ex) {
			is = null;
		}
		return is;
	}

	private StringBuilder leerRespuesta(InputStream is) throws UnsupportedEncodingException, IOException {

		StringBuilder sb = new StringBuilder();
		java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(is, "UTF8"));
		String line = "";
		while ((line = rd.readLine()) != null) {
			sb.append(line);
			// System.out.println("linea:"+ line);
			System.out.println("LEYENDO RESPUESTA:" + line);
		}
		return sb;
	}

	private void moverArchivo(String nombre_archivo, String path_archivo_xml) {
		try {
			File origen = new File(path_archivo_xml);

			// File comp = new
			// File(ambiente.getDirectorioComprobantes()+"\\ComprobantesNoProcesados");

			File comp = new File("ubicacionoprocesados");
			if (!comp.exists())
				comp.mkdir();
			// File destino = new
			// File(ambiente.getDirectorioComprobantes()+"\\ComprobantesNoProcesados\\"+nombre_archivo);
			File destino = new File("destinonoprocesados");
			InputStream in = new FileInputStream(origen);
			OutputStream out = new FileOutputStream(destino);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			// origen.delete();
		} catch (Exception e) {
			System.out.println("Se produjo un error (" + e.getMessage() + ")");
		}
	}

	public String formatSendPostAutorizacion(String codAcceso) {
		String xml = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ec='http://ec.gob.sri.ws.autorizacion'>"
				+ "<soapenv:Header/>" + "<soapenv:Body>" + "<ec:autorizacionComprobante>" + "<claveAccesoComprobante>"
				+ codAcceso + "</claveAccesoComprobante>" + "</ec:autorizacionComprobante>" + "</soapenv:Body>"
				+ "</soapenv:Envelope>";
		return xml;
	}

	public StringBuilder sendPostSoap(String urlWebServices, String method, String host, String getEncodeXML)
			throws InterruptedException, IOException {
		// public boolean sendPostSoap(String urlWebServices, String method,
		// String host) throws InterruptedException {
		StringBuilder sb=new StringBuilder();
		System.out.println("METODO DE ENVIO");
		// ambiente = amb;
		HttpURLConnection con = null;
		InputStream is = null;
		boolean respuestaConexion = false;
		

			sb=enviarDocumento(con, getEncodeXML);


		return sb;
	}

	public StringBuilder sendPostSoapConsulta(String urlWebServices, String method, String host, String getEncodeXML)
			throws InterruptedException, IOException {
		// public boolean sendPostSoap(String urlWebServices, String method,
		// String host) throws InterruptedException {
		StringBuilder sbS=new StringBuilder();
		
		System.out.println("ENVIO DOCUMENTO CONSULTA");
		// ambiente = amb;
		HttpURLConnection con = null;
		InputStream is = null;
		boolean respuestaConexion = false;
		try {

			sbS=enviarDocumentoConsulta(con, getEncodeXML);

			// System.out.println("DOCUMENTO ENVIADO");
			// System.out.println("ESPERANDO RESPUESTA");
		} catch (Exception ex) {
			/*
			 * con.disconnect();
			 * System.out.println("SE PRODUJO UN ERROR AL ESTABLECER CONEXION ("
			 * +ex.getMessage()+")"); respuestaConexion=false;
			 * respuestaFinal=respuestaConexion;
			 */
			sbS=null;
		}
		return sbS;
	}

}

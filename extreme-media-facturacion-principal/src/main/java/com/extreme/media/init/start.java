package com.extreme.media.init;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.MoveFile;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.commons.Util;
import com.extreme.media.conexion.BdCon;
import com.extreme.media.conexion.BdTypes;
import com.extreme.media.connection.ConeccionMysql;
import com.extreme.media.decrypt.Decrypt;
import com.extreme.media.encrypt.Encrypt;
import com.extreme.media.entity.ExternalPostgres;
import com.extreme.media.entity.FacturaDinamico;
import com.extreme.media.entity.FacturaFijo;
import com.extreme.media.entity.StructEntity;
import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.extreme.media.mail.SendMail;
import com.extreme.media.map.Mapping;
import com.extreme.media.pdf.Factura;
import com.extreme.media.postgress.InformacionFactura;
import com.extreme.media.postgress.Querys;
import com.extreme.media.send.SendDocument;
import com.extreme.media.struct.SetStruct;
import com.extreme.media.struct.Struct;
import com.extreme.media.transform.ClaveAcceso;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import core.XAdESBESSignature;

public class start {

	static final String TuIoPlMnFXxX = "Facturacion2018@Integracion";
	//static final Logger log = Logger.getLogger(start.class);
	

	public static String getFechaActualFormatoUno() {
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha = String.format("%02d", c1.get(Calendar.DATE)) + "/"
				+ String.format("%02d", c1.get(Calendar.MONTH) + 1) + "/" + annio;
		return fecha;
	}

	public static String getFechaActualFormatoCuatro() {
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha = annio + "-" + String.format("%02d", c1.get(Calendar.MONTH) + 1) + "-"
				+ String.format("%02d", c1.get(Calendar.DATE));
		return fecha;
	}

	public static String getFechaActualFormatoTres() {
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha = annio + "-" + String.format("%02d", c1.get(Calendar.DATE)) + "-"
				+ String.format("%02d", c1.get(Calendar.MONTH) + 1);
		return fecha;
	}

	public static String getFechaActualFormatoDos() {
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fechaFormat = String.format("%02d", c1.get(Calendar.DATE))
				+ String.format("%02d", c1.get(Calendar.MONTH) + 1) + annio;
		return fechaFormat;
	}

	public static void main(String[] args) {
		
		DOMConfigurator.configure("C:\\FAC\\log\\LogPropertiesStart.xml");
		Log log = LogFactory.getLog(start.class);
		

		String valuePath = "";
		String pathLogo = "";
		String ambiente = "";
		String tipoEmision = "";
		String log4JPropertyFile = "C:/FAC/log/log4j.properties";
		int waitTime = 0;
		Properties p = new Properties();

		log.info("**INICIANDO APLICACION FACTURACION ELECTRONICA**");

		log.info("FECHA UNO:" + getFechaActualFormatoUno());
		log.info("FECHA DOS:" + getFechaActualFormatoDos());
		log.info("FECHA TRES:" + getFechaActualFormatoTres());
		log.info("FECHA CUATRO:" + getFechaActualFormatoCuatro());

		Util u = new Util();
		Decrypt de = new Decrypt();

		// OBTENIENDO CONSTANTES CONFIGURACIONES
		Map<String, String> pathFiles = new HashMap<String, String>();
		Map<String, String> pathConfig = new HashMap<String, String>();
		Map<String, String> XmlRespuestaSri = new HashMap<String, String>();
		Map<String, String> firmas = new HashMap<String, String>();

		Map<String, String> XmlRespuestaSign = new HashMap<String, String>();

		Document d;
		Document e;
		Document resSri;
		Document sign;
		ReadXml rx = new ReadXml();

		Constants constants = new Constants();
		try {

			d = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);

			e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);

			sign = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.SIGNCONFIG);

			log.info("**LEYENDO CONFIGURACIONES**");

			pathFiles = rx.readXmlDocument(d);

			XmlRespuestaSign = rx.readXmlMapDocumentSign(sign);

			valuePath = pathFiles.get("filesFolderValidated");

			pathConfig = rx.readXmlMapAppConfig(e);

			String ubicacionMoverArchivo = pathConfig.get("UbicacionServidor");

			String res = "";
			String passSignature = "";

			System.out.println("NUMERO DE FIRMAS CONFIGURADAS:" + XmlRespuestaSign.size());

			log.info("**NUMERO DE FIRMAS**" + XmlRespuestaSign.size());

			for (Entry<String, String> jugador : XmlRespuestaSign.entrySet()) {
				String clave = jugador.getKey().toString();
				String valor = jugador.getValue();
				firmas.put(clave, valor);
				log.info("Firma:" + clave);
			}

			pathLogo = pathConfig.get("ImagenPdf");
			ambiente = pathConfig.get("ambiente");
			tipoEmision = pathConfig.get("tipoEmision");
			waitTime = Integer.parseInt(pathConfig.get("WaitTimeSend"));

			log.info("Configuración:pathLogo:" + pathLogo);
			log.info("Configuración:ambiente:" + ambiente);
			log.info("Configuración:tipoEmision:" + tipoEmision);
			log.info("Configuración:waitTime:" + waitTime);
		} catch (Exception e3) {
			log.error("ERROR:", e3);
			e3.printStackTrace();

		}

		// CARGA DINAMICA INFORMACION PARA APLICACION 16072018

		String fecha = "";// esta variable se obtiene automaticamente
							// del getdate del sistema
		String estado = "";
		String ruc = "";
		if (args.length == 0) {

		} else {
			if (args[0] == "GETDATENOW") {
				fecha = getFechaActualFormatoCuatro();
			} else {
				fecha = args[0];
			}

		}

		Connection con = null;
		InformacionFactura ifactura = new InformacionFactura();
		System.out.println("----INICIANDO APLICACION FACTURACION----");
		System.out.println("**CARGANDO CONFIGURACIONES**");
		System.out.println("**OBTENIENDO CONEXION A POSTGRESS**");

		BdCon bd;
		try {
			bd = new BdCon(BdTypes.POSTGRESS);
			con = bd.GetConnectionPostgress();
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
			log.error("ERROR:", e4);
		}
		// creando coneccion a postgress
		

		// instancia de consulta para obtener informacion de base de datos
		Querys q = new Querys();

		// LISTA PARA OBTENER CABECERA
		List<ExternalData> listCabecera = new ArrayList<ExternalData>();

		try {

			listCabecera = q.QueryInfoFactDinamico(con, fecha, args[1], args[2],args[3]);//se grega clave de acceso

			log.info("ARGUMENTOS:" + fecha + " " + args[1] + " " + args[2]+" "+args[3]);

		} catch (SQLException e3) {
			e3.printStackTrace();
			log.error("ERROR:", e3);
		}

		// variables externas para mapeo
		System.out.println("TAMANO CABECERA:" + listCabecera.size());

		for (int y = 0; y < listCabecera.size(); y++) {

			List<ExternalDataDetalle> result = new ArrayList<ExternalDataDetalle>();

			System.out.println("********-------------------ORDEN DE ENVIO " + y + "---------------------*********");
			log.info("ORDEN DE ENVIO:" + y);

			System.out.println(listCabecera.get(y).CLAVE_ACCESO);
			log.info("PROCESANDO:" + listCabecera.get(y).CLAVE_ACCESO);

			Querys qr = new Querys();

			try {
				result = qr.QueryInfoDetFact(con, listCabecera.get(y).IDENTIFICACION_EMPRESA,
						listCabecera.get(y).ESTABLECIMIENTO, listCabecera.get(y).PUNTO_EMISION,
						listCabecera.get(y).SECUENCIAL);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				log.error("ERROR:" + e2);
			}

			System.out.println("TAMANO:" + result.size());

			log.info("TAMANO:" + result.size());

			// SETEANDO CLASES PARA CREACION DE ARCHIVOS
			// SETEANDO VALORES
			SetStruct ss = new SetStruct();
			StructEntity se = new StructEntity();
			se = ss.SetConstants(listCabecera.get(y), result);

			// CREACION DE ARCHIVO OBTENIENDO STRING XML
			Struct s = new Struct();
			String xml;
			xml = s.StructFactura(se.getFf(), se.getList(), listCabecera.get(y).CLAVE_ACCESO);
			// CREANDO ARCHIVO FISICO
			BufferedWriter writer;

			System.out.println(valuePath.trim() + listCabecera.get(y).CLAVE_ACCESO + ".xml");

			log.info(valuePath.trim() + listCabecera.get(y).CLAVE_ACCESO + ".xml");

			try {
				writer = new BufferedWriter(
						new FileWriter(valuePath.trim() + listCabecera.get(y).CLAVE_ACCESO + ".xml"));
				writer.write(xml);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:" + e1);
			}
			// LA RUTA OBTENER DE CONFIGURACIONES

			// FIRMANDO DOCUMENTOS

			String xmlPath = valuePath.trim() + listCabecera.get(y).CLAVE_ACCESO + ".xml";
			// String pathSignature = firmaPath;

			String pathOut = valuePath.trim();
			String nameFileOut = listCabecera.get(y).CLAVE_ACCESO + "_Sign.xml";

			System.out.println("UBICACION XML:" + xmlPath);

			log.info("UBICACION XML:" + xmlPath);

			if (firmas.containsKey(listCabecera.get(y).IDENTIFICACION_EMPRESA) == true) {// existe
																							// la
				log.info("VALIDANDO QUE FIRMA SEA LA QUE SE VA INGRESAR"); // firma
				String[] parts = firmas.get(listCabecera.get(y).IDENTIFICACION_EMPRESA).split("  ");

				System.out.println("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]);
				log.info("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]);

				try {

					XAdESBESSignature.firmar(xmlPath, parts[0], Decrypt.Desencriptar(parts[1], TuIoPlMnFXxX), pathOut,
							nameFileOut);
					log.info("ARCHIVO FIRMADO" + listCabecera.get(y).CLAVE_ACCESO);
				} catch (CertificateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log.error("ERROR:", e1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log.error("ERROR:", e1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					log.error("ERROR:", e1);
				}

			} else {

			}

			// ENVIO DE DOCUMENTOS
			SendDocument sd = new SendDocument();

			StringBuilder sbS = new StringBuilder();

			Boolean resultUpdateSend = false;
			try {

				sbS = sd.enviarComprantesWebServices(valuePath.trim() + listCabecera.get(y).CLAVE_ACCESO + "_Sign.xml",
						1);
				log.info("ARCHIVO ENVIADO:" + listCabecera.get(y).CLAVE_ACCESO + "_Sign.xml");

				resultUpdateSend = q.UpdateEstado(con, "ENVIADO", "", listCabecera.get(y).IDENTIFICACION_EMPRESA,
						listCabecera.get(y).ESTABLECIMIENTO, listCabecera.get(y).PUNTO_EMISION,
						listCabecera.get(y).SECUENCIAL);
				// *********************************************REGISTRO SE
				// ENVIO**********************************************

				Document dSri = rx.convertStringToDocument(sbS.toString());
				Map<String, String> infoResSri = new HashMap<String, String>();
				infoResSri = rx.readXmlRespuestaSri(dSri);

				System.out.println("RESULTADO SRI:" + infoResSri.get("estado"));
				Boolean resultUpdate;

				if (infoResSri.get("estado").equals("AUTORIZADO")) {
					System.out.println("ACTUALIZANDO AUTORIZADAS");
					System.out.println("CDATA:" + infoResSri.get("comprobante"));

					try {
						resultUpdate = q.UpdateEstado(con, infoResSri.get("estado"), "",
								listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
								listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						log.error("ERROR:" + e2);
						log.error("ERROR GRAVE NO SE PUDO ACTUALIZAR EN BD :", e2);
					}
					/*
					 * CREACION DE PDF EN CASO DE QUE APARESCA COMO AUTORIZADO
					 */
					// CREACION DE PDF
					Factura factPdf = new Factura();

					try {
						factPdf.GenerarPdf(listCabecera.get(y), result, pathLogo, ambiente, tipoEmision,
								valuePath.trim());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (BadElementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					}

					// insercion en base de datos mysql
					ConeccionMysql conmysql;
					try {
						double valorTotal=0.00; 
						double finalValorTotal=0.00; 
						valorTotal=Double.sum(Double.parseDouble(listCabecera.get(y).TOTAL_CON_IMPUESTO),Double.parseDouble(listCabecera.get(y).PROPINA));
						finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
					
							conmysql = new ConeccionMysql();
						
						String fechaUno = getFechaActualFormatoCuatro();

						System.out.println("FECHA:" + fechaUno);
						// u.getFechaActualFormatoTres()
						conmysql.UpdateTableDocuments(fechaUno, listCabecera.get(y).IDENTIFICACION_CLIENTE,
								listCabecera.get(y).RAZON_SOCIAL_CLIENTE, listCabecera.get(y).CLAVE_ACCESO, "Factura",
								listCabecera.get(y).CLAVE_ACCESO + ".pdf",
								listCabecera.get(y).CLAVE_ACCESO + "_Sign.xml", "1", fechaUno,
								listCabecera.get(y).NOMBRE_COMERCIAL,String.valueOf(finalValorTotal));
					}
						 catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);

					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
						log.error("ERROR AL INSERTAR A MY SQL:" + e1);
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
				}

					// FIN ENVIO DE
					// DOCUMENTOS---------------------------------------------------------------

				} else if (infoResSri.get("estado").equals("RECIBIDA"))

				{
					System.out.println("ACTUALIZANDO RECIBIDA");
					try {
						resultUpdate = q.UpdateEstado(con, infoResSri.get("estado"), "",
								listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
								listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
						log.error("ERROR GRAVE NO SE PUDO ACTUALIZAR EN BD :", e1);
					}
				} else if (infoResSri.get("estado").equals("DEVUELTA"))

				{
					System.out.println("ACTUALIZANDO DEVUELTA");
					try {
						log.info("MENSAJE DEVUELTA"+infoResSri.get("mensaje"));
						resultUpdate = q.UpdateEstado(con, infoResSri.get("estado"),
								infoResSri.get("identificador") + ";" + infoResSri.get("tipo") + ";"
										+ infoResSri.get("mensaje").replaceAll("\'", "") + ";" + infoResSri.get("informacionAdicional"),
								listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
								listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					}
				} else {
					try {
						resultUpdate = q.UpdateEstado(con, infoResSri.get("estado"),
								infoResSri.get("identificador") + ";" + infoResSri.get("tipo") + ";"
										+ infoResSri.get("mensaje") + ";" + infoResSri.get("informacionAdicional"),
								listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
								listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					}
				}
				
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException et) {
					// TODO Auto-generated catch block
					et.printStackTrace();
					log.error("ERROR EN TIEMPOS DE ESPERA:", et);
				}

				// **************************************FIN ENVIO****************************************************************************

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR GRAVE NO SE PUDO ACTUALIZAR EN BD :", e1);

			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstado(con, "ERROR", e1.toString(),
							listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
							listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstado(con, "ERROR", e1.toString(),
							listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
							listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstado(con, "ERROR", e1.toString(),
							listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
							listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstado(con, "ERROR", e1.toString(),
							listCabecera.get(y).IDENTIFICACION_EMPRESA, listCabecera.get(y).ESTABLECIMIENTO,
							listCabecera.get(y).PUNTO_EMISION, listCabecera.get(y).SECUENCIAL);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			

		}
		// FINAL BUCLE

	}

}

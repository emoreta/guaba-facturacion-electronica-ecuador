package com.extreme.media.nc.init;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.commons.Util;
import com.extreme.media.conexion.BdCon;
import com.extreme.media.conexion.BdTypes;
import com.extreme.media.connection.ConeccionMysql;
import com.extreme.media.decrypt.Decrypt;
import com.extreme.media.entity.StructEntity;
import com.extreme.media.entity.StructEntityNc;
import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.extreme.media.entityes.ExternalDataDetalleNc;
import com.extreme.media.entityes.ExternalDataNc;
import com.extreme.media.pdf.Factura;
import com.extreme.media.postgress.InformacionFactura;
import com.extreme.media.postgress.Querys;
import com.extreme.media.send.SendDocument;
import com.extreme.media.struct.SetStruct;
import com.extreme.media.struct.Struct;
import com.extreme.media.struct.nc.SetStructNc;
import com.extreme.media.struct.nc.StructNc;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import core.XAdESBESSignature;



public class StartNc {

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
		// TODO Auto-generated method stubs
		DOMConfigurator.configure("C:\\FAC\\log\\LogPropertiesStartNc.xml");
		Log log = LogFactory.getLog(StartNc.class);
		String valuePath = "";
		String pathLogo = "";
		String ambiente = "";
		String tipoEmision = "";
		String log4JPropertyFile = "C:/FAC/log/log4j.properties";
		int waitTime = 0;
		Properties p = new Properties();
		
		log.info("**INICIANDO APLICACION FACTURACION ELECTRONICA NC**");

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
		System.out.println("----INICIANDO APLICACION NOTAS DE CREDITO----");
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
		List<ExternalDataNc> listCabecera = new ArrayList<ExternalDataNc>();

		try {

			listCabecera = q.QueryInfoNcDinamico(con, fecha, args[1], args[2],args[3]);//se grega clave de acceso

			log.info("ARGUMENTOS:" + fecha + " " + args[1] + " " + args[2]+" "+args[3]);

		} catch (SQLException e3) {
			e3.printStackTrace();
			log.error("ERROR:", e3);
		}

		// variables externas para mapeo
		System.out.println("TAMANO CABECERA:" + listCabecera.size());

		for (int y = 0; y < listCabecera.size(); y++) {

			List<ExternalDataDetalleNc> result = new ArrayList<ExternalDataDetalleNc>();

			System.out.println("********-------------------ORDEN DE ENVIO " + y + "---------------------*********");
			log.info("ORDEN DE ENVIO:" + y);

			System.out.println(listCabecera.get(y).clave_acceso_sri_nc);
			log.info("PROCESANDO:" + listCabecera.get(y).clave_acceso_sri_nc);

			Querys qr = new Querys();
	

			try {
				result = qr.QueryInfoDetNc(con, listCabecera.get(y).identificacion_empresa_nc,
						listCabecera.get(y).establecimiento_nc, listCabecera.get(y).punto_emision_nc,
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
			SetStructNc ss = new SetStructNc();
			StructEntityNc se = new StructEntityNc();
			se = ss.SetConstantsNc(listCabecera.get(y), result);

			// CREACION DE ARCHIVO OBTENIENDO STRING XML
			StructNc s = new StructNc();
			String xml;
			xml = s.StructNotaCredito(se.getFf(), se.getList(), listCabecera.get(y).clave_acceso_sri_nc);
			// CREANDO ARCHIVO FISICO
			BufferedWriter writer;

			System.out.println(valuePath.trim() + listCabecera.get(y).clave_acceso_sri_nc + ".xml");

			log.info(valuePath.trim() + listCabecera.get(y).clave_acceso_sri_nc + ".xml");

			try {
				writer = new BufferedWriter(
						new FileWriter(valuePath.trim() + listCabecera.get(y).clave_acceso_sri_nc + ".xml"));
				writer.write(xml);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:" + e1);
			}
			// LA RUTA OBTENER DE CONFIGURACIONES

			// FIRMANDO DOCUMENTOS

			String xmlPath = valuePath.trim() + listCabecera.get(y).clave_acceso_sri_nc + ".xml";
			// String pathSignature = firmaPath;

			String pathOut = valuePath.trim();
			String nameFileOut = listCabecera.get(y).clave_acceso_sri_nc + "_Sign.xml";

			System.out.println("UBICACION XML NC:" + xmlPath);

			log.info("UBICACION XML NC:" + xmlPath);

			if (firmas.containsKey(listCabecera.get(y).IDENTIFICACION_EMPRESA) == true) {// existe
																							// la
				log.info("VALIDANDO QUE FIRMA SEA LA QUE SE VA INGRESAR NC"); // firma
				String[] parts = firmas.get(listCabecera.get(y).identificacion_empresa_nc).split("  ");

				System.out.println("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]);
				log.info("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]);

				try {

					XAdESBESSignature.firmar(xmlPath, parts[0], Decrypt.Desencriptar(parts[1], TuIoPlMnFXxX), pathOut,
							nameFileOut);
					log.info("ARCHIVO FIRMADO NC" + listCabecera.get(y).clave_acceso_sri_nc);
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

				sbS = sd.enviarComprantesWebServices(valuePath.trim() + listCabecera.get(y).clave_acceso_sri_nc + "_Sign.xml",
						1);
				log.info("ARCHIVO ENVIADO:" + listCabecera.get(y).clave_acceso_sri_nc + "_Sign.xml");

				resultUpdateSend = q.UpdateEstadoNc(con, "ENVIADO", "", listCabecera.get(y).identificacion_empresa_nc,
						listCabecera.get(y).establecimiento_nc, listCabecera.get(y).punto_emision_nc,
						listCabecera.get(y).secuencial_nc);
				// *********************************************REGISTRO SE
				// ENVIO**********************************************

				Document dSri = rx.convertStringToDocument(sbS.toString());
				Map<String, String> infoResSri = new HashMap<String, String>();
				infoResSri = rx.readXmlRespuestaSri(dSri);

				System.out.println("RESULTADO SRI NC:" + infoResSri.get("estado"));
				Boolean resultUpdate;

				if (infoResSri.get("estado").equals("AUTORIZADO")) {
					System.out.println("ACTUALIZANDO AUTORIZADAS");
					System.out.println("CDATA:" + infoResSri.get("comprobante"));

					try {
						resultUpdate = q.UpdateEstadoNc(con, infoResSri.get("estado"), "",
								listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
								listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
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
						factPdf.GenerarPdfNc(listCabecera.get(y), result, pathLogo, ambiente, tipoEmision,
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
					
							conmysql = new ConeccionMysql();
						
						String fechaUno = getFechaActualFormatoCuatro();

						System.out.println("FECHA:" + fechaUno);
						// u.getFechaActualFormatoTres()
						conmysql.UpdateTableDocuments(fechaUno, listCabecera.get(y).IDENTIFICACION_CLIENTE,
								listCabecera.get(y).RAZON_SOCIAL_EMPRESA, listCabecera.get(y).clave_acceso_sri_nc, "Nota Credito",
								listCabecera.get(y).clave_acceso_sri_nc + ".pdf",
								listCabecera.get(y).clave_acceso_sri_nc + "_Sign.xml", "1", fechaUno,
								listCabecera.get(y).RAZON_SOCIAL_EMPRESA,
								listCabecera.get(y).TOTAL_IMPUESTO);//esta linea se agrego el 19032018 verificar
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
						resultUpdate = q.UpdateEstadoNc(con, infoResSri.get("estado"), "",
								listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
								listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
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
						resultUpdate = q.UpdateEstadoNc(con, infoResSri.get("estado"),
								infoResSri.get("identificador") + ";" + infoResSri.get("tipo") + ";"
										+ infoResSri.get("mensaje").replaceAll("\'", "") + ";" + infoResSri.get("informacionAdicional"),
										listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
										listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						log.error("ERROR:" + e1);
					}
				} else {
					try {
						resultUpdate = q.UpdateEstadoNc(con, infoResSri.get("estado"),
								infoResSri.get("identificador") + ";" + infoResSri.get("tipo") + ";"
										+ infoResSri.get("mensaje") + ";" + infoResSri.get("informacionAdicional"),
										listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
										listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
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
					resultUpdateSend = q.UpdateEstadoNc(con, "ERROR", e1.toString(),
							listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
							listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstadoNc(con, "ERROR", e1.toString(),
							listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
							listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstadoNc(con, "ERROR", e1.toString(),
							listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
							listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.error("ERROR:", e1);
				try {
					resultUpdateSend = q.UpdateEstadoNc(con, "ERROR", e1.toString(),
							listCabecera.get(y).identificacion_empresa_nc, listCabecera.get(y).establecimiento_nc,
							listCabecera.get(y).establecimiento_nc, listCabecera.get(y).secuencial_nc);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			

		}
		// FINAL BUCLE
		
		
		
		
		
		

	}

}

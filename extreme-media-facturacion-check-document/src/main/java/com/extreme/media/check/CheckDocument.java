package com.extreme.media.check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.MoveFile;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.conexion.BdCon;
import com.extreme.media.conexion.BdTypes;
import com.extreme.media.connection.ConeccionMysql;
import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.extreme.media.mail.SendMail;
import com.extreme.media.pdf.Factura;
import com.extreme.media.postgress.InformacionFactura;
import com.extreme.media.postgress.Querys;
import com.extreme.media.send.SendDocument;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class CheckDocument {
	static final Logger log = Logger.getLogger(CheckDocument.class);

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
		// String
		// fecha=String.format("%02d",c1.get(Calendar.DATE))+"/"+String.format("%02d",
		// c1.get(Calendar.MONTH)+1)+"/"+annio;
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

	public String getFechaActualFormatoDos() {
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fechaFormat = String.format("%02d", c1.get(Calendar.DATE))
				+ String.format("%02d", c1.get(Calendar.MONTH) + 1) + annio;
		return fechaFormat;
	}

	public static void main(String[] args) {
		ConeccionMysql conmysql=null;
		Map<String, String> XmlRespuestaSign = new HashMap<String, String>();
		Map<String, String> firmas = new HashMap<String, String>();
	
		DOMConfigurator.configure("C:\\FAC\\log\\LogPropertiesCheck.xml");
		Log log = LogFactory.getLog(CheckDocument.class);
		// TODO Auto-generated method stub
		log.info("**INICIANDO APLICACION FACTURACION ELECTRONICA CONSULTA**");
		try {
			conmysql = new ConeccionMysql();
			System.out.println("You made it, take control your database now MYSQL!");
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
			log.error(" SIN CONEXION A MYSQL",e4);
			return;
		}
		
		log.info(args[0]+"   "+args[1]);
		String estado="";
		String fecha = "";
		if (args.length == 0) {
			
			fecha = getFechaActualFormatoCuatro();
			// fecha = "2018-09-10";

		} else {

			fecha = args[0];
			estado= args[1];
			log.info("argumentos");
		}

		// String fecha="2018-07-30";
		// informacion a consultar
		Connection con = null;
		InformacionFactura ifactura = new InformacionFactura();
		
		
		
		

		System.out.println("****INICIANDO VALIDADOR****");
		System.out.println("**CARGANDO CONFIGURACIONES**");
		System.out.println("**OBTENIENDO CONEXION A POSTGRESS**");
		List<ExternalData> listCheck = new ArrayList<ExternalData>();

		Map<String, String> pathFiles = new HashMap<String, String>();
		Document d;
		ReadXml rx = new ReadXml();
		Constants constants = new Constants();
		d = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);
		pathFiles = rx.readXmlDocument(d);
		String valuePath = pathFiles.get("filesFolderValidated");

		System.out.println("PATH ARCHIVOS:" + valuePath.trim());

		Map<String, String> pathConfig = new HashMap<String, String>();
		Document e;
		Document sign;
		sign = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.SIGNCONFIG);
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		pathConfig = rx.readXmlMapAppConfig(e);

		String pathLogo = pathConfig.get("ImagenPdf");
		String ambiente = pathConfig.get("ambiente");
		String tipoEmision = pathConfig.get("tipoEmision");
		
		//CARGA FIRMAS LOGO
				XmlRespuestaSign = rx.readXmlMapDocumentSign(sign);
				log.info("**NUMERO DE FIRMAS**" + XmlRespuestaSign.size());

				for (Entry<String, String> jugador : XmlRespuestaSign.entrySet()) {
					String clave = jugador.getKey().toString();
					String valor = jugador.getValue();
					firmas.put(clave, valor);
					log.info("Firma:" + clave);
				}

		BdCon bd;
		try {
			bd = new BdCon(BdTypes.POSTGRESS);
			con = bd.GetConnectionPostgress();
			
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			log.error(" SIN CONEXION A POSTGRESS",e3);
			return;
		}
		// creando coneccion a postgress
		
		// ReadXml rx = new ReadXml();

		// instancia de consulta para obtener informacion de base de datos
		Querys q = new Querys();

		try {
			listCheck = q.QueryInfoFactCheckDinamico(con, fecha.trim(),estado.trim());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();

			log.error("ERROR:", e2);
		}

		for (int y = 0; y < listCheck.size(); y++) {

			List<ExternalDataDetalle> result = new ArrayList<ExternalDataDetalle>();
			Querys qr = new Querys();
			
			log.info("REVISANDO:"+ listCheck.get(y).CLAVE_ACCESO);

			try {
				result = qr.QueryInfoDetFact(con, listCheck.get(y).IDENTIFICACION_EMPRESA,
						listCheck.get(y).ESTABLECIMIENTO, listCheck.get(y).PUNTO_EMISION, listCheck.get(y).SECUENCIAL);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				log.error("ERROR:", e2);
			}

			System.out.println("CLAVE ACCESO:" + listCheck.get(y).CLAVE_ACCESO);

			System.out.println("ITERACION DE VALIDACION" + y);
			SendDocument sd = new SendDocument();
			try {
				StringBuilder sbS = new StringBuilder();
				sbS = null;
				sbS = sd.consultaComprobantesWebServices(listCheck.get(y).CLAVE_ACCESO);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException et) {
					// TODO Auto-generated catch block
					et.printStackTrace();
					log.error("ERROR:", et);
				}

				Document dSri = rx.convertStringToDocument(sbS.toString());

				Map<String, String> infoResSri = new HashMap<String, String>();
				infoResSri = rx.readXmlRespuestaAutorizacionSri(dSri);
				Boolean resultUpdate;

				if (infoResSri.get("estado") == null) {
					// resultUpdate=q.UpdateEstadoCheck(con,"", "NUNCA ENVIADA",
					// "", listCheck.get(y).CLAVE_ACCESO);
				} else {
					System.out.println("ESTADO RETORNO:" + infoResSri.get("estado"));
					log.info("RESPUESTA:"+ listCheck.get(y).CLAVE_ACCESO+"    "+infoResSri.get("estado"));
					if (infoResSri.get("estado").equals("AUTORIZADO")) {
						BufferedWriter writer;
						try {
							writer = new BufferedWriter(new FileWriter(valuePath.trim() + listCheck.get(y).CLAVE_ACCESO
									+ "_" + infoResSri.get("estado") + ".xml"));
							writer.write(sbS.toString());
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}

						Factura factPdf = new Factura();

						try {
							log.info("VALIDANDO QUE FIRMA SEA LA QUE SE VA INGRESAR"); // firma
							log.info("Tamaño:"+firmas.size());
							
							String[] parts = firmas.get(listCheck.get(y).IDENTIFICACION_EMPRESA).split("  ");

							System.out.println("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]+ "----" + parts[3]);
							log.info("FIRMA:" + parts[0] + "----" + parts[1]+ "----" + parts[2]+ "----" + parts[3]);
							if(parts[3].toString().equals("0"))
							{
							factPdf.GenerarPdfStandart(listCheck.get(y), result,parts[2], ambiente, tipoEmision,
									valuePath.trim().trim());
							log.info("CREANDO PDF:"+ listCheck.get(y).CLAVE_ACCESO);
							}
							else if(parts[3].toString().equals("1"))
							{
								factPdf.GenerarPdf(listCheck.get(y), result,parts[2], ambiente, tipoEmision,
										valuePath.trim().trim());
								log.info("CREANDO PDF:"+ listCheck.get(y).CLAVE_ACCESO);
							}
							else
							{
								
								log.error("ERROR GRAVE PDF:NO SE ELEIGIO NINGUN TEMPLATE");
							}
						} catch (BadElementException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR CREAR PDF:", e1);
						}

						System.out.println("ACTUALIZANDO AUTORIZADAS");
						System.out.println("CDATA:" + infoResSri.get("comprobante"));
						
						log.info("ACTUALIZANDO AUTORIZADAS :"+ listCheck.get(y).CLAVE_ACCESO);

						try {
							resultUpdate = q.UpdateEstadoCheck(con, infoResSri.get("estado"), "",
									infoResSri.get("fechaAutorizacion"), listCheck.get(y).CLAVE_ACCESO);
							log.info("ACTUALIZADO EN BD AUTORIZADAS :"+ listCheck.get(y).CLAVE_ACCESO);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							log.error("ERROR EN ACTUALIZAR REGISTRO:", e2);
						}

						// insercion en base de datos mysql
						

						String fechaUno = getFechaActualFormatoCuatro();

						System.out.println("FECHA:" + fechaUno);
						// u.getFechaActualFormatoTres()
						// listCheck.get(y).IDENTIFICACION_EMPRESA
					
							try {
								double valorTotal=0.00; 
								double finalValorTotal=0.00; 
								valorTotal=Double.sum(Double.parseDouble(listCheck.get(y).TOTAL_CON_IMPUESTO),Double.parseDouble(listCheck.get(y).PROPINA));
								finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
								
								conmysql.UpdateTableDocuments(fechaUno, listCheck.get(y).IDENTIFICACION_CLIENTE,
										listCheck.get(y).RAZON_SOCIAL_CLIENTE, listCheck.get(y).CLAVE_ACCESO, "Factura",
										listCheck.get(y).CLAVE_ACCESO + ".pdf", listCheck.get(y).CLAVE_ACCESO + "_Sign.xml",
										"1", fechaUno, listCheck.get(y).NOMBRE_COMERCIAL,String.valueOf(finalValorTotal));
								
								log.info("INSERTANDO EN MYSQL DOCUMENTO :"+ listCheck.get(y).CLAVE_ACCESO);

								conmysql.InsertUserPortal(listCheck.get(y).RAZON_SOCIAL_CLIENTE, "",
										listCheck.get(y).CORREO_CLIENTE, listCheck.get(y).IDENTIFICACION_CLIENTE,
										listCheck.get(y).IDENTIFICACION_CLIENTE);
								log.info("INSERTANDO EN MYSQL USUARIO:"+ listCheck.get(y).RAZON_SOCIAL_CLIENTE +"  "+listCheck.get(y).IDENTIFICACION_CLIENTE);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							

						

						// envio correo electronico
						//SendMail sm = new SendMail();
						// String
						// claveAccesoRet=listCabecera.get(y).CLAVE_ACCESO;

						//String cadUno = valuePath.trim() + listCheck.get(y).CLAVE_ACCESO + ".pdf";
						//String cadDos = "";
						//String cadTres = valuePath.trim() + listCheck.get(y).CLAVE_ACCESO + "_Sign.xml";

						/*
						 * sm.SendMailDocumento(cadUno, cadDos, cadTres,
						 * cadDos);
						 */

						// FIN ENVIO DE
						// DOCUMENTOS---------------------------------------------------------------

					} else if (infoResSri.get("estado").equals("RECIBIDA"))

					{
						System.out.println("ACTUALIZANDO RECIBIDA");
						try {
							resultUpdate = q.UpdateEstadoCheck(con, infoResSri.get("estado"), "", "",
									listCheck.get(y).CLAVE_ACCESO);
							log.info("ACTUALIZANDO ESTADO RECIBIDO :"+ listCheck.get(y).CLAVE_ACCESO);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}
					} else if (infoResSri.get("estado").equals("DEVUELTA"))

					{
						System.out.println("ACTUALIZANDO DEVUELTA");
						try {
							resultUpdate = q.UpdateEstadoCheck(con, infoResSri.get("estado"),
									infoResSri.get("identificador") + " " + infoResSri.get("mensaje"), "",
									listCheck.get(y).CLAVE_ACCESO);
							log.info("ACTUALIZANDO ESTADO DEVUELTA :"+ listCheck.get(y).CLAVE_ACCESO);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}
					} else if (infoResSri.get("estado").equals("NO AUTORIZADO"))

					{
						System.out.println("****COMPROBANTE:  " + infoResSri.get("comprobante"));

						BufferedWriter writer;
						try {
							writer = new BufferedWriter(new FileWriter(
									valuePath.trim() + listCheck.get(y).CLAVE_ACCESO + "_NO_AUTORIZADO" + ".xml"));
							writer.write(sbS.toString());
							writer.close();
							log.info("CREADO ARCHIVO DE NO AUTORIZADOS :"+ listCheck.get(y).CLAVE_ACCESO);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}
						try {
							resultUpdate = q.UpdateEstadoCheck(con, infoResSri.get("estado"),
									infoResSri.get("mensaje") + ";" + infoResSri.get("informacionAdicional"),
									infoResSri.get("fechaAutorizacion"), listCheck.get(y).CLAVE_ACCESO);
							log.info("ACTUALIZANDO ESTADO NO AUTORIZADO :"+ listCheck.get(y).CLAVE_ACCESO);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}
					} else {
						try {
							resultUpdate = q.UpdateEstadoCheck(con, infoResSri.get("estado"),
									infoResSri.get("identificador") + ";" + infoResSri.get("tipo") + ";"
											+ infoResSri.get("mensaje") + ";" + infoResSri.get("informacionAdicional"),
									"", listCheck.get(y).CLAVE_ACCESO);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							log.error("ERROR:", e1);
						}
					}
				}

			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				log.error("ERROR:", ex);
			} catch (IOException ext) {
				// TODO Auto-generated catch block
				ext.printStackTrace();
				log.error("ERROR:", ext);
			}

		}
	}

}

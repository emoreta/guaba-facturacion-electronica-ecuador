/*
 * Copyright (c) 2017 Extreme Media SA, All Rights Reserved.
 *
 * This code is confidential to Extreme Media and shall not be disclosed outside Extreme Media
 * without the prior written permission of the Technology Center.
 *
 * In the event that such disclosure is permitted the code shall not be copied
 * or distributed other than on a need-to-know basis and any recipients may be
 * required to sign a confidentiality undertaking in favor of Extreme Media SA.
 *
 * Extreme Media Enterprise Ecuador SA
 *
 */
package com.extreme.media.commons;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadXml implements IReadXml {

	private static final String FILES_FOLDER_ERROR = "filesFolderError";
	private static final String FILES_FOLDER_VALIDATED = "filesFolderValidated";
	private static final String FILES_FOLDER_AUTOMATIC = "filesFolderAutomatic";
	private static final String FILES_FOLDER_EXTERNAL = "filesFolderExternal";
	private static final String CONFIG = "config";
	
	private static final String FACTURA = "factura";


	public Document getDoc(String dir)  {
		// TODO Auto-generated method stub
		File fXmlFile = new File(dir);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc=null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public Document convertStringToDocument(String xmlStr) {
		// TODO Auto-generated method stub
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

	

	public Map<String, String> readXmlDocument(Document xmlDocument) {
		// TODO Auto-generated method stub
		Map<String, String> path = new HashMap<String, String>();

		xmlDocument.getDocumentElement().normalize();
		NodeList nList = xmlDocument.getElementsByTagName(CONFIG);

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				path.put(FILES_FOLDER_AUTOMATIC,
						element.getElementsByTagName(FILES_FOLDER_AUTOMATIC).item(0).getTextContent());
				path.put(FILES_FOLDER_VALIDATED,
						element.getElementsByTagName(FILES_FOLDER_VALIDATED).item(0).getTextContent());
				path.put(FILES_FOLDER_ERROR, element.getElementsByTagName(FILES_FOLDER_ERROR).item(0).getTextContent());
				path.put(FILES_FOLDER_EXTERNAL,
						element.getElementsByTagName(FILES_FOLDER_EXTERNAL).item(0).getTextContent());
			}
		}
		return path;
	}
	
	public Map<String, String> readXmlMapDocumentFactura(Document xmlDocument) {
		// TODO Auto-generated method stub
		Map<String, String> path = new HashMap<String, String>();

		xmlDocument.getDocumentElement().normalize();
		NodeList nList = xmlDocument.getElementsByTagName("property");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				path.put(element.getAttribute("name"),element.getAttribute("join"));
				/*path.put(FILES_FOLDER_VALIDATED,
						element.getElementsByTagName(FILES_FOLDER_VALIDATED).item(0).getTextContent());
				path.put(FILES_FOLDER_ERROR, element.getElementsByTagName(FILES_FOLDER_ERROR).item(0).getTextContent());
				path.put(FILES_FOLDER_EXTERNAL,
						element.getElementsByTagName(FILES_FOLDER_EXTERNAL).item(0).getTextContent());*/
				//System.out.println(element.getAttribute("name"));
				//System.out.println(element.getAttribute("join"));
				//System.out.println(element.getElementsByTagName("property").item(0).getNodeValue());
			}
		}
		return path;
	}
	
	public Map<String, String> readXmlMapAppConfig(Document xmlDocument) {
		// TODO Auto-generated method stub
		Map<String, String> path = new HashMap<String, String>();

		xmlDocument.getDocumentElement().normalize();
		NodeList nList = xmlDocument.getElementsByTagName("property");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;

				path.put(element.getAttribute("name"),element.getAttribute("value"));
				/*path.put(FILES_FOLDER_VALIDATED,
						element.getElementsByTagName(FILES_FOLDER_VALIDATED).item(0).getTextContent());
				path.put(FILES_FOLDER_ERROR, element.getElementsByTagName(FILES_FOLDER_ERROR).item(0).getTextContent());
				path.put(FILES_FOLDER_EXTERNAL,
						element.getElementsByTagName(FILES_FOLDER_EXTERNAL).item(0).getTextContent());*/
				//System.out.println(element.getAttribute("name"));
				//System.out.println(element.getAttribute("join"));
				//System.out.println(element.getElementsByTagName("property").item(0).getNodeValue());
			}
		}
		return path;
	}
	
	public Map<String, String> readXmlRespuestaSri(Document xmlDocument)
	  {
	    Map<String, String> path = new HashMap();
	    
	    xmlDocument.getDocumentElement().normalize();
	    NodeList nList = xmlDocument.getElementsByTagName("RespuestaRecepcionComprobante");
	    for (int temp = 0; temp < nList.getLength(); temp++)
	    {
	      Node nNode = nList.item(temp);
	      if (nNode.getNodeType() == 1)
	      {
	        Element element = (Element)nNode;
	        
	        path.put("estado", element.getElementsByTagName("estado").item(0).getTextContent());
	        System.out.println("estado read:" + element.getElementsByTagName("estado").item(0).getTextContent());
	        if (element.getElementsByTagName("estado").item(0).getTextContent().equals("AUTORIZADO"))
	        {
	          path.put("numeroAutorizacion", element.getElementsByTagName("numeroAutorizacion").item(0).getTextContent());
	          path.put("fechaAutorizacion", element.getElementsByTagName("fechaAutorizacion").item(0).getTextContent());
	          path.put("comprobante", element.getElementsByTagName("comprobante").item(0).getTextContent());
	        }
	        else if (!element.getElementsByTagName("estado").item(0).getTextContent().equals("RECIBIDA"))
	        {
	          if (!element.getElementsByTagName("estado").item(0).getTextContent().equals("NO AUTORIZADO")) {
	            if (element.getElementsByTagName("estado").item(0).getTextContent().equals("DEVUELTA"))
	            {
	              path.put("identificador", element.getElementsByTagName("identificador").item(0).getTextContent());
	              path.put("mensaje", element.getElementsByTagName("mensaje").item(0).getTextContent());
	            }
	            else
	            {
	              path.put("identificador", element.getElementsByTagName("identificador").item(0).getTextContent());
	              path.put("tipo", element.getElementsByTagName("tipo").item(0).getTextContent());
	              path.put("mensaje", element.getElementsByTagName("mensaje").item(0).getTextContent());
	              path.put("informacionAdicional", element.getElementsByTagName("informacionAdicional").item(0).getTextContent());
	            }
	          }
	        }
	      }
	    }
	    return path;
	  }

	public Map<String, String> readXmlRespuestaAutorizacionSri(Document xmlDocument)
	/*     */   {
	/* 250 */     Map<String, String> path = new HashMap();
	/*     */     
	/* 252 */     xmlDocument.getDocumentElement().normalize();
	/* 253 */     NodeList nList = xmlDocument.getElementsByTagName("RespuestaAutorizacionComprobante");
	/*     */     
	/* 255 */     for (int temp = 0; temp < nList.getLength(); temp++)
	/*     */     {
	/* 257 */       Node nNode = nList.item(temp);
	/*     */       
	/* 259 */       if (nNode.getNodeType() == 1)
	/*     */       {
	/* 261 */         Element element = (Element)nNode;
	/*     */         
	/* 263 */         if (!element.getElementsByTagName("numeroComprobantes").item(0).getTextContent().equals("0"))
	/*     */         {
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/*     */ 
	/* 273 */           path.put("estado", element.getElementsByTagName("estado").item(0).getTextContent());
	/* 274 */           System.out.println("estado read:" + element.getElementsByTagName("estado").item(0).getTextContent());
	/*     */           
	/* 276 */           if (element.getElementsByTagName("estado").item(0).getTextContent().equals("AUTORIZADO"))
	/*     */           {
	/* 278 */             path.put("numeroAutorizacion", element.getElementsByTagName("numeroAutorizacion").item(0).getTextContent());
	/* 279 */             path.put("fechaAutorizacion", element.getElementsByTagName("fechaAutorizacion").item(0).getTextContent());
	/* 280 */             path.put("comprobante", element.getElementsByTagName("comprobante").item(0).getTextContent());
	/*     */ 
	/*     */           }
	/* 283 */           else if (element.getElementsByTagName("estado").item(0).getTextContent().equals("NO AUTORIZADO"))
	/*     */           {
	/*     */ 
	/*     */ 
	/* 287 */             path.put("fechaAutorizacion", element.getElementsByTagName("fechaAutorizacion").item(0).getTextContent());
	/* 288 */             path.put("comprobante", element.getElementsByTagName("comprobante").item(0).getTextContent());
	/* 289 */             path.put("mensaje", element.getElementsByTagName("mensaje").item(0).getTextContent());
	/* 290 */             path.put("informacionAdicional", element.getElementsByTagName("informacionAdicional").item(0).getTextContent());
	/*     */ 
	/*     */           }
	/* 293 */           else if (element.getElementsByTagName("estado").item(0).getTextContent().equals("DEVUELTA"))
	/*     */           {
	/*     */ 
	/* 296 */             path.put("identificador", element.getElementsByTagName("identificador").item(0).getTextContent());
	/* 297 */             path.put("mensaje", element.getElementsByTagName("mensaje").item(0).getTextContent());
	/*     */ 
	/*     */           }
	/*     */           else
	/*     */           {
	/*     */ 
	/* 303 */             path.put("identificador", element.getElementsByTagName("identificador").item(0).getTextContent());
	/* 304 */             path.put("tipo", element.getElementsByTagName("tipo").item(0).getTextContent());
	/* 305 */             path.put("mensaje", element.getElementsByTagName("mensaje").item(0).getTextContent());
	/* 306 */             path.put("informacionAdicional", element.getElementsByTagName("informacionAdicional").item(0).getTextContent());
	/*     */           }
	/*     */         }
	/*     */       }
	/*     */     }
	/*     */     
	/*     */ 
	/*     */ 
	/* 314 */     return path;
	/*     */   }
	/*     */ 
	public Map<String, String> readXmlMapDocumentSign(Document xmlDocument)
	  {
	    Map<String, String> path = new TreeMap();
	    
	    xmlDocument.getDocumentElement().normalize();
	    NodeList nList = xmlDocument.getElementsByTagName("property");
	    for (int temp = 0; temp < nList.getLength(); temp++)
	    {
	      Node nNode = nList.item(temp);
	      if (nNode.getNodeType() == 1)
	      {
	        Element element = (Element)nNode;
	        System.out.println("firmas" + temp);
	        
	        path.put(element.getAttribute("ruc"), element.getAttribute("ubicacion") + "  " + element.getAttribute("contrasena")+ "  " + element.getAttribute("logo")+ "  " + element.getAttribute("template"));
	      }
	    }
	    return path;
	  }

}

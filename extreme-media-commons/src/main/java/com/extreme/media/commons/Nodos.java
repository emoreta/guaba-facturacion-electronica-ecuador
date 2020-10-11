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

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Nodos {
	public String getNodes(String rootNodo, String infoNodo, org.w3c.dom.Document doc){    
	      String resultNodo = "";         
	        org.w3c.dom.Element docEle = doc.getDocumentElement();
	        
	          
	        NodeList List = docEle.getElementsByTagName(rootNodo);
	         
	        if(List.getLength()>0)
	        {
	            Node node = List.item(0);           
	            if (node.getNodeType() == Node.ELEMENT_NODE) 
	            {
	                 org.w3c.dom.Element e = (org.w3c.dom.Element) node;
	                 NodeList nodeList = e.getElementsByTagName(infoNodo);
	                 resultNodo = nodeList.item(0).getChildNodes().item(0).getNodeValue();
	            } 
	        }
	            return resultNodo;
	    }

}

package com.extreme.media.transform;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Nodos {
	
	public String getNodes(String rootNodo, String infoNodo, org.w3c.dom.Document doc){    
	      String resultNodo = "";         
	        org.w3c.dom.Element docEle = doc.getDocumentElement();
	        
	          
	        NodeList studentList = docEle.getElementsByTagName(rootNodo);
	         
	        if(studentList.getLength()>0)
	        {
	            Node node = studentList.item(0);           
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

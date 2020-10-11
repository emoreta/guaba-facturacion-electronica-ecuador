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
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadListFileFolder {

	String path;

	public String getPath() {
		return path;
	}

	public Map<Long, String> readFileFolder(String nameNodeFileConfig)
			throws ParserConfigurationException, SAXException, IOException {
		Constants constants = new Constants();
		Map<String, String> dir = new HashMap<String, String>();
		Map<Long, String> filesFolder = new HashMap<Long, String>();

		String pathAutomatic;
		ReadXml r = new ReadXml();
		Document d;
		d = r.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);
		
		System.out.println("RUTA:"+constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);
		
		System.out.println("NODO:"+nameNodeFileConfig);
       
		dir = r.readXmlDocument(d);
		pathAutomatic = dir.get(nameNodeFileConfig);
		path = pathAutomatic;
		System.out.println("PATH:"+path);
	       

		int i = 0;
		File folder = new File(pathAutomatic.trim().replace("\\", "\\\\"));
		File[] files = folder.listFiles();
		for (File file : files) {

			filesFolder.put(Long.valueOf(i), file.getName());
			i++;
		}
		return filesFolder;
	}

}

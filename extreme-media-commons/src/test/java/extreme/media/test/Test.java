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
package extreme.media.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;

public class Test {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		Document d;
		Document m;
		Document conf;
		ReadXml rx = new ReadXml();
		Constants constants = new Constants();

		d = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.FILECONFIG);
		rx.readXmlDocument(d);
		System.out.println(rx.readXmlDocument(d));
		
		
		m = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.MAPCONFIG);
		
		
		System.out.println(rx.readXmlMapDocumentFactura(m));
		
		
		conf=rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		
		System.out.println(rx.readXmlMapAppConfig(conf));

	}

}

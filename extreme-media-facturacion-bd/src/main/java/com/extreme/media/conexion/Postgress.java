package com.extreme.media.conexion;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.decrypt.Decrypt;
import com.extreme.media.entity.ExternalPostgres;
import com.extreme.media.entity.FacturaDinamico;
import com.extreme.media.entity.FacturaFijo;
import com.extreme.media.send.SendDocument;
import com.extreme.media.struct.Struct;
import com.extreme.media.transform.ClaveAcceso;

import core.XAdESBESSignature;

public class Postgress {
	
	static final String TuIoPlMnFXxX = "Facturacion2018@Integracion";
	public Connection conn() throws Exception{
		
		Decrypt cd=new Decrypt();
		Map<String, String> pathConfig = new HashMap<String, String>();
		Document ed;
		ReadXml rx = new ReadXml();
		Constants constants = new Constants();
		ed = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
        pathConfig=rx.readXmlMapAppConfig(ed);
		String cadenaBd = pathConfig.get("bd");
		String decryptCadenaBd=Decrypt.Desencriptar(cadenaBd, TuIoPlMnFXxX);
		
		String string = decryptCadenaBd;
		String[] parts = string.split(";");
		String part0 = parts[0]; 
		String part1 = parts[1]; 
		String part2 = parts[2]; 
		
		
		
		ExternalPostgres ep=new ExternalPostgres();
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			//return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					part0, part1,
					part2);
			System.out.println("Connection OK");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			//return;

		}
		
		return connection;
		
		
	}
	

}

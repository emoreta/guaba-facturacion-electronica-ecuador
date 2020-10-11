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
package com.extreme.media.sql.connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
	java.sql.Connection c;
	private static final int countUser = 1;
	static Statement s;
	static ResultSet r;
	public Connection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver JDBC Gestion cargada");
			//c = DriverManager.getConnection("jdbc:sqlserver://DESARROLLOEM;database=SistemaItelbas", "sa", "sa");
			c = DriverManager.getConnection("jdbc:sqlserver://DESARROLLOUNO;database=SistemaItelbas", "sa", "sa");
			System.out.println("BD Gestion Conectada");
		} catch (SQLException ex) {
			// Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE,
			// null, ex);
		} catch (ClassNotFoundException ex) {
			// Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}
	
	public boolean authenticate(String login, String password) {
		int cc = 0;
		boolean state = false;
		String SQL = "SELECT count(*) as cc " + "FROM USUARIO where email_usuario='" + login + "' "
				+ "and contrasena_usuario='" + password + "'";
		try {
			s = c.createStatement();
			r = s.executeQuery(SQL);
			while (r.next()) {
				cc = Integer.parseInt(r.getString("cc"));
			}

			if (countUser == cc) {
				state = true;
			}
			System.out.println(state);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return state;
	}

}

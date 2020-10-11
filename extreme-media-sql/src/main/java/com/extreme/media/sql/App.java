package com.extreme.media.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	private static final int countUser = 1;
	static Statement s;
	static ResultSet r;

	public static void main(String[] args) {
		int cc = 0;
		boolean state=false;
		Connection con = new Connection();
		String SQL = "SELECT count(*) as cc " + "FROM USUARIO where email_usuario='edison.moreta@itelbas.com' "
				+ "and contrasena_usuario='sistema2016'";
		try {
			s = con.c.createStatement();
			r = s.executeQuery(SQL);
			while (r.next()) {
				cc = Integer.parseInt(r.getString("cc"));
			}
			
			if(countUser==cc)
			{
				state=true;
			}
			System.out.println(state);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//return state;

	}
}

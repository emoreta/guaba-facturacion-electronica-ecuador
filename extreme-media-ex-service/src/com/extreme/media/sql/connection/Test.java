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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.extreme.media.ex.util.Perfil;

public class Test {
	private java.sql.Connection connection = null;
	private static final int countUser = 1;
	static Statement s;
	static ResultSet r;

	private final String statement = "select * from usuario;";

	public Test() {
	}

	private java.sql.Connection getConnection() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver JDBC Gestion cargada");
			// connection =
			// DriverManager.getConnection("jdbc:sqlserver://DESARROLLOEM;database=SistemaItelbas",
			// "sa","sa");
			connection = DriverManager.getConnection("jdbc:sqlserver://DESARROLLOUNO;database=SistemaItelbas", "sa",
					"sa");
			System.out.println("BD Gestion Conectada");
		} catch (SQLException ex) {
			// Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE,
			// null, ex);
			System.out.println(ex);
		} catch (ClassNotFoundException ex) {
			// Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE,
			// null, ex);
			System.out.println(ex);
		}

		return connection;

	}

	/*
	 * Display the driver properties, database details
	 */

	public boolean newPerfil(Perfil perfil) {

		boolean state = false;
		// String sql = "";
		String sql = "INSERT INTO [dbo].[PERFIL](" + "[NOMBRE_PERFIL]" + ",[DESCRIPCION_PERFIL]" + ",[ESTADO_PERFIL]"
				+ ",[FECHA_PERFIL]" + ",[HOST_PERFIL])" + " VALUES" + "(" + "'" + perfil.getNombre() + "'" + ",'"
				+ perfil.getDescripcion() + "'" + ",1" + ",getdate()" + ",'prueba')";
		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			state = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state = false;
		}

		return state;
	}

	public boolean updatePerfilSql(Perfil perfil) {

		boolean state = false;
		// String sql = "";
		String sql = "UPDATE [dbo].[PERFIL]" + "SET [NOMBRE_PERFIL] = '" + perfil.getNombre() + "'"
				+ ",[DESCRIPCION_PERFIL] = '" + perfil.getDescripcion() + "'" + ",[ESTADO_PERFIL] = '"
				+ perfil.getEstado() + "',[FECHA_PERFIL] = getDate(),[HOST_PERFIL] = 'prueba' WHERE ID_PERFIL='"
				+ perfil.getId() + "'";
		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			state = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state = false;
		}

		return state;
	}

	public boolean deletePerfilSql(Perfil perfil) {

		boolean state = false;
		// String sql = "";
		String sql = "DELETE FROM [dbo].[PERFIL] WHERE ID_PERFIL='"+perfil.getId()+"'";

		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			state = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state = false;
		}

		return state;
	}

	public String PerfilResulset() {

		System.out.println("estoy intentandoconsultar");
		String result;
		Statement s;
		ResultSet r = null;

		String SQL = "SELECT  [ID_PERFIL]" + ",[NOMBRE_PERFIL] ,[DESCRIPCION_PERFIL]"
				+ ",[FECHA_PERFIL],[HOST_PERFIL],ESTADO_PERFIL FROM [PERFIL] ";
		connection = this.getConnection();
		try {
			s = connection.createStatement();
			r = s.executeQuery(SQL);
			result = getFormattedResult(r).toString();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "";
		}

		return result;
	}

	public boolean authenticate(String login, String password) {

		System.out.println("CREDENCIALES:" + login + " " + password);
		int cc = 0;
		boolean state = false;

		String SQL = "SELECT count(*) as cc FROM USUARIO where email_usuario='" + login + "' "
				+ "and contrasena_usuario='" + password + "'";

		System.out.println(SQL);

		connection = this.getConnection();
		try {
			s = connection.createStatement();
			r = s.executeQuery(SQL);
			while (r.next()) {
				cc = Integer.parseInt(r.getString("cc"));
				System.out.println("numero :" + cc);
			}

			if (countUser == cc) {
				state = true;
			}
			System.out.println(state);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return state;
	}

	public void displayDbProperties() {
		java.sql.DatabaseMetaData dm = null;
		java.sql.ResultSet result = null;
		try {
			connection = this.getConnection();
			if (connection != null) {
				dm = connection.getMetaData();
				System.out.println("Driver Information");
				System.out.println("\tDriver Name: " + dm.getDriverName());
				System.out.println("\tDriver Version: " + dm.getDriverVersion());
				System.out.println("\nDatabase Information ");
				System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
				System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());

				Statement select = connection.createStatement();
				result = select.executeQuery(statement);

				while (result.next()) {
					System.out.println("Nombre: " + result.getString(1) + "\n");
					System.out.println("Apellido: " + result.getString(2) + "\n");
					System.out.println("Dni: " + result.getString(3) + "\n");
				}
				result.close();
				result = null;
				closeConnection();
			} else
				System.out.println("Error: No active Connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
		dm = null;
	}

	private void closeConnection() {
		try {
			if (connection != null)
				connection.close();
			connection = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Test myDbTest = new Test();
		ResultSet r;
		myDbTest.authenticate("edison.moreta@itelbas.com", "sistema2016");

		// r=myDbTest.PerfilResulset();
		// convertResultSetToJson(r);
		// System.out.println(getFormattedResult(r).toString());

		// while (r.next()) {
		// System.out.println("Nombre: " + r.getString(1) + "\n");
		// System.out.println("Apellido: " + r.getString(2) + "\n");
		// System.out.println("Dni: " + r.getString(3) + "\n");
		// }

	}

	public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
		JSONObject j = new JSONObject();
		// System.out.println("longitud"+resultSet.);
		// System.out.println("res"+resultSet.getInt(2));
		while (resultSet.next()) {
			j.put("ID_PERFIL", resultSet.getInt(resultSet.findColumn("ID_PERFIL")));
			j.put("NOMBRE_PERFIL", resultSet.getString(resultSet.findColumn("NOMBRE_PERFIL")));
			j.put("DESCRIPCION_PERFIL", resultSet.getString(resultSet.findColumn("DESCRIPCION_PERFIL")));
			// j.put("ESTADO_PERFIL",resultSet.getInt(resultSet.findColumn("ESTADO_PERFIL")));
			j.put("FECHA_PERFIL", resultSet.getString(resultSet.findColumn("FECHA_PERFIL")));
			j.put("HOST_PERFIL", resultSet.getString(resultSet.findColumn("HOST_PERFIL")));
		}

		System.out.println(j.toString());
		return j.toString();

	}

	public static List<JSONObject> getFormattedResult(ResultSet rs) {
		List<JSONObject> resList = new ArrayList<JSONObject>();
		try {
			// get column names
			ResultSetMetaData rsMeta = rs.getMetaData();
			int columnCnt = rsMeta.getColumnCount();
			List<String> columnNames = new ArrayList<String>();
			for (int i = 1; i <= columnCnt; i++) {
				columnNames.add(rsMeta.getColumnName(i).toUpperCase());
			}

			while (rs.next()) { // convert each object to an human readable JSON
								// object
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= columnCnt; i++) {
					String key = columnNames.get(i - 1);
					String value = rs.getString(i);
					obj.put(key, value);
				}
				resList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resList;
	}

}

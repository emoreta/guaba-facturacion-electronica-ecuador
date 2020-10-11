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
package com.extreme.media.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql {
	
	
	
	public static Connection GetConnection()
	{
	 Connection conexion=null;
	  
	    try
	    {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String url = "jdbc:sqlserver://DEVELOPERVII-PC\\MSSQLSERVER_2;databaseName=RECARGAS;user=sa;password=sa;";
	        conexion= DriverManager.getConnection(url);
	        System.out.println("CONEXION REALIZADA CON EXITO");
	    }
	    catch(ClassNotFoundException ex)
	    {
	        //JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    	System.out.println("CONEXION FALLIDA "+ex.toString());
	    	conexion=null;
	    }
	    catch(SQLException ex)
	    {
	       // JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    	System.out.println("CONEXION FALLIDA "+ex.toString());
	    	conexion=null;
	    }
	    catch(Exception ex)
	    {
	       // JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
	    	System.out.println("CONEXION FALLIDA "+ex.toString());
	    	conexion=null;
	    }
	    finally
	    {
	        return conexion;
	    }
	}

}

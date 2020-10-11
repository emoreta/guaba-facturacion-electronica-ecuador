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

import java.io.IOException;
import java.sql.Connection;

import com.extreme.media.entity.ExternalPostgres;

public class BdCon {
	
	static Connection connection=null;
	
	public BdCon(BdTypes bd) throws Exception {
		switch (bd) {
		case MYSQL:
			System.out.println("YOU ARE USED MYSQL");
			break;
		case SQL:
			System.out.println("YOU ARE USED SQL");
			break;
		case ORACLE:
			System.out.println("YOU ARE USED ORACLE");
			break;
		case SYBASE:
			System.out.println("YOU ARE USED SYBASE");
			break;
		case POSTGRESS:
			System.out.println("YOU ARE USED POSTGRESS");
			Postgress p=new Postgress();
			try {
				connection=p.conn();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("NO UTILIZAS NINGUNA BD IMPOSIBLE INICIAR SISTEMA");
			break;
		}

	}
	public Connection GetConnectionPostgress()
	{
		return connection;
	}
	

}

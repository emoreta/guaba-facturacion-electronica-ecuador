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

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String typeBd = "POSTGRESS";
		BdCon bd;
		if (typeBd == "SQL") {
			try {
				bd = new BdCon(BdTypes.SQL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeBd == "ORACLE") {
			try {
				bd = new BdCon(BdTypes.SQL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeBd == "SYBASE") {
			try {
				bd = new BdCon(BdTypes.SYBASE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeBd == "MYSQL") {
			try {
				bd = new BdCon(BdTypes.MYSQL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (typeBd == "POSTGRESS") {
			try {
				bd = new BdCon(BdTypes.POSTGRESS);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("You do not have a connection BD,CONTACT ADMINISTRADOR");
		}

	}

}

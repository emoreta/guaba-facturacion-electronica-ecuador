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

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Util {
	
	public double Redondear(double numero)
    {
           return Math.rint(numero*100)/100;
    }
	public String ConvertFechaEmision(String fechaOrigen)
	{
		
		String[] parts;
		String fecha="";
		if(fechaOrigen!="")
		{
			
			parts= fechaOrigen.split("-");
			//fecha=String.format("%02d",parts[2])+"-"+String.format("%02d", parts[1])+"-"+parts[0];
			System.out.println("FECHA EMISION:"+parts[2]+"-"+parts[1]+"-"+parts[0]);
			fecha=parts[2]+"/"+parts[1]+"/"+parts[0];
		}
		return fecha;
	}
	
	public String getFechaActualFormatoUno()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha=String.format("%02d",c1.get(Calendar.DATE))+"/"+String.format("%02d", c1.get(Calendar.MONTH)+1)+"/"+annio;
		return fecha;
	}
	public String getFechaActualFormatoTres()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fecha=annio+"-"+String.format("%02d",c1.get(Calendar.DATE))+"-"+String.format("%02d", c1.get(Calendar.MONTH)+1);
		return fecha;
	}
	public String getFechaActualFormatoDos()
	{
		Calendar c1 = new GregorianCalendar();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH));
		String annio = Integer.toString(c1.get(Calendar.YEAR));
		String fechaFormat=String.format("%02d",c1.get(Calendar.DATE))+String.format("%02d", c1.get(Calendar.MONTH)+1)+annio;
		return fechaFormat;
	}

}

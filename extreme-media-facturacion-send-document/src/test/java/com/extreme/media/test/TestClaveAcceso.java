package com.extreme.media.test;

import com.extreme.media.transform.ClaveAcceso;

public class TestClaveAcceso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fechaEmision="10092018";//ddmmaaaaa 8
		String tipoComprobante="01";//Factura tabla 3
		String numeroRuc="1718921362001";//ruc
		String tipoAmbiente="1";//pruebas o produccion
		String serie="001001";//serie de facturas
		String numeroComprobante="000003002";//secuencial desde 3000 para pruebas 
		String codigoNumerico="12345678";//ddmmaaaaa
		String tipoEmision="1";//ddmmaaaaa
		String digitoVerificador="";//modulo11
		
		
		String claveAcceso;//retorno
		ClaveAcceso c = new ClaveAcceso();
        claveAcceso= c.generaClave(fechaEmision, tipoComprobante,
        		numeroRuc, tipoAmbiente, serie, numeroComprobante, 
        		codigoNumerico, tipoEmision);
        
        System.out.println("CLAVE ACCESO:"+claveAcceso);
        

	}

}

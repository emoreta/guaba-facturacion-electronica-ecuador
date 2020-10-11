package com.provisional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.extreme.media.conexion.BdCon;
import com.extreme.media.conexion.BdTypes;
import com.extreme.media.postgress.InformacionFactura;
import com.extreme.media.transform.ClaveAcceso;

public class UpdateClaves {

	public static void ListRuc(Connection connection)
	{
		List<String> liste = new ArrayList<String>();
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement stq;
			Statement st;
			Statement sta;

			try {
				stq = connection.createStatement();

				ResultSet rsc = stq.executeQuery("SELECT SECUENCIAL FROM mtd.pos_cab_fact_electronica");
				
				int countVal=0;
				
				while (rsc.next()) {
					//countVal=Integer.parseInt(rsc.getString(1));
					liste.add(rsc.getString(1));
					
				}
				
				String fechaEmision="10102018";//ddmmaaaaa 8
				String tipoComprobante="01";//Factura tabla 3
				String numeroRuc="1718921362001";//ruc
				String tipoAmbiente="1";//pruebas o produccion
				String serie="001001";//serie de facturas
				String numeroComprobante="000006130";//secuencial desde 3000 para pruebas 
				String codigoNumerico="12345678";//ddmmaaaaa
				String tipoEmision="1";//ddmmaaaaa
				String digitoVerificador="";//modulo11
				
				
				int secuencia=6130;
				String numbercompleto="";
				for (int i=0;i<=liste.size();i++)
				{
					numbercompleto="00000"+String.valueOf(secuencia);
					String claveAcceso;//retorno
					ClaveAcceso c = new ClaveAcceso();
			        claveAcceso= c.generaClave(fechaEmision, tipoComprobante,
			        		numeroRuc, tipoAmbiente, serie, numbercompleto, 
			        		codigoNumerico, tipoEmision);
					
					st = connection.createStatement();
					String sql = "update mtd.pos_cab_fact_electronica "+
"set estado_documento='INICIAL',fecha_emision='2018-10-10',secuencial='"+secuencia+"',clave_acceso='"+claveAcceso+"' "+
"where fecha_emision='2018-10-10' "
//+ "and secuencial='"+liste.get(i)+"' "
+ "and secuencial='66090' "
		+ "AND identificacion_empresa='1712715463001'";
					
					System.out.println(liste.get(i));
					
					st.executeUpdate(sql);
					
					sta = connection.createStatement();
					String sqla = "update mtd.pos_det_fact_electronica "+
"set secuencial='"+secuencia+"' "+
"where secuencial='"+liste.get(i)+"' and establecimiento='001' and punto_emision='001'";
					
					sta.executeUpdate(sqla);
					
					
					
					secuencia++;
				}
			
					} catch (Exception e) {
						
					}
			
		}
		

	}
	
	public static void main(String[] args) {
		
		Connection con = null;


		BdCon bd = new BdCon(BdTypes.POSTGRESS);
		// creando coneccion a postgress
		con = bd.GetConnectionPostgress();
		// TODO Auto-generated method stub
		
		ListRuc(con);
		

	}

}

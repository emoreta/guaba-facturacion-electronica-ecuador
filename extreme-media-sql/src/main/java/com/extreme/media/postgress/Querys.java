package com.extreme.media.postgress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.extreme.media.entity.ExternalPostgres;
import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.extreme.media.entityes.ExternalDataDetalleNc;
import com.extreme.media.entityes.ExternalDataNc;

public class Querys {

	// Connection connection = null;

	public List<String> ListRuc(Connection connection) throws SQLException {
		List<String> liste = new ArrayList<String>();
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st
					.executeQuery("SELECT distinct(identificacion_empresa)as ruc FROM mtd.POS_CAB_FACT_ELECTRONICA");
			while (rsc.next()) {
				liste.add(rsc.getString(1));

			}
		}

		return liste;
	}

	public boolean UpdateEstadoCheck(Connection connection, String estado, String motivoRechazo,
			String fechaAutorizacion, String claveAcceso) throws SQLException {
		boolean val = false;
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			System.out.println("ACTUALIZANDO REGISTROS");
			if (estado.equals("AUTORIZADO")) {

				st = connection.createStatement();
				String sql = "update mtd.POS_CAB_FACT_ELECTRONICA " + "set " + "estado_documento='" + estado + "', "
						+ "fecha_envio_sri='" + fechaAutorizacion + "' " + "WHERE CLAVE_ACCESO='" + claveAcceso + "' ";

				st.executeUpdate(sql);
				// st.close();
				// connection.close();
			} else if (estado.equals("DEVUELTA")) {
				st = connection.createStatement();
				String sql = "update mtd.POS_CAB_FACT_ELECTRONICA " + "set " + "estado_documento='" + estado + "', "
						+ "motivo_rechazo='" + motivoRechazo + "' " + "WHERE CLAVE_ACCESO='" + claveAcceso + "' ";
				st.executeUpdate(sql);

			} else if (estado.equals("RECIBIDA")) {

				st = connection.createStatement();
				String sql = "update mtd.POS_CAB_FACT_ELECTRONICA " + "set " + "estado_documento='" + estado + "', "
						+ "motivo_rechazo='" + motivoRechazo + "' " + "WHERE CLAVE_ACCESO='" + claveAcceso + "' ";
				st.executeUpdate(sql);
			} else {
				st = connection.createStatement();
				String sql = "update mtd.POS_CAB_FACT_ELECTRONICA " + "set " + "estado_documento='" + estado + "', "
						+ "motivo_rechazo='" + motivoRechazo + "' " + "WHERE CLAVE_ACCESO='" + claveAcceso + "' ";
				st.executeUpdate(sql);

			}

			val = true;

		}

		return val;
	}
	
	public boolean UpdateEstadoCheckNc(Connection connection, String estado, String motivoRechazo,
			String fechaAutorizacion, String claveAcceso) throws SQLException {
		boolean val = false;
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			System.out.println("ACTUALIZANDO REGISTROS NC");
			if (estado.equals("AUTORIZADO")) {

				st = connection.createStatement();
				String sql = "update mtd.pos_nc_fact_electronica " + "set " + "estado_envio_sri='" + estado + "', "
						+ "fecha_envio_sri='" + fechaAutorizacion + "' " + "WHERE clave_acceso_sri='" + claveAcceso + "' ";

				st.executeUpdate(sql);
				// st.close();
				// connection.close();
			} else if (estado.equals("DEVUELTA")) {
				st = connection.createStatement();
				String sql = "update mtd.pos_nc_fact_electronica " + "set " + "estado_envio_sri='" + estado + "', "
						+ "motivo_rechazo_sri='" + motivoRechazo + "' " + "WHERE clave_acceso_sri='" + claveAcceso + "' ";
				st.executeUpdate(sql);

			} else if (estado.equals("RECIBIDA")) {

				st = connection.createStatement();
				String sql = "update mtd.pos_nc_fact_electronica " + "set " + "estado_envio_sri='" + estado + "', "
						+ "motivo_rechazo_sri='" + motivoRechazo + "' " + "WHERE clave_acceso_sri='" + claveAcceso + "' ";
				st.executeUpdate(sql);
			} else {
				st = connection.createStatement();
				String sql = "update mtd.pos_nc_fact_electronica " + "set " + "estado_envio_sri='" + estado + "', "
						+ "motivo_rechazo_sri='" + motivoRechazo + "' " + "WHERE clave_acceso_sri='" + claveAcceso + "' ";
				st.executeUpdate(sql);

			}

			val = true;

		}

		return val;
	}

	public boolean UpdateEstado(Connection connection, String estado, String motivoRechazo,
			String identificacionEmpresa, String establecimiento, String puntoEmision, String secuencial)
			throws SQLException {
		boolean val = false;
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			System.out.println("ACTUALIZANDO RESGISTROS");

			st = connection.createStatement();
			String sql = "update mtd.POS_CAB_FACT_ELECTRONICA " + "set " + "estado_documento='" + estado + "', "
					+ "motivo_rechazo='" + motivoRechazo + "' " + "WHERE SECUENCIAL='" + secuencial + "'  "
					+ "AND ESTABLECIMIENTO='" + establecimiento + "' " + "AND IDENTIFICACION_EMPRESA='"
					+ identificacionEmpresa + "' " + "AND PUNTO_EMISION='" + puntoEmision + "'";
			st.executeUpdate(sql);
			// st.close();
			// connection.close();

			val = true;

		}

		return val;
	}
	public boolean UpdateEstadoNc(Connection connection, String estado, String motivoRechazo,
			String identificacionEmpresa, String establecimiento, String puntoEmision, String secuencial)
			throws SQLException {
		boolean val = false;
		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			System.out.println("ACTUALIZANDO RESGISTROS NC");

			st = connection.createStatement();
			String sql = "update mtd.pos_nc_fact_electronica " + "set " + "estado_envio_sri='" + estado + "', "
					+ "motivo_rechazo_sri='" + motivoRechazo + "' " + "WHERE secuencial_nc='" + secuencial + "'  "
					+ "AND establecimiento='" + establecimiento + "' " + "AND identificacion_empresa='"
					+ identificacionEmpresa + "' " + "AND punto_emision='" + puntoEmision + "'";
			st.executeUpdate(sql);
			// st.close();
			// connection.close();

			val = true;

		}

		return val;
	}

	// ENVIA DE ACUERDO A LO QUE VENGA,SI VIENE NOT
	public List<ExternalData> QueryInfoFactDinamico(Connection connection, String fecha, String estado, String ruc,
			String claveAcceso) throws SQLException {
		String condicion = "";
		List<ExternalData> liste = new ArrayList<ExternalData>();

		System.out.println(fecha + "  " + estado + "  " + ruc + " " + claveAcceso);
		if (fecha != "NOT" && estado == "NOT" && ruc == "NOT" && claveAcceso == "NOT") {
			condicion = "   where FECHA_EMISION='" + fecha + "' and estado_documento NOT IN('AUTORIZADO')";
		} else if (fecha.equals("NOT") && estado != "NOT" && ruc.equals("NOT") && claveAcceso.equals("NOT")) {
			condicion = "  where estado_documento='" + estado + "' ";
		} else if (fecha == "NOT" && estado == "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			condicion = " where IDENTIFICACION_EMPRESA='" + ruc + "' and estado_documento NOT IN('AUTORIZADO')";
		} else if (fecha != "NOT" && estado != "NOT" && ruc == "NOT" && claveAcceso == "NOT") {
			condicion = "   where FECHA_EMISION='" + fecha + "' and estado_documento  IN('" + estado + "')";
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			condicion = "   where IDENTIFICACION_EMPRESA='" + ruc + "'  and estado_documento  IN('" + estado + "')";
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			condicion = "   where IDENTIFICACION_EMPRESA='" + ruc + "'  and estado_documento  IN('" + estado + "')";
		} else if (fecha != "NOT" && estado == "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			condicion = "   where FECHA_EMISION='" + fecha + "' AND IDENTIFICACION_EMPRESA='" + ruc + "'";
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			condicion = "   where  estado_documento  IN('" + estado + "')  AND IDENTIFICACION_EMPRESA='" + ruc + "'";
		} else if (fecha.equals("NOT") && estado.equals("NOT") && ruc.equals("NOT") && claveAcceso != "NOT") {

			condicion = "   where  CLAVE_ACCESO  IN('" + claveAcceso + "')";

		} else if (fecha.equals("NOT") && estado.equals("NOT") && ruc.equals("NOT") && claveAcceso.equals("NOT")) {
			condicion = "   IMPOSIBLE PROCESAR SIN CONDICION";
		} else {
			condicion = "   IMPOSIBLE PROCESAR SIN CONDICION";
		}
		System.out.println("CONDICION:" + condicion);

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					+ condicion // solicito cliente el envio por estado asi que
								// se desarrollo dinamismo
					+ " ORDER BY CAST(SECUENCIAL AS FLOAT)  ,FECHA_EMISION ASC");//

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}

		}
		return liste;

	}

	// ENVIA DE ACUERDO A LO QUE VENGA,SI VIENE NOT
	public List<ExternalDataNc> QueryInfoNcDinamico(Connection connection, String fecha, String estado, String ruc,
			String claveAcceso) throws SQLException {

		// UNIR LA CONSULTA PARA QUE ESTA AQUI CON LA DE FACTURAS PARA TRAER LA
		// INFORMACION

		System.out.println("INFORMACION NOTAS DE CREDITO");
		String condicion = "";
		List<ExternalDataNc> liste = new ArrayList<ExternalDataNc>();

		System.out.println(fecha + "  " + estado + "  " + ruc + " " + claveAcceso);
		
		if (fecha != "NOT" && estado == "NOT" && ruc == "NOT" && claveAcceso == "NOT") {
			
			condicion = "   where fecha_emision='" + fecha + "' and estado_envio_sri NOT IN('AUTORIZADO')";
			
		} else if (fecha.equals("NOT") && estado != "NOT" && ruc.equals("NOT") && claveAcceso.equals("NOT")) {
			
			condicion = "  and nc.estado_envio_sri='" + estado + "' ";
			
		} else if (fecha == "NOT" && estado == "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			
			condicion = " and nc.identificacion_empresa='" + ruc + "' and nc.estado_envio_sri NOT IN('AUTORIZADO')";
			
		} else if (fecha != "NOT" && estado != "NOT" && ruc == "NOT" && claveAcceso == "NOT") {
			
			condicion = "   and nc.fecha_emision='" + fecha + "' and nc.estado_envio_sri  IN('" + estado + "')";
			
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			
			condicion = "   and nc.identificacion_empresa='" + ruc + "'  and nc.estado_envio_sri  IN('" + estado + "')";
			
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			
			condicion = "   and nc.identificacion_empresa='" + ruc + "'  and nc.estado_envio_sri  IN('" + estado + "')";
			
		} else if (fecha != "NOT" && estado == "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			
			condicion = "   and nc.fecha_emision='" + fecha + "' AND nc.identificacion_empresa='" + ruc + "'";
			
		} else if (fecha == "NOT" && estado != "NOT" && ruc != "NOT" && claveAcceso == "NOT") {
			
			condicion = "   and  nc.estado_envio_sri  IN('" + estado + "')  AND nc.identificacion_empresa='" + ruc + "'";
			
		} else if (fecha.equals("NOT") && estado.equals("NOT") && ruc.equals("NOT") && claveAcceso != "NOT") {

			condicion = "   and  nc.clave_acceso_sri  IN('" + claveAcceso + "')";

		} else if (fecha.equals("NOT") && estado.equals("NOT") && ruc.equals("NOT") && claveAcceso.equals("NOT")) {
			
			condicion = "   IMPOSIBLE PROCESAR SIN CONDICION";
			
		} else {
			
			condicion = "   IMPOSIBLE PROCESAR SIN CONDICION";
		}
		System.out.println("CONDICION:" + condicion);
		
		

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery(
							 " SELECT fact.IDENTIFICACION_EMPRESA,fact.ESTABLECIMIENTO,fact.PUNTO_EMISION ,"
							+ " fact.SECUENCIAL,fact.CLAVE_ACCESO,fact.RAZON_SOCIAL_EMPRESA,fact.NOMBRE_COMERCIAL,"
							+ " fact.CODIGO_DOCUMENTO,fact.DIRECCION_EMPRESA,fact.FECHA_EMISION,fact.DIRECCION_ESTABLECIMIENTO,"
							+ " CASE WHEN fact.TIPO_ID_CLIENTE='C' AND fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=10 THEN '05' "
							+ " WHEN fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=13 THEN '04' "
							+ " WHEN fact.RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
							+ " WHEN fact.TIPO_ID_CLIENTE='P' THEN '06'     ELSE '00'     END AS TIPO_ID_CLIENTE,"
							+ "  fact.RAZON_SOCIAL_CLIENTE,fact.IDENTIFICACION_CLIENTE,fact.DIRECCION_CLIENTE,"
							+ "  fact.TOTAL_SIN_IMPUESTO,fact.TOTAL_DESCUENTO,fact.TOTAL_CON_IMPUESTO,fact.TOTAL_IMPUESTO,"
							+ "  fact.FECHA_ENVIO_SRI,fact.ESTADO_DOCUMENTO,"
							+ "  fact.MOTIVO_RECHAZO,fact.CONTRIBUYENTE_ESPECIAL,fact.OBLIGADO_CONTABILIDAD,fact.MONEDA,fact.TELEFONO_CLIENTE,fact.CORREO_CLIENTE,"
							+ "  fact.CODIGO_IMPUESTO,fact.CODIGO_IMPUESTO_PORCENTAJE,fact.TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,fact.TARIFA,fact.PROPINA, "
							+"   nc.identificacion_empresa,nc.establecimiento,nc.punto_emision,nc.secuencial_factura,nc.secuencial_nc,"
						    + " nc.tipo_documento_nc,nc.fecha_emision,nc.motivo_emision,nc.clave_acceso_sri,nc.motivo_rechazo_sri "
							+ " FROM mtd.pos_cab_fact_electronica  fact ,mtd.pos_nc_fact_electronica  nc"
							+ " where "
							+ "  fact.secuencial=nc.secuencial_factura "
							+ condicion );

			

			while (rsc.next()) {

				ExternalDataNc ep = new ExternalDataNc();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);
				
				ep.identificacion_empresa_nc= rsc.getString(33);
				ep.establecimiento_nc= rsc.getString(34);
				ep.punto_emision_nc= rsc.getString(35);
				ep.secuencial_factura_nc= rsc.getString(36);
				ep.secuencial_nc= rsc.getString(37);
				ep.tipo_documento_nc= rsc.getString(38);
				ep.fecha_emision_nc= rsc.getString(39);
				ep.motivo_emision_nc= rsc.getString(40);
				ep.clave_acceso_sri_nc= rsc.getString(41);
				ep.motivo_rechazo_sri_nc= rsc.getString(42);
				System.out.println("CLAVE ACCESO NC:"+ep.clave_acceso_sri_nc);

				liste.add(ep);
			}

		}
		return liste;

	}
	public List<ExternalDataDetalleNc> QueryInfoDetNc(Connection connection, String identificacionEmpresa,
			String establecimiento, String puntoEmision, String secuencial) throws SQLException {

		List<ExternalDataDetalleNc> listd = new ArrayList<ExternalDataDetalleNc>();

		System.out.println(identificacionEmpresa + "***" + establecimiento + "***" + puntoEmision + "***" + secuencial);

		// ExternalDataDetalle ed = new ExternalDataDetalle();

		if (connection != null) {
			System.out.println("CONSULTANDO DETALLE!");

			Statement stD;

			stD = connection.createStatement();

			ResultSet rscD = stD.executeQuery("SELECT  IDENTIFICACION_EMPRESA ,ESTABLECIMIENTO  ,"
					+ "PUNTO_EMISION  ,SECUENCIAL ,DESCRIPCION_PRODUCTO,CANTIDAD_PRODUCTO ,"
					+ "PRECIO_UNITARIO,VALOR_DESCUENTO  ,TOTAL_SIN_IMPUESTO,CODIGO_PRINCIPAL,CODIGO_AUXILIAR "
					+ "FROM mtd.POS_DET_FACT_ELECTRONICA WHERE SECUENCIAL='" + secuencial + "' "
					+ "AND ESTABLECIMIENTO='" + establecimiento + "' " + "AND IDENTIFICACION_EMPRESA='"
					+ identificacionEmpresa + "' " + "AND PUNTO_EMISION='" + puntoEmision + "'");

			while (rscD.next()) {
				System.out.println("RECORRIENDO DETALLE");

				ExternalDataDetalleNc edD = new ExternalDataDetalleNc();

				edD.IDENTIFICACION_EMPRESA = rscD.getString(1);
				edD.ESTABLECIMIENTO = rscD.getString(2);
				edD.PUNTO_EMISION = rscD.getString(3);
				edD.SECUENCIAL = rscD.getString(4);
				edD.DESCRIPCION_PRODUCTO = rscD.getString(5);
				edD.CANTIDAD_PRODUCTO = rscD.getString(6);
				edD.PRECIO_UNITARIO = rscD.getString(7);
				edD.VALOR_DESCUENTO = rscD.getString(8);
				edD.TOTAL_SIN_IMPUESTO = rscD.getString(9);

				edD.CODIGO_PRINCIPAL = rscD.getString(10);
				edD.CODIGO_AUXILIAR = rscD.getString(11);

				listd.add(edD);
			}
			// stD.close();
			// connection.close();

		}
		System.out.println("DETALLE TAMAÑO:" + listd.size());
		return listd;

	}
	public List<ExternalDataDetalleNc> QueryInfoDetNc(Connection connection, List<ExternalDataNc> edata)
			throws SQLException {

		List<ExternalDataDetalleNc> listd = new ArrayList<ExternalDataDetalleNc>();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			System.out.println("TAMAÑO:" + edata.size());

			for (int y = 0; y < edata.size(); y++) {

				System.out.println("VAR:" + edata.get(y).SECUENCIAL + "--" + edata.get(y).ESTABLECIMIENTO + "--"
						+ edata.get(y).IDENTIFICACION_EMPRESA + "--" + edata.get(y).PUNTO_EMISION);

				ResultSet rsc = st
						.executeQuery("SELECT  IDENTIFICACION_EMPRESA ," + "ESTABLECIMIENTO  ," + "PUNTO_EMISION  ,"
								+ "SECUENCIAL ," + "DESCRIPCION_PRODUCTO," + "CANTIDAD_PRODUCTO ," + "PRECIO_UNITARIO,"
								+ "VALOR_DESCUENTO  ," + "TOTAL_SIN_IMPUESTO,CODIGO_PRINCIPAL,CODIGO_AUXILIAR   "
								+ "FROM mtd.POS_DET_FACT_ELECTRONICA WHERE SECUENCIAL='" + edata.get(y).SECUENCIAL
								+ "' " + "AND ESTABLECIMIENTO='" + edata.get(y).ESTABLECIMIENTO + "' "
								+ "AND IDENTIFICACION_EMPRESA='" + edata.get(y).IDENTIFICACION_EMPRESA
								+ "' AND PUNTO_EMISION='" + edata.get(y).PUNTO_EMISION + "'");

				while (rsc.next()) {
					ExternalDataDetalleNc ed = new ExternalDataDetalleNc();
					System.out.println("DETALLE FACTURA:" + rsc.getString(1) + "    " + rsc.getString(7));

					ed.IDENTIFICACION_EMPRESA = rsc.getString(1);
					ed.ESTABLECIMIENTO = rsc.getString(2);
					ed.PUNTO_EMISION = rsc.getString(3);
					ed.SECUENCIAL = rsc.getString(4);
					ed.DESCRIPCION_PRODUCTO = rsc.getString(5);
					ed.CANTIDAD_PRODUCTO = rsc.getString(6);
					ed.PRECIO_UNITARIO = rsc.getString(7);
					ed.VALOR_DESCUENTO = rsc.getString(8);
					ed.TOTAL_SIN_IMPUESTO = rsc.getString(9);

					ed.CODIGO_PRINCIPAL = rsc.getString(10);
					ed.CODIGO_AUXILIAR = rsc.getString(11);

					listd.add(ed);
				}
			}

		}
		return listd;
	}

	public List<ExternalData> QueryInfoFact(Connection connection, String fecha) throws SQLException {

		List<ExternalData> liste = new ArrayList<ExternalData>();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06'  " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					+ "  where FECHA_EMISION='" + fecha + "' and estado_documento!='AUTORIZADO'"// modificado
																								// por
																								// cliente
																								// no
																								// quiere
																								// que
																								// sea
																								// por
																								// fecha
					+ " ORDER BY CAST(SECUENCIAL AS FLOAT)  ,FECHA_EMISION ASC");//

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}

		}
		return liste;

	}

	public List<ExternalData> QueryInfoFactReproceso(Connection connection, String fecha, String Ruc)
			throws SQLException {

		List<ExternalData> liste = new ArrayList<ExternalData>();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					+ "  where FECHA_EMISION='" + fecha + "' and  IDENTIFICACION_EMPRESA='" + Ruc
					+ "' AND ESTADO_DOCUMENTO!='AUTORIZADO' "
					+ " ORDER BY CAST(SECUENCIAL AS FLOAT)  ,FECHA_EMISION ASC");//

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}
			// st.close();
			// connection.close();

		}
		return liste;

	}

	public List<ExternalData> QueryInfoFactTranfer(Connection connection, String fecha,String claveAcceso) throws SQLException {

		System.out.println("QUERY INFORMACION VALIDAR");
		List<ExternalData> liste = new ArrayList<ExternalData>();
		
		String condicion = "";
		
		if (fecha.equals("NOT") && claveAcceso!="NOT" ) {

					condicion = "   where  CLAVE_ACCESO  IN('" + claveAcceso + "') ";
		}
		else if (fecha!="NOT" && claveAcceso.equals("NOT"))
		{
			        condicion = "   where  FECHA_EMISION='" + fecha + "' ";
		}
		else
		{
			condicion="ERROR";
		}
		System.out.println("CONDICION:"+condicion);

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					//+ "  where FECHA_EMISION='" + fecha + "' "
					+condicion
					+ "AND estado_documento in ('AUTORIZADO')  AND  CORREO_CLIENTE!='NINGUNO'");

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}
			// st.close();
			// connection.close();

		}
		return liste;

	}
	public List<ExternalDataNc> QueryInfoFactTranferNc(Connection connection, String fecha,String claveAcceso) throws SQLException {

		System.out.println("QUERY INFORMACION VALIDAR");
		List<ExternalDataNc> liste = new ArrayList<ExternalDataNc>();
		
		String condicion = "";
		
		if (fecha.equals("NOT") && claveAcceso!="NOT" ) {

					condicion = "   where  nc.clave_acceso_sri  IN('" + claveAcceso + "')  ";
		}
		else if (fecha!="NOT" && claveAcceso.equals("NOT"))
		{
			        condicion = "   where  nc.fecha_emision='" + fecha + "'  ";
		}
		else
		{
			condicion="ERROR";
		}
		System.out.println("CONDICION:"+condicion);

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery(
					 " SELECT fact.IDENTIFICACION_EMPRESA,fact.ESTABLECIMIENTO,fact.PUNTO_EMISION ,"
					+ " fact.SECUENCIAL,fact.CLAVE_ACCESO,fact.RAZON_SOCIAL_EMPRESA,fact.NOMBRE_COMERCIAL,"
					+ " fact.CODIGO_DOCUMENTO,fact.DIRECCION_EMPRESA,fact.FECHA_EMISION,fact.DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN fact.TIPO_ID_CLIENTE='C' AND fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ " WHEN fact.TIPO_ID_CLIENTE='P' THEN '06'     ELSE '00'     END AS TIPO_ID_CLIENTE,"
					+ "  fact.RAZON_SOCIAL_CLIENTE,fact.IDENTIFICACION_CLIENTE,fact.DIRECCION_CLIENTE,"
					+ "  fact.TOTAL_SIN_IMPUESTO,fact.TOTAL_DESCUENTO,fact.TOTAL_CON_IMPUESTO,fact.TOTAL_IMPUESTO,"
					+ "  fact.FECHA_ENVIO_SRI,fact.ESTADO_DOCUMENTO,"
					+ "  fact.MOTIVO_RECHAZO,fact.CONTRIBUYENTE_ESPECIAL,fact.OBLIGADO_CONTABILIDAD,fact.MONEDA,fact.TELEFONO_CLIENTE,fact.CORREO_CLIENTE,"
					+ "  fact.CODIGO_IMPUESTO,fact.CODIGO_IMPUESTO_PORCENTAJE,fact.TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,fact.TARIFA,fact.PROPINA, "
					+"   nc.identificacion_empresa,nc.establecimiento,nc.punto_emision,nc.secuencial_factura,nc.secuencial_nc,"
				    + " nc.tipo_documento_nc,nc.fecha_emision,nc.motivo_emision,nc.clave_acceso_sri,nc.motivo_rechazo_sri "
					+ " FROM mtd.pos_cab_fact_electronica as fact"
					+ " INNER JOIN mtd.pos_nc_fact_electronica as nc"
					+ " on  fact.secuencial=nc.secuencial_factura "
					+ condicion 
			        + " AND fact.CORREO_CLIENTE!='NINGUNO' "  );
			
			String cadena=" SELECT fact.IDENTIFICACION_EMPRESA,fact.ESTABLECIMIENTO,fact.PUNTO_EMISION ,"
					+ " fact.SECUENCIAL,fact.CLAVE_ACCESO,fact.RAZON_SOCIAL_EMPRESA,fact.NOMBRE_COMERCIAL,"
					+ " fact.CODIGO_DOCUMENTO,fact.DIRECCION_EMPRESA,fact.FECHA_EMISION,fact.DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN fact.TIPO_ID_CLIENTE='C' AND fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ " WHEN fact.TIPO_ID_CLIENTE='P' THEN '06'     ELSE '00'     END AS TIPO_ID_CLIENTE,"
					+ "  fact.RAZON_SOCIAL_CLIENTE,fact.IDENTIFICACION_CLIENTE,fact.DIRECCION_CLIENTE,"
					+ "  fact.TOTAL_SIN_IMPUESTO,fact.TOTAL_DESCUENTO,fact.TOTAL_CON_IMPUESTO,fact.TOTAL_IMPUESTO,"
					+ "  fact.FECHA_ENVIO_SRI,fact.ESTADO_DOCUMENTO,"
					+ "  fact.MOTIVO_RECHAZO,fact.CONTRIBUYENTE_ESPECIAL,fact.OBLIGADO_CONTABILIDAD,fact.MONEDA,fact.TELEFONO_CLIENTE,fact.CORREO_CLIENTE,"
					+ "  fact.CODIGO_IMPUESTO,fact.CODIGO_IMPUESTO_PORCENTAJE,fact.TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,fact.TARIFA,fact.PROPINA, "
					+"   nc.identificacion_empresa,nc.establecimiento,nc.punto_emision,nc.secuencial_factura,nc.secuencial_nc,"
				    + " nc.tipo_documento_nc,nc.fecha_emision,nc.motivo_emision,nc.clave_acceso_sri,nc.motivo_rechazo_sri "
					+ " FROM mtd.pos_cab_fact_electronica as fact"
					+ " INNER JOIN mtd.pos_nc_fact_electronica as nc"
					+ " on  fact.secuencial=nc.secuencial_factura "
					+ condicion 
			        + " AND fact.CORREO_CLIENTE!='NINGUNO' " ;
			
			System.out.println(cadena);

			

			while (rsc.next()) {

				ExternalDataNc ep = new ExternalDataNc();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);
				
				ep.identificacion_empresa_nc= rsc.getString(33);
				ep.establecimiento_nc= rsc.getString(34);
				ep.punto_emision_nc= rsc.getString(35);
				ep.secuencial_factura_nc= rsc.getString(36);
				ep.secuencial_nc= rsc.getString(37);
				ep.tipo_documento_nc= rsc.getString(38);
				ep.fecha_emision_nc= rsc.getString(39);
				ep.motivo_emision_nc= rsc.getString(40);
				ep.clave_acceso_sri_nc= rsc.getString(41);
				ep.motivo_rechazo_sri_nc= rsc.getString(42);
				System.out.println("CLAVE ACCESO NC:"+ep.clave_acceso_sri_nc);

				liste.add(ep);
			}

		}
		return liste;

	}

	public List<ExternalData> QueryInfoFactCheckDinamico(Connection connection, String fecha, String estado)
			throws SQLException {

		String validacion = "NOT";
		System.out.println("QUERY INFORMACION VALIDAR");
		List<ExternalData> liste = new ArrayList<ExternalData>();
		String condicion = "";

		if (!validacion.equals(fecha) && !validacion.equalsIgnoreCase(estado)) {
			System.out.println("AQUI ESTOY");
			condicion = "   where FECHA_EMISION='" + fecha + "' and estado_documento  IN('" + estado + "')";
			System.out.println(condicion);
		} else if (fecha.equals("NOT") && !validacion.equals(estado)) {
			condicion = "   where estado_documento  IN('" + estado + "')";
			System.out.println(condicion);
		} else {
			condicion = "  ERROR";
			System.out.println(condicion);
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					// + " where FECHA_EMISION='" + fecha + "' AND
					// estado_documento in ('RECIBIDA','DEVUELTA') ");
					+ condicion);
			

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}
			// st.close();
			// connection.close();

		}
		return liste;

	}
	public List<ExternalDataNc> QueryInfoFactCheckDinamicoNc(Connection connection, String fecha, String estado)
			throws SQLException {

		String validacion = "NOT";
		System.out.println("QUERY INFORMACION VALIDAR");
		List<ExternalDataNc> liste = new ArrayList<ExternalDataNc>();
		String condicion = "";

		if (!validacion.equals(fecha) && !validacion.equalsIgnoreCase(estado)) {
			System.out.println("AQUI ESTOY");
			condicion = "   where nc.fecha_emision='" + fecha + "' and nc.estado_envio_sri  IN('" + estado + "')";
			System.out.println(condicion);
		} else if (fecha.equals("NOT") && !validacion.equals(estado)) {
			condicion = "   where  nc.estado_envio_sri  IN('" + estado + "')";
			System.out.println(condicion);
		} else {
			condicion = "  ERROR";
			System.out.println(condicion);
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery(
					 " SELECT fact.IDENTIFICACION_EMPRESA,fact.ESTABLECIMIENTO,fact.PUNTO_EMISION ,"
					+ " fact.SECUENCIAL,fact.CLAVE_ACCESO,fact.RAZON_SOCIAL_EMPRESA,fact.NOMBRE_COMERCIAL,"
					+ " fact.CODIGO_DOCUMENTO,fact.DIRECCION_EMPRESA,fact.FECHA_EMISION,fact.DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN fact.TIPO_ID_CLIENTE='C' AND fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(fact.IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ " WHEN fact.RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ " WHEN fact.TIPO_ID_CLIENTE='P' THEN '06'     ELSE '00'     END AS TIPO_ID_CLIENTE,"
					+ "  fact.RAZON_SOCIAL_CLIENTE,fact.IDENTIFICACION_CLIENTE,fact.DIRECCION_CLIENTE,"
					+ "  fact.TOTAL_SIN_IMPUESTO,fact.TOTAL_DESCUENTO,fact.TOTAL_CON_IMPUESTO,fact.TOTAL_IMPUESTO,"
					+ "  fact.FECHA_ENVIO_SRI,fact.ESTADO_DOCUMENTO,"
					+ "  fact.MOTIVO_RECHAZO,fact.CONTRIBUYENTE_ESPECIAL,fact.OBLIGADO_CONTABILIDAD,fact.MONEDA,fact.TELEFONO_CLIENTE,fact.CORREO_CLIENTE,"
					+ "  fact.CODIGO_IMPUESTO,fact.CODIGO_IMPUESTO_PORCENTAJE,fact.TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,fact.TARIFA,fact.PROPINA, "
					+"   nc.identificacion_empresa,nc.establecimiento,nc.punto_emision,nc.secuencial_factura,nc.secuencial_nc,"
				    + " nc.tipo_documento_nc,nc.fecha_emision,nc.motivo_emision,nc.clave_acceso_sri,nc.motivo_rechazo_sri "
					+ " FROM mtd.pos_cab_fact_electronica as fact"
					+ " INNER JOIN mtd.pos_nc_fact_electronica as nc"
					+ " on  fact.secuencial=nc.secuencial_factura "
					+ condicion );

			

			while (rsc.next()) {

				ExternalDataNc ep = new ExternalDataNc();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);
				
				ep.identificacion_empresa_nc= rsc.getString(33);
				ep.establecimiento_nc= rsc.getString(34);
				ep.punto_emision_nc= rsc.getString(35);
				ep.secuencial_factura_nc= rsc.getString(36);
				ep.secuencial_nc= rsc.getString(37);
				ep.tipo_documento_nc= rsc.getString(38);
				ep.fecha_emision_nc= rsc.getString(39);
				ep.motivo_emision_nc= rsc.getString(40);
				ep.clave_acceso_sri_nc= rsc.getString(41);
				ep.motivo_rechazo_sri_nc= rsc.getString(42);
				System.out.println("CLAVE ACCESO NC:"+ep.clave_acceso_sri_nc);

				liste.add(ep);
			}

		}
		return liste;

	}

	public List<ExternalData> QueryInfoFactCheck(Connection connection, String fecha) throws SQLException {

		System.out.println("QUERY INFORMACION VALIDAR");
		List<ExternalData> liste = new ArrayList<ExternalData>();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			ResultSet rsc = st.executeQuery("SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					+ "  where FECHA_EMISION='" + fecha + "' AND estado_documento in ('RECIBIDA','DEVUELTA')  "
					+ "UNION " + "SELECT IDENTIFICACION_EMPRESA," + "ESTABLECIMIENTO," + " PUNTO_EMISION ,"
					+ "SECUENCIAL," + "CLAVE_ACCESO," + " RAZON_SOCIAL_EMPRESA," + " NOMBRE_COMERCIAL,"
					+ " CODIGO_DOCUMENTO," + " DIRECCION_EMPRESA," + " FECHA_EMISION," + " DIRECCION_ESTABLECIMIENTO,"
					+ " CASE WHEN TIPO_ID_CLIENTE='C' AND RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=10 THEN '05' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE!='CONSUMIDOR FINAL' AND length(IDENTIFICACION_CLIENTE)=13 THEN '04' "
					+ "     WHEN RAZON_SOCIAL_CLIENTE='CONSUMIDOR FINAL' THEN '07' "
					+ "     WHEN TIPO_ID_CLIENTE='P' THEN '06' " + "     ELSE '00' " + "     END AS TIPO_ID_CLIENTE,"
					+ " RAZON_SOCIAL_CLIENTE," + " IDENTIFICACION_CLIENTE," + " DIRECCION_CLIENTE,"
					+ " TOTAL_SIN_IMPUESTO," + " TOTAL_DESCUENTO," + " TOTAL_CON_IMPUESTO," + " TOTAL_IMPUESTO,"
					+ " FECHA_ENVIO_SRI," + " ESTADO_DOCUMENTO,"
					+ " MOTIVO_RECHAZO,CONTRIBUYENTE_ESPECIAL,OBLIGADO_CONTABILIDAD,MONEDA,TELEFONO_CLIENTE,CORREO_CLIENTE,"
					+ " CODIGO_IMPUESTO,CODIGO_IMPUESTO_PORCENTAJE,TOTAL_SIN_IMPUESTO AS BASE_IMPONIBLE,TARIFA,PROPINA FROM mtd.POS_CAB_FACT_ELECTRONICA "
					+ "  where FECHA_EMISION='" + fecha + "' AND estado_documento is null   ");

			while (rsc.next()) {

				ExternalData ep = new ExternalData();

				System.out.println("SECUENCIAL:" + rsc.getString(4));
				System.out.println("CLAVE ACCESO:" + rsc.getString(5));
				// ep.
				ep.IDENTIFICACION_EMPRESA = rsc.getString(1);
				ep.ESTABLECIMIENTO = rsc.getString(2);
				ep.PUNTO_EMISION = rsc.getString(3);
				ep.SECUENCIAL = rsc.getString(4);
				ep.CLAVE_ACCESO = rsc.getString(5);
				ep.RAZON_SOCIAL_EMPRESA = rsc.getString(6);
				ep.NOMBRE_COMERCIAL = rsc.getString(7);
				ep.CODIGO_DOCUMENTO = rsc.getString(8);
				ep.DIRECCION_EMPRESA = rsc.getString(9);
				ep.FECHA_EMISION = rsc.getString(10);
				ep.DIRECCION_ESTABLECIMIENTO = rsc.getString(11);
				ep.TIPO_ID_CLIENTE = rsc.getString(12);
				ep.RAZON_SOCIAL_CLIENTE = rsc.getString(13);
				ep.IDENTIFICACION_CLIENTE = rsc.getString(14);
				ep.DIRECCION_CLIENTE = rsc.getString(15);
				ep.TOTAL_SIN_IMPUESTO = rsc.getString(16);
				ep.TOTAL_DESCUENTO = rsc.getString(17);
				ep.TOTAL_CON_IMPUESTO = rsc.getString(18);
				ep.TOTAL_IMPUESTO = rsc.getString(19);
				ep.FECHA_ENVIO_SRI = rsc.getString(20);
				ep.ESTADO_DOCUMENTO = rsc.getString(21);
				ep.MOTIVO_RECHAZO = rsc.getString(22);

				ep.CONTRIBUYENTE_ESPECIAL = rsc.getString(23);
				ep.OBLIGADO_CONTABILIDAD = rsc.getString(24);
				ep.MONEDA = rsc.getString(25);
				ep.TELEFONO_CLIENTE = rsc.getString(26);
				ep.CORREO_CLIENTE = rsc.getString(27);
				ep.CODIGO_IMPUESTO = rsc.getString(28);
				ep.CODIGO_IMPUESTO_PORCENTAJE = rsc.getString(29);
				ep.BASE_IMPONIBLE = rsc.getString(30);
				ep.TARIFA = rsc.getString(31);
				ep.PROPINA = rsc.getString(32);

				liste.add(ep);
			}
			// st.close();
			// connection.close();

		}
		return liste;

	}

	public List<ExternalDataDetalle> QueryInfoDetFact(Connection connection, List<ExternalData> edata)
			throws SQLException {

		List<ExternalDataDetalle> listd = new ArrayList<ExternalDataDetalle>();

		if (connection != null) {
			System.out.println("You made it, take control your database now!");

			Statement st;

			st = connection.createStatement();

			System.out.println("TAMAÑO:" + edata.size());

			for (int y = 0; y < edata.size(); y++) {

				System.out.println("VAR:" + edata.get(y).SECUENCIAL + "--" + edata.get(y).ESTABLECIMIENTO + "--"
						+ edata.get(y).IDENTIFICACION_EMPRESA + "--" + edata.get(y).PUNTO_EMISION);

				ResultSet rsc = st
						.executeQuery("SELECT  IDENTIFICACION_EMPRESA ," + "ESTABLECIMIENTO  ," + "PUNTO_EMISION  ,"
								+ "SECUENCIAL ," + "DESCRIPCION_PRODUCTO," + "CANTIDAD_PRODUCTO ," + "PRECIO_UNITARIO,"
								+ "VALOR_DESCUENTO  ," + "TOTAL_SIN_IMPUESTO,CODIGO_PRINCIPAL,CODIGO_AUXILIAR   "
								+ "FROM mtd.POS_DET_FACT_ELECTRONICA WHERE SECUENCIAL='" + edata.get(y).SECUENCIAL
								+ "' " + "AND ESTABLECIMIENTO='" + edata.get(y).ESTABLECIMIENTO + "' "
								+ "AND IDENTIFICACION_EMPRESA='" + edata.get(y).IDENTIFICACION_EMPRESA
								+ "' AND PUNTO_EMISION='" + edata.get(y).PUNTO_EMISION + "'");

				while (rsc.next()) {
					ExternalDataDetalle ed = new ExternalDataDetalle();
					System.out.println("DETALLE FACTURA:" + rsc.getString(1) + "    " + rsc.getString(7));

					ed.IDENTIFICACION_EMPRESA = rsc.getString(1);
					ed.ESTABLECIMIENTO = rsc.getString(2);
					ed.PUNTO_EMISION = rsc.getString(3);
					ed.SECUENCIAL = rsc.getString(4);
					ed.DESCRIPCION_PRODUCTO = rsc.getString(5);
					ed.CANTIDAD_PRODUCTO = rsc.getString(6);
					ed.PRECIO_UNITARIO = rsc.getString(7);
					ed.VALOR_DESCUENTO = rsc.getString(8);
					ed.TOTAL_SIN_IMPUESTO = rsc.getString(9);

					ed.CODIGO_PRINCIPAL = rsc.getString(10);
					ed.CODIGO_AUXILIAR = rsc.getString(11);

					listd.add(ed);
				}
			}

		}
		return listd;
	}

	public List<ExternalDataDetalle> QueryInfoDetFact(Connection connection, String identificacionEmpresa,
			String establecimiento, String puntoEmision, String secuencial) throws SQLException {

		List<ExternalDataDetalle> listd = new ArrayList<ExternalDataDetalle>();

		System.out.println(identificacionEmpresa + "***" + establecimiento + "***" + puntoEmision + "***" + secuencial);

		if (connection != null) {
			System.out.println("CONSULTANDO DETALLE!");

			Statement stD;

			stD = connection.createStatement();

			ResultSet rscD = stD.executeQuery("SELECT  IDENTIFICACION_EMPRESA ,ESTABLECIMIENTO  ,"
					+ "PUNTO_EMISION  ,SECUENCIAL ,DESCRIPCION_PRODUCTO,CANTIDAD_PRODUCTO ,"
					+ "PRECIO_UNITARIO,VALOR_DESCUENTO  ,TOTAL_SIN_IMPUESTO,CODIGO_PRINCIPAL,CODIGO_AUXILIAR "
					+ "FROM mtd.POS_DET_FACT_ELECTRONICA WHERE SECUENCIAL='" + secuencial + "' "
					+ "AND ESTABLECIMIENTO='" + establecimiento + "' " + "AND IDENTIFICACION_EMPRESA='"
					+ identificacionEmpresa + "' " + "AND PUNTO_EMISION='" + puntoEmision + "'");

			while (rscD.next()) {
				System.out.println("RECORRIENDO DETALLE");

				ExternalDataDetalle edD = new ExternalDataDetalle();

				edD.IDENTIFICACION_EMPRESA = rscD.getString(1);
				edD.ESTABLECIMIENTO = rscD.getString(2);
				edD.PUNTO_EMISION = rscD.getString(3);
				edD.SECUENCIAL = rscD.getString(4);
				edD.DESCRIPCION_PRODUCTO = rscD.getString(5);
				edD.CANTIDAD_PRODUCTO = rscD.getString(6);
				edD.PRECIO_UNITARIO = rscD.getString(7);
				edD.VALOR_DESCUENTO = rscD.getString(8);
				edD.TOTAL_SIN_IMPUESTO = rscD.getString(9);

				edD.CODIGO_PRINCIPAL = rscD.getString(10);
				edD.CODIGO_AUXILIAR = rscD.getString(11);

				listd.add(edD);
			}
			// stD.close();
			// connection.close();

		}
		System.out.println("DETALLE TAMAÑO:" + listd.size());
		return listd;

	}
	public List<ExternalDataDetalle> QueryInfoDetFactNc(Connection connection, String identificacionEmpresa,
			String establecimiento, String puntoEmision, String secuencial) throws SQLException {

		List<ExternalDataDetalle> listd = new ArrayList<ExternalDataDetalle>();

		System.out.println(identificacionEmpresa + "***" + establecimiento + "***" + puntoEmision + "***" + secuencial);

		if (connection != null) {
			System.out.println("CONSULTANDO DETALLE!");

			Statement stD;

			stD = connection.createStatement();

			ResultSet rscD = stD.executeQuery("SELECT  IDENTIFICACION_EMPRESA ,ESTABLECIMIENTO  ,"
					+ "PUNTO_EMISION  ,SECUENCIAL ,DESCRIPCION_PRODUCTO,CANTIDAD_PRODUCTO ,"
					+ "PRECIO_UNITARIO,VALOR_DESCUENTO  ,TOTAL_SIN_IMPUESTO,CODIGO_PRINCIPAL,CODIGO_AUXILIAR "
					+ "FROM mtd.POS_DET_FACT_ELECTRONICA WHERE SECUENCIAL='" + secuencial + "' "
					+ "AND ESTABLECIMIENTO='" + establecimiento + "' " + "AND IDENTIFICACION_EMPRESA='"
					+ identificacionEmpresa + "' " + "AND PUNTO_EMISION='" + puntoEmision + "'");

			while (rscD.next()) {
				System.out.println("RECORRIENDO DETALLE");

				ExternalDataDetalle edD = new ExternalDataDetalle();

				edD.IDENTIFICACION_EMPRESA = rscD.getString(1);
				edD.ESTABLECIMIENTO = rscD.getString(2);
				edD.PUNTO_EMISION = rscD.getString(3);
				edD.SECUENCIAL = rscD.getString(4);
				edD.DESCRIPCION_PRODUCTO = rscD.getString(5);
				edD.CANTIDAD_PRODUCTO = rscD.getString(6);
				edD.PRECIO_UNITARIO = rscD.getString(7);
				edD.VALOR_DESCUENTO = rscD.getString(8);
				edD.TOTAL_SIN_IMPUESTO = rscD.getString(9);

				edD.CODIGO_PRINCIPAL = rscD.getString(10);
				edD.CODIGO_AUXILIAR = rscD.getString(11);

				listd.add(edD);
			}
			// stD.close();
			// connection.close();

		}
		System.out.println("DETALLE TAMAÑO:" + listd.size());
		return listd;

	}

	/*
	 * public List<ExternalDataDetalle> QueryInfoDetFact(Connection connection,
	 * String fecha) {
	 * 
	 * List<ExternalDataDetalle> listd = new ArrayList<ExternalDataDetalle>();
	 * 
	 * 
	 * if (connection != null) {
	 * System.out.println("You made it, take control your database now!");
	 * 
	 * Statement st;
	 * 
	 * try { st = connection.createStatement();
	 * 
	 * ResultSet rsc = st .executeQuery("SELECT  IDENTIFICACION_EMPRESA ," +
	 * "ESTABLECIMIENTO  ," + "PUNTO_EMISION  ," + "SECUENCIAL ," +
	 * "DESCRIPCION_PRODUCTO," + "CANTIDAD_PRODUCTO ," + "PRECIO_UNITARIO," +
	 * "VALOR_DESCUENTO  ," +
	 * "TOTAL_SIN_IMPUESTO  FROM POS_DET_FACT_ELECTRONICA ");//
	 * 
	 * while (rsc.next()) {
	 * 
	 * ExternalDataDetalle ed = new ExternalDataDetalle();
	 * 
	 * ed.IDENTIFICACION_EMPRESA = rsc.getString(1); ed.ESTABLECIMIENTO =
	 * rsc.getString(2); ed.PUNTO_EMISION = rsc.getString(3); ed.SECUENCIAL =
	 * rsc.getString(4); ed.DESCRIPCION_PRODUCTO = rsc.getString(5);
	 * ed.CANTIDAD_PRODUCTO = rsc.getString(6); ed.PRECIO_UNITARIO =
	 * rsc.getString(7); ed.VALOR_DESCUENTO = rsc.getString(8);
	 * ed.TOTAL_SIN_IMPUESTO = rsc.getString(9);
	 * 
	 * listd.add(ed); }
	 * 
	 * } catch (Exception ex) {
	 * 
	 * }
	 * 
	 * } return listd;
	 * 
	 * }
	 */

}

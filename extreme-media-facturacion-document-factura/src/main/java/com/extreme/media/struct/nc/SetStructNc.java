package com.extreme.media.struct.nc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.commons.Util;
import com.extreme.media.entity.NotaCreditoDinamico;
import com.extreme.media.entity.NotaCreditoFijo;
import com.extreme.media.entity.StructEntityNc;
import com.extreme.media.entityes.ExternalDataNc;
import com.extreme.media.entityes.ExternalDataDetalleNc;

public class SetStructNc {
Util u = new Util();
	
	
	Constants constants = new Constants();
	ReadXml rx = new ReadXml();
	
	public StructEntityNc SetConstantsNc(ExternalDataNc ec, List<ExternalDataDetalleNc> ed) {
		
		Map<String, String> pathConfig = new HashMap<String, String>();
		
		Document e;
		e = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
		pathConfig=rx.readXmlMapAppConfig(e);
		
		String ambiente=pathConfig.get("ambiente");
		String tipoEmision=pathConfig.get("tipoEmision");
		
		
		NotaCreditoFijo ff = new NotaCreditoFijo();
		
		StructEntityNc se = new StructEntityNc();
		List<NotaCreditoDinamico> dd = new ArrayList<NotaCreditoDinamico>();

		ff.setAmbiente(ambiente);// viene de configuraciones
		ff.setTipoEmision(tipoEmision);//viene de configuraciones emision normal
		ff.setRazonSocial(ec.RAZON_SOCIAL_EMPRESA);
		ff.setNombreComercial(ec.NOMBRE_COMERCIAL);
		ff.setRuc(ec.identificacion_empresa_nc);// EXTRAER DE ARCHIVOS
												// CONFIGURACION
		// ff.setClaveAcceso(ep.fctr_claveaccesofac);
		ff.setClave_acceso_sri_nc(ec.clave_acceso_sri_nc);
		ff.setCodDoc("04");//quemaDO 04 PORQUE ES SOLO PARA NOTA DE CREDITO
		ff.setEstab(ec.establecimiento_nc);
		ff.setPtoEmi(ec.punto_emision_nc);
		// ff.setSecuencial(String.format("%09d", ep.autorizacion));//SE
		// UTILIZARA MOMENTANEAMENTE HASTA QUE PROVEAN LA INFORMACION
		ff.setSecuencial(String.format("%09d", Integer.parseInt(ec.secuencial_nc)));// SE
																					// UTILIZARA
																					// MOMENTANEAMENTE
																					// HASTA
																					// QUE
																					// PROVEAN
																					// LA
																					// INFORMACION
		ff.setDirMatriz(ec.DIRECCION_EMPRESA);
		ff.setFechaEmision(u.ConvertFechaEmision(ec.FECHA_EMISION));// TAMBIEN VIENE DE
															// BASE DE DATOS
															// MOMENTANEAMENTE
															// TOMADA DE CLASE
															// GENERADA
		ff.setDirEstablecimiento(ec.DIRECCION_ESTABLECIMIENTO);
		ff.setContribuyenteEspecial(ec.CONTRIBUYENTE_ESPECIAL);//solo cuando corresponda en este caso viene en blanco
		ff.setObligadoContabilidad(ec.OBLIGADO_CONTABILIDAD);
		ff.setTipoIdentificacionComprador(ec.TIPO_ID_CLIENTE);// estan enviando C toca setear
												// a 04 cedula
		ff.setRazonSocialComprador(ec.RAZON_SOCIAL_CLIENTE);
		ff.setIdentificacionComprador(ec.IDENTIFICACION_CLIENTE);
		
		ff.setTotalSinImpuestos(ec.TOTAL_SIN_IMPUESTO);
		//ff.setTotalDescuento(ec.TOTAL_DESCUENTO);
		
		ff.setTotalImpuestoCodigo(ec.CODIGO_IMPUESTO);
		ff.setTotalImpuestoCodigoPorcentaje(ec.CODIGO_IMPUESTO_PORCENTAJE);
		
		ff.setTotalImpuestoBaseImponible(ec.TOTAL_SIN_IMPUESTO);// SOLICITAR QUE ENVIEN
		ff.setTotalImpuestoValor(ec.TOTAL_IMPUESTO);// PEDIR QUE ENVIEN
		//ff.setPropina(ec.PROPINA);// PEDIR QUE ENVIEN EN CASO QUE SE DEN CASOS*/
		ff.setImporteTotal(ec.TOTAL_CON_IMPUESTO);// PEDIR QUE VALIDEN QUE SEA
													// EL MISMO
		ff.setMoneda(ec.MONEDA);// SIEMPRE DOLAR EN TODO CASO TRAER DE
								// CONFIGURACION
		ff.setImpuestoCodigo(ec.CODIGO_IMPUESTO);
		ff.setImpuestoCodigoPorcentaje(ec.CODIGO_IMPUESTO_PORCENTAJE);
		ff.setImpuestoTarifa(ec.TARIFA);//siempre va 12.00 preferible que venga de 
		ff.setImpuestoBaseImponible(ec.TOTAL_SIN_IMPUESTO);
		ff.setImpuestoValor(ec.TOTAL_IMPUESTO);
		
		ff.setCampoAdicionalDireccion(ec.DIRECCION_CLIENTE);
		ff.setCampoAdicionalTelefono(ec.TELEFONO_CLIENTE);// solicitar que envien el		
		ff.setCampoAdicionalEmail(ec.CORREO_CLIENTE);// solicitar que envien correo*/
		ff.setMotivo(ec.motivo_emision_nc);
		
		ff.setCodDocModificado(ec.tipo_documento_nc);
		ff.setNumDocModificado(ec.ESTABLECIMIENTO+"-"+ec.PUNTO_EMISION+"-"+String.format("%09d", Integer.parseInt(ec.secuencial_factura_nc)));
		
		

		// DATOS DINAMICOS
		
		System.out.println("--INICIANDO DATOS DINAMICOS--");

		for (int y = 0; y < ed.size(); y++) {
			
			NotaCreditoDinamico dc = new NotaCreditoDinamico();
			System.out.println("--DENTRO DE  DATOS DINAMICOS--");
			
			System.out.println(ed.get(y).DESCRIPCION_PRODUCTO);
			
			dc.setCodigoInterno(ed.get(y).CODIGO_PRINCIPAL);//SOLICITAR CODIGO DE PRODUCTO
			/*dc.setCodigoAuxiliar(ed.get(y).CODIGO_AUXILIAR);//SOLICITAR CODIGO AUXILIAR EN CASO QUE LO TENGA*/
			dc.setDescripcion(ed.get(y).DESCRIPCION_PRODUCTO);
			dc.setCantidad(ed.get(y).CANTIDAD_PRODUCTO);
			dc.setPrecioUnitario(ed.get(y).PRECIO_UNITARIO);
			dc.setDescuento(ed.get(y).VALOR_DESCUENTO);
			dc.setPrecioTotalSinImpuesto(ed.get(y).TOTAL_SIN_IMPUESTO);

			dd.add(dc);
		}

		// seteo de variables
		se.setFf(ff);
		//se.setFd(dc);
		se.setList(dd);

		return se;
	}

	/*public StructEntity SetConstants(ExternalPostgres ep, String claveacceso) {
		FacturaFijo ff = new FacturaFijo();
		FacturaDinamico dc = new FacturaDinamico();
		StructEntity se = new StructEntity();
		List<FacturaDinamico> dd = new ArrayList<FacturaDinamico>();

		ff.setAmbiente("1");
		ff.setTipoEmision("1");
		ff.setRazonSocial("EDISON XAVIER MORETA ESCOBAR");
		ff.setNombreComercial("EXTREME M");
		ff.setRuc("1718921362001");// EXTRAER DE ARCHIVOS CONFIGURACION
		// ff.setClaveAcceso(ep.fctr_claveaccesofac);
		ff.setClaveAcceso(claveacceso);
		ff.setCodDoc("01");
		ff.setEstab("001");
		ff.setPtoEmi("001");
		// ff.setSecuencial(String.format("%09d", ep.autorizacion));//SE
		// UTILIZARA MOMENTANEAMENTE HASTA QUE PROVEAN LA INFORMACION
		ff.setSecuencial(String.format("%09d", Integer.parseInt(ep.cajacdgo)));// SE
																				// UTILIZARA
																				// MOMENTANEAMENTE
																				// HASTA
																				// QUE
																				// PROVEAN
																				// LA
																				// INFORMACION
		ff.setDirMatriz("C/ Azuay - Interseccion Av de la Republica. E2-192");
		ff.setFechaEmision(u.getFechaActualFormatoUno());// TAMBIEN VIENE DE
															// BASE DE DATOS
															// MOMENTANEAMENTE
															// TOMADA DE CLASE
															// GENERADA
		ff.setDirEstablecimiento("C/ Azuay - Interseccion Av de la Republica. E2-192");
		ff.setContribuyenteEspecial("");
		ff.setObligadoContabilidad("NO");
		ff.setTipoIdentificacionComprador("04");
		ff.setRazonSocialComprador(ep.fctrprsn);
		ff.setIdentificacionComprador(ep.clntcdgo);
		ff.setTotalSinImpuestos("100.00");
		ff.setTotalDescuento("0.00");
		ff.setTotalImpuestoCodigo("2");
		ff.setTotalImpuestoCodigoPorcentaje("2");
		ff.setTotalImpuestoBaseImponible("100.00");
		ff.setTotalImpuestoValor("12.00");
		ff.setPropina("0.00");
		ff.setImporteTotal("112.00");
		ff.setMoneda("DOLAR");
		ff.setImpuestoCodigo("2");
		ff.setImpuestoCodigoPorcentaje("2");
		ff.setImpuestoTarifa("12.00");
		ff.setImpuestoBaseImponible("100.00");
		ff.setImpuestoValor("12.00");
		ff.setCampoAdicionalDireccion("TEST DIRECCION");
		ff.setCampoAdicionalTelefono("022890011");
		ff.setCampoAdicionalEmail(ep.fctrpasaportecorreo);

		dc.setCodigoPrincipal("001");
		dc.setCodigoAuxiliar("001");
		dc.setDescripcion("SILLA DE MADERA");
		dc.setCantidad("1.00");
		dc.setPrecioUnitario("100.00");
		dc.setDescuento("0.00");
		dc.setPrecioTotalSinImpuesto("100.00");

		dd.add(dc);

		// seteo de variables
		se.setFf(ff);
		se.setFd(dc);
		se.setList(dd);

		return se;
	}*/


}

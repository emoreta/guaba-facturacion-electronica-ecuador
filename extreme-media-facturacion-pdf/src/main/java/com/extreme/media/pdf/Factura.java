package com.extreme.media.pdf;

import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.extreme.media.entityes.ExternalDataDetalleNc;
import com.extreme.media.entityes.ExternalDataNc;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Factura {
	// private String pathLogo="src/imagenes/disashop.png";
	private String pathPdf = "C:\\XMLPRUEBA\\";
	// private String nombrePdf="facturaPdf.pdf";
	private String DescripcionSql = "por definir";
	private String pathLogo = "C:\\firmasEnviar\\LOGO\\GuabaEstudioTranparente.png";

	public void GenerarPdfStandartNc(ExternalDataNc listCabecera, List<ExternalDataDetalleNc> result, String pathLogo,
			String ambiente, String tipoEmision, String pathPdf)
			throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		String ambienteTexto = "";
		String tipoEmisionTexto = "";
		if (ambiente.equals("1")) {
			ambienteTexto = "PRUEBAS";

		} else if (ambiente.equals("2")) {
			ambienteTexto = "PRODUCCION";
		} else {
			ambienteTexto = "ERROR";
		}

		if (tipoEmision.equals("1")) {
			tipoEmisionTexto = "EMISION NORMAL";

		} else {
			tipoEmisionTexto = "ERROR";
		}

		FileOutputStream archivo = new FileOutputStream(pathPdf + listCabecera.clave_acceso_sri_nc + ".pdf");
		Document documento = new Document();
		PdfWriter writer = PdfWriter.getInstance(documento, archivo);
		documento.open();

		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
		Chunk c = new Chunk("ESPACIO EN BLANCO", f);
		Paragraph p = new Paragraph(c);

		// CONFIGURACION FUENTE 1
		Font fuente = new Font();
		fuente.setColor(BaseColor.BLACK);
		fuente.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 2
		Font fuente1 = new Font();
		fuente1.setColor(BaseColor.BLACK);
		fuente1.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 3
		Font fuente2 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente2.setColor(BaseColor.BLACK);
		fuente2.setSize(12);
		// -------------

		// CONFIGURACION FUENTE 3
		Font fuente3 = new Font();
		fuente3.setColor(BaseColor.BLACK);
		fuente3.setSize(8);
		// -------------
		// CONFIGURACION FUENTE 4
		Font fuente4 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente4.setColor(BaseColor.BLACK);
		fuente4.setSize(12);

		Barcode128 codeEAN = new Barcode128();

		codeEAN.setCode(listCabecera.CLAVE_ACCESO);
		Image img = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
		img.scaleAbsolute(250f, 35f);

		PdfPTable tableInformacionDerecha = new PdfPTable(1);
		tableInformacionDerecha.getDefaultCell().setBorder(0);
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   R.U.C .:" + listCabecera.IDENTIFICACION_EMPRESA, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph(" NOTA DE CREDITO", fuente4));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		tableInformacionDerecha.addCell(new Paragraph("   N°:" + listCabecera.ESTABLECIMIENTO + "-"
				+ listCabecera.PUNTO_EMISION + "-" + listCabecera.SECUENCIAL, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n", fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.clave_acceso_sri_nc, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha
				.addCell(new Paragraph("   FECHA Y HORA DE\n   AUTORIZACION:" + listCabecera.FECHA_ENVIO_SRI, fuente1));// viene
																														// del
																														// resultado
																														// del
																														// sri
																														// parametrizar
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   AMBIENTE:" + ambienteTexto, fuente1));// viene
																								// de
																								// configuraciones
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   EMISION: " + tipoEmisionTexto, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   CLAVE DE ACCESO", fuente2));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new PdfPCell(img));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		// -----------------

		// ----------------
		PdfPTable tableDivisionInicio = new PdfPTable(2);
		tableDivisionInicio.setWidthPercentage(100);
		Image i = Image.getInstance(pathLogo);
		i.scalePercent(10);
		PdfPCell cell;
		PdfPCell cellDerecha;
		cellDerecha = new PdfPCell(tableInformacionDerecha);
		cellDerecha.setRowspan(2);

		tableDivisionInicio.addCell(i);
		tableDivisionInicio.addCell(cellDerecha);
		tableDivisionInicio.addCell(new Paragraph("\n\n\n\n   " + listCabecera.RAZON_SOCIAL_EMPRESA
				+ "\n\n\n\n   Dir Matriz:" + listCabecera.DIRECCION_EMPRESA + "  \n\n\n\n   Dir Sucursal:"
				+ listCabecera.DIRECCION_ESTABLECIMIENTO
				+ "  \n\n\n\n   Contribuyente Especial N  \n\n\n\n   OBLIGADO A LLEVAR CONTABILIDAD "
				+ listCabecera.OBLIGADO_CONTABILIDAD, fuente3));
		documento.add(tableDivisionInicio);

		// ---------------------

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableEncabezado = new PdfPTable(1);
		tableEncabezado.setWidthPercentage(100);
		tableEncabezado.addCell(new Paragraph("Razón Social / Nombres y Apellidos:" + listCabecera.RAZON_SOCIAL_CLIENTE
				+ "      Identificacion:" + listCabecera.IDENTIFICACION_CLIENTE + " \n\n Fecha Emision:"
				+ listCabecera.FECHA_EMISION + "       Guia Remision:", fuente));
		// documento.add( Chunk.NEWLINE );
		documento.add(tableEncabezado);

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableDetalle = new PdfPTable(10);

		tableDetalle.setWidthPercentage(100);

		tableDetalle.addCell(new Paragraph("Cod.\nPrincipal", fuente));
		tableDetalle.addCell(new Paragraph("Cod.\nAuxiliar", fuente));
		tableDetalle.addCell(new Paragraph("Cant", fuente));
		tableDetalle.addCell(new Paragraph("Descripcion", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Precio\nUnitario", fuente));
		tableDetalle.addCell(new Paragraph("Descuento", fuente));
		tableDetalle.addCell(new Paragraph("Precio Total", fuente));

		float[] columnWidths = new float[] { 12f, 15f, 15f, 25f, 25f, 25f, 25f, 12f, 12f, 12f };
		tableDetalle.setWidths(columnWidths);
		System.out.println("TAMAÑO RECORRIDO PDF:" + result.size());
		for (int x = 0; x < result.size(); x++) {
			
			double valorTotalUni=0.00; 
			double finalValorTotalUni=0.00; 
			valorTotalUni=Double.parseDouble(result.get(x).PRECIO_UNITARIO)-Double.parseDouble(result.get(x).VALOR_DESCUENTO);
			
			finalValorTotalUni=Math.round( valorTotalUni * 100.00 ) / 100.00;
			
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_PRINCIPAL, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_AUXILIAR, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CANTIDAD_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).DESCRIPCION_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).VALOR_DESCUENTO, fuente));
			//tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(String.valueOf(finalValorTotalUni), fuente));

		}
		documento.add(tableDetalle);

		PdfPCell c2;
		PdfPCell c3;

		PdfPTable tableInfoAdicional = new PdfPTable(1);
		tableInfoAdicional.setWidthPercentage(100);

		tableInfoAdicional
				.addCell(new Paragraph(
						"Información Adicional \n\nDirección: " + listCabecera.DIRECCION_CLIENTE + "\n\nTeléfono:"
								+ listCabecera.TELEFONO_CLIENTE + "\n\nEmail:" + listCabecera.CORREO_CLIENTE + "\n\n",
						fuente));
		float[] columnWidthsInfoA = new float[] { 50f };
		tableInfoAdicional.setWidths(columnWidthsInfoA);

		c2 = new PdfPCell(tableInfoAdicional);
		double valorTotal=0.00; 
		double finalValorTotal=0.00; 
		valorTotal=Double.sum(Double.parseDouble(listCabecera.TOTAL_CON_IMPUESTO),Double.parseDouble(listCabecera.PROPINA));
		finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
		

		PdfPTable tableInfoAdicionalTotal = new PdfPTable(2);
		// tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL
		// "+DescripcionSql,fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL ", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL 0%", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL No objeto de IVA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL SIN IMPUESTOS", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL Exento de IVA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("DESCUENTO", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_DESCUENTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("ICE", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("IVA ", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("IRBPNR", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("PROPINA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.PROPINA, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL", fuente));
		//tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_CON_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(String.valueOf(finalValorTotal), fuente));
		c3 = new PdfPCell(tableInfoAdicionalTotal);

		PdfPTable TableDivision = new PdfPTable(2);
		TableDivision.setWidthPercentage(100);
		TableDivision.addCell(c2);
		TableDivision.addCell(c3);
		documento.add(TableDivision);
		documento.close();
	}
	
	public void GenerarPdfStandart(ExternalData listCabecera, List<ExternalDataDetalle> result, String pathLogo,
			String ambiente, String tipoEmision, String pathPdf)
			throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		String ambienteTexto = "";
		String tipoEmisionTexto = "";
		if (ambiente.equals("1")) {
			ambienteTexto = "PRUEBAS";

		} else if (ambiente.equals("2")) {
			ambienteTexto = "PRODUCCION";
		} else {
			ambienteTexto = "ERROR";
		}

		if (tipoEmision.equals("1")) {
			tipoEmisionTexto = "EMISION NORMAL";

		} else {
			tipoEmisionTexto = "ERROR";
		}

		FileOutputStream archivo = new FileOutputStream(pathPdf + listCabecera.CLAVE_ACCESO + ".pdf");
		Document documento = new Document();
		PdfWriter writer = PdfWriter.getInstance(documento, archivo);
		documento.open();

		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
		Chunk c = new Chunk("ESPACIO EN BLANCO", f);
		Paragraph p = new Paragraph(c);

		// CONFIGURACION FUENTE 1
		Font fuente = new Font();
		fuente.setColor(BaseColor.BLACK);
		fuente.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 2
		Font fuente1 = new Font();
		fuente1.setColor(BaseColor.BLACK);
		fuente1.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 3
		Font fuente2 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente2.setColor(BaseColor.BLACK);
		fuente2.setSize(12);
		// -------------

		// CONFIGURACION FUENTE 3
		Font fuente3 = new Font();
		fuente3.setColor(BaseColor.BLACK);
		fuente3.setSize(8);
		// -------------
		// CONFIGURACION FUENTE 4
		Font fuente4 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente4.setColor(BaseColor.BLACK);
		fuente4.setSize(12);

		Barcode128 codeEAN = new Barcode128();

		codeEAN.setCode(listCabecera.CLAVE_ACCESO);
		Image img = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
		img.scaleAbsolute(250f, 35f);

		PdfPTable tableInformacionDerecha = new PdfPTable(1);
		tableInformacionDerecha.getDefaultCell().setBorder(0);
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   R.U.C .:" + listCabecera.IDENTIFICACION_EMPRESA, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   FACTURA", fuente4));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		tableInformacionDerecha.addCell(new Paragraph("   N°:" + listCabecera.ESTABLECIMIENTO + "-"
				+ listCabecera.PUNTO_EMISION + "-" + listCabecera.SECUENCIAL, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n", fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.CLAVE_ACCESO, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha
				.addCell(new Paragraph("   FECHA Y HORA DE\n   AUTORIZACION:" + listCabecera.FECHA_ENVIO_SRI, fuente1));// viene
																														// del
																														// resultado
																														// del
																														// sri
																														// parametrizar
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   AMBIENTE:" + ambienteTexto, fuente1));// viene
																								// de
																								// configuraciones
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   EMISION: " + tipoEmisionTexto, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new Paragraph("   CLAVE DE ACCESO", fuente2));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));
		tableInformacionDerecha.addCell(new PdfPCell(img));
		tableInformacionDerecha.addCell(new Paragraph("BLANCO", fcell));

		// -----------------

		// ----------------
		PdfPTable tableDivisionInicio = new PdfPTable(2);
		tableDivisionInicio.setWidthPercentage(100);
		Image i = Image.getInstance(pathLogo);
		i.scalePercent(10);
		PdfPCell cell;
		PdfPCell cellDerecha;
		cellDerecha = new PdfPCell(tableInformacionDerecha);
		cellDerecha.setRowspan(2);

		tableDivisionInicio.addCell(i);
		tableDivisionInicio.addCell(cellDerecha);
		tableDivisionInicio.addCell(new Paragraph("\n\n\n\n   " + listCabecera.RAZON_SOCIAL_EMPRESA
				+ "\n\n\n\n   Dir Matriz:" + listCabecera.DIRECCION_EMPRESA + "  \n\n\n\n   Dir Sucursal:"
				+ listCabecera.DIRECCION_ESTABLECIMIENTO
				+ "  \n\n\n\n   Contribuyente Especial N  \n\n\n\n   OBLIGADO A LLEVAR CONTABILIDAD "
				+ listCabecera.OBLIGADO_CONTABILIDAD, fuente3));
		documento.add(tableDivisionInicio);

		// ---------------------

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableEncabezado = new PdfPTable(1);
		tableEncabezado.setWidthPercentage(100);
		tableEncabezado.addCell(new Paragraph("Razón Social / Nombres y Apellidos:" + listCabecera.RAZON_SOCIAL_CLIENTE
				+ "      Identificacion:" + listCabecera.IDENTIFICACION_CLIENTE + " \n\n Fecha Emision:"
				+ listCabecera.FECHA_EMISION + "       Guia Remision:", fuente));
		// documento.add( Chunk.NEWLINE );
		documento.add(tableEncabezado);

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableDetalle = new PdfPTable(10);

		tableDetalle.setWidthPercentage(100);

		tableDetalle.addCell(new Paragraph("Cod.\nPrincipal", fuente));
		tableDetalle.addCell(new Paragraph("Cod.\nAuxiliar", fuente));
		tableDetalle.addCell(new Paragraph("Cant", fuente));
		tableDetalle.addCell(new Paragraph("Descripcion", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Detalle\nAdicional", fuente));
		tableDetalle.addCell(new Paragraph("Precio\nUnitario", fuente));
		tableDetalle.addCell(new Paragraph("Descuento", fuente));
		tableDetalle.addCell(new Paragraph("Precio Total", fuente));

		float[] columnWidths = new float[] { 12f, 15f, 15f, 25f, 25f, 25f, 25f, 12f, 12f, 12f };
		tableDetalle.setWidths(columnWidths);
		System.out.println("TAMAÑO RECORRIDO PDF:" + result.size());
		for (int x = 0; x < result.size(); x++) {
			
			double valorTotalUni=0.00; 
			double finalValorTotalUni=0.00; 
			valorTotalUni=Double.parseDouble(result.get(x).PRECIO_UNITARIO)-Double.parseDouble(result.get(x).VALOR_DESCUENTO);
			
			finalValorTotalUni=Math.round( valorTotalUni * 100.00 ) / 100.00;
			
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_PRINCIPAL, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_AUXILIAR, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CANTIDAD_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).DESCRIPCION_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph("", fuente));// en blanco no
															// tienen detalle
															// adicional
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).VALOR_DESCUENTO, fuente));
			//tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(String.valueOf(finalValorTotalUni), fuente));

		}
		documento.add(tableDetalle);

		PdfPCell c2;
		PdfPCell c3;

		PdfPTable tableInfoAdicional = new PdfPTable(1);
		tableInfoAdicional.setWidthPercentage(100);

		tableInfoAdicional
				.addCell(new Paragraph(
						"Información Adicional \n\nDirección: " + listCabecera.DIRECCION_CLIENTE + "\n\nTeléfono:"
								+ listCabecera.TELEFONO_CLIENTE + "\n\nEmail:" + listCabecera.CORREO_CLIENTE + "\n\n",
						fuente));
		float[] columnWidthsInfoA = new float[] { 50f };
		tableInfoAdicional.setWidths(columnWidthsInfoA);

		c2 = new PdfPCell(tableInfoAdicional);
		double valorTotal=0.00; 
		double finalValorTotal=0.00; 
		valorTotal=Double.sum(Double.parseDouble(listCabecera.TOTAL_CON_IMPUESTO),Double.parseDouble(listCabecera.PROPINA));
		finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
		

		PdfPTable tableInfoAdicionalTotal = new PdfPTable(2);
		// tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL
		// "+DescripcionSql,fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL ", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL 0%", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL No objeto de IVA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL SIN IMPUESTOS", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL Exento de IVA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("DESCUENTO", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_DESCUENTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("ICE", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("IVA ", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("IRBPNR", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("0.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("PROPINA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.PROPINA, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL", fuente));
		//tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_CON_IMPUESTO, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(String.valueOf(finalValorTotal), fuente));
		c3 = new PdfPCell(tableInfoAdicionalTotal);

		PdfPTable TableDivision = new PdfPTable(2);
		TableDivision.setWidthPercentage(100);
		TableDivision.addCell(c2);
		TableDivision.addCell(c3);
		documento.add(TableDivision);
		documento.close();
	}

	public void GenerarPdf(ExternalData listCabecera, List<ExternalDataDetalle> result, String pathLogo,
			String ambiente, String tipoEmision, String pathPdf)
			throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		String ambienteTexto = "";
		String tipoEmisionTexto = "";
		if(ambiente=="1")
        {
        	ambienteTexto="PRUEBAS";
        	
        }
        else if(ambiente=="2")
        {
        	ambienteTexto="PRODUCCION";
        }
        else
        {
        	ambienteTexto="ERROR";
        }
        	
        
        if(tipoEmision=="1")
        {
        	tipoEmisionTexto="EMISION NORMAL";
        	
        }
        else 
        {
        	tipoEmisionTexto="ERROR";
        }

		FileOutputStream archivo = new FileOutputStream(pathPdf + listCabecera.CLAVE_ACCESO + ".pdf");
		Document documento = new Document();
		PdfWriter writer = PdfWriter.getInstance(documento, archivo);
		documento.open();

		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
		Chunk c = new Chunk("ESPACIO EN BLANCO", f);
		Paragraph p = new Paragraph(c);

		Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
		Font font = new Font();
		Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);

		// CONFIGURACION FUENTE 1
		Font fuente = new Font();
		fuente.setColor(BaseColor.BLACK);
		fuente.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 2
		Font fuente1 = new Font();
		fuente1.setColor(BaseColor.BLACK);
		fuente1.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 3
		Font fuente2 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente2.setColor(BaseColor.BLACK);
		fuente2.setSize(12);
		// -------------

		// CONFIGURACION FUENTE 3
		Font fuente3 = new Font();
		fuente3.setColor(BaseColor.BLACK);
		fuente3.setSize(8);
		// -------------
		// CONFIGURACION FUENTE 4
		Font fuente4 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente4.setColor(BaseColor.BLACK);
		fuente4.setSize(12);

		// CONFIGURACION FUENTE 4
		Font fuente5 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.BLACK);
		fuente5.setColor(BaseColor.BLACK);
		fuente5.setSize(8);

		// CONFIGURACION FUENTE 4
		Font fuente6 = new Font(FontFamily.HELVETICA, 16.0f, Font.BOLD, BaseColor.WHITE);

		// CONFIGURACION FUENTE 4
		Font fuenteNew = new Font(FontFamily.HELVETICA, 8.0f, Font.BOLD, BaseColor.WHITE);

		BaseFont bf_russian = BaseFont.createFont("C:\\FAC\\assets\\calibri.ttf", "CP1251", BaseFont.EMBEDDED);
		Font russian = new Font(bf_russian, 12.0f, Font.BOLD, BaseColor.BLACK);
		Font russianP = new Font(bf_russian, 10.0f, Font.BOLD, BaseColor.BLACK);
		Font russian16 = new Font(bf_russian, 16.0f, Font.BOLD, BaseColor.WHITE);

		Barcode128 codeEAN = new Barcode128();

		codeEAN.setCode(listCabecera.CLAVE_ACCESO);
		Image img = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
		img.scaleAbsolute(250f, 35f);

		PdfPCell cellp;
		cellp = new PdfPCell(new Phrase("FACTURA", fuente6));
		cellp.setBackgroundColor(BaseColor.RED);
		cellp.setBorder(Rectangle.NO_BORDER);
		cellp.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellp.setColspan(2);

		PdfPCell cellpC;
		cellpC = new PdfPCell(new Phrase("CLAVE DE ACCESO", russian));
		cellpC.setColspan(2);

		PdfPCell cellImage;
		cellImage = new PdfPCell(img, true);
		cellImage.setColspan(2);

		PdfPTable tableInformacionDerecha = new PdfPTable(2);
		tableInformacionDerecha.addCell(new Paragraph("   R.U.C ", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.IDENTIFICACION_EMPRESA, russian));
		// tableInformacionDerecha.getDefaultCell().setColspan(2);

		tableInformacionDerecha.addCell(cellp);

		tableInformacionDerecha.getDefaultCell().setColspan(1);
		tableInformacionDerecha.addCell(new Paragraph("No.", russian));
		tableInformacionDerecha.addCell(new Paragraph(
				listCabecera.ESTABLECIMIENTO + "-" + listCabecera.PUNTO_EMISION + "-" + listCabecera.SECUENCIAL,
				russian));
		tableInformacionDerecha.getDefaultCell().setColspan(2);
		tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.CLAVE_ACCESO, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("   FECHA DE AUTORIZACION:", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.FECHA_ENVIO_SRI, fuente1));
		tableInformacionDerecha.addCell("");

		tableInformacionDerecha.addCell("");
		// tableInformacionDerecha.getDefaultCell().setColspan(2);
		tableInformacionDerecha.addCell(cellImage);

		Paragraph parag1 = new Paragraph("DIRECCION: ", russianP);
		parag1.add(new Chunk(listCabecera.DIRECCION_EMPRESA, fuente1));
		Paragraph comb = new Paragraph();
		comb.add(parag1);

		
		Paragraph parag1cE = new Paragraph("Contribuyente especial: ", russianP);
		//parag1cE.add(new Chunk(listCabecera.CONTRIBUYENTE_ESPECIAL, fuente5));
		parag1cE.add(new Chunk("NO", fuente5));
		Paragraph combceS = new Paragraph();
		combceS.add(parag1cE);

		Paragraph parag1TT = new Paragraph("Obligado a llevar contabilidad: ", russianP);
		parag1TT.add(new Chunk(listCabecera.OBLIGADO_CONTABILIDAD, fuente5));
		Paragraph combceS1 = new Paragraph();
		combceS1.add(parag1TT);

		Paragraph combceSw = new Paragraph();
		Paragraph parag1cEW = new Paragraph("FECHA DE EMISION: ", russianP);
		parag1cEW.add(new Chunk(listCabecera.FECHA_EMISION, fuente5));
		combceSw.add(parag1cEW);

		// -----------------
		PdfPTable tableInformacionIzquiera = new PdfPTable(1);
		tableInformacionIzquiera.addCell(new Paragraph(listCabecera.RAZON_SOCIAL_EMPRESA, russian));
		tableInformacionIzquiera.addCell(new Paragraph(comb));

		tableInformacionIzquiera.addCell(new Paragraph("SISTEMA DE RENTAS INTERNAS", russian));
		tableInformacionIzquiera.addCell(new Paragraph(combceS));
		tableInformacionIzquiera.addCell(combceSw);

		// ----------------
		PdfPTable tableDivisionInicio = new PdfPTable(2);
		tableDivisionInicio.setWidthPercentage(100);
		Image i = Image.getInstance(pathLogo);
		i.scalePercent(10);

		PdfPCell cell;
		PdfPCell cellDerecha;
		PdfPCell cellIzquierda;
		cellDerecha = new PdfPCell(tableInformacionDerecha);
		cellIzquierda = new PdfPCell(tableInformacionIzquiera);
		cellDerecha.setRowspan(2);

		tableDivisionInicio.addCell(i);

		tableDivisionInicio.addCell(cellDerecha);
		tableDivisionInicio.addCell(cellIzquierda);

		documento.add(tableDivisionInicio);

		// ---------------------

		documento.add(p);// AGREGO ESPACION EN BLANCO

		Paragraph paragCli = new Paragraph("CLIENTE: ", russianP);
		paragCli.add(new Chunk(listCabecera.RAZON_SOCIAL_CLIENTE, fuente1));
		Paragraph paragCli1 = new Paragraph("RUC o CC:", russianP);
		paragCli1.add(new Chunk(listCabecera.IDENTIFICACION_CLIENTE, fuente1));
		Paragraph paragCli2 = new Paragraph("CORREO:", russianP);
		paragCli2.add(new Chunk(listCabecera.CORREO_CLIENTE, fuente1));
		Paragraph paragCli3 = new Paragraph("TELEFONO:", russianP);
		paragCli3.add(new Chunk(listCabecera.TELEFONO_CLIENTE, fuente1));
		Paragraph paragCli4 = new Paragraph("DIRECCION:", russianP);
		paragCli4.add(new Chunk(listCabecera.DIRECCION_CLIENTE, fuente1));

		PdfPTable tableEncabezado = new PdfPTable(2);
		tableEncabezado.setWidthPercentage(100);
		tableEncabezado.addCell(paragCli);
		tableEncabezado.addCell(paragCli1);
		tableEncabezado.addCell(paragCli2);
		tableEncabezado.addCell(paragCli3);
		tableEncabezado.addCell(paragCli4);
		tableEncabezado.addCell("");
		// documento.add( Chunk.NEWLINE );
		documento.add(tableEncabezado);

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableDetalle = new PdfPTable(6);

		tableDetalle.setWidthPercentage(100);

		PdfPCell cellCodigo;
		cellCodigo = new PdfPCell(new Phrase("CODIGO", fuenteNew));
		cellCodigo.setBackgroundColor(BaseColor.RED);
		cellCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCRIPCION;
		cellCodigoDESCRIPCION = new PdfPCell(new Phrase("DESCRIPCION", fuenteNew));
		cellCodigoDESCRIPCION.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCRIPCION.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCRIPCION.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoCANTIDAD;
		cellCodigoCANTIDAD = new PdfPCell(new Phrase("CANTIDAD", fuenteNew));
		cellCodigoCANTIDAD.setBackgroundColor(BaseColor.RED);
		cellCodigoCANTIDAD.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoCANTIDAD.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoPRECIO;
		cellCodigoPRECIO = new PdfPCell(new Phrase("PRECIO", fuenteNew));
		cellCodigoPRECIO.setBackgroundColor(BaseColor.RED);
		cellCodigoPRECIO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoPRECIO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCUENTO;
		cellCodigoDESCUENTO = new PdfPCell(new Phrase("DESCUENTO", fuenteNew));
		cellCodigoDESCUENTO.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCUENTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCUENTO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoIMPORTE;
		cellCodigoIMPORTE = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE.setHorizontalAlignment(Element.ALIGN_CENTER);

		tableDetalle.addCell(cellCodigo);
		tableDetalle.addCell(cellCodigoDESCRIPCION);
		tableDetalle.addCell(cellCodigoCANTIDAD);
		tableDetalle.addCell(cellCodigoPRECIO);
		tableDetalle.addCell(cellCodigoDESCUENTO);
		tableDetalle.addCell(cellCodigoIMPORTE);

		float[] columnWidths = new float[] { 12f, 15f, 15f, 25f, 25f, 25f };
		tableDetalle.setWidths(columnWidths);
		System.out.println("TAMAÑO RECORRIDO PDF:" + result.size());
		for (int x = 0; x < result.size(); x++) {
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_PRINCIPAL, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).DESCRIPCION_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CANTIDAD_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).VALOR_DESCUENTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
		}

		documento.add(tableDetalle);

		PdfPCell c2;
		PdfPCell c3;

		documento.add(p);

		PdfPCell cellCodigoIMPUESTO;
		cellCodigoIMPUESTO = new PdfPCell(new Phrase("IMPUESTO", fuenteNew));
		cellCodigoIMPUESTO.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPUESTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPUESTO.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoTARIFA;
		cellCodigoTARIFA = new PdfPCell(new Phrase("TARIFA", fuenteNew));
		cellCodigoTARIFA.setBackgroundColor(BaseColor.RED);
		cellCodigoTARIFA.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoTARIFA.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoBASEIMPONIBLE;
		cellCodigoBASEIMPONIBLE = new PdfPCell(new Phrase("BASE IMPONIBLE", fuenteNew));
		cellCodigoBASEIMPONIBLE.setBackgroundColor(BaseColor.RED);
		cellCodigoBASEIMPONIBLE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoBASEIMPONIBLE.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoIMPORTE1;
		cellCodigoIMPORTE1 = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE1.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE1.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable tableInfoAdicionalTotal = new PdfPTable(4);
		tableInfoAdicionalTotal.setWidthPercentage(100);
		/*
		 * tableInfoAdicionalTotal.addCell(new Paragraph("IMPUESTO",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("TARIFA",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("BASE IMPONIBLE",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("IMPORTE",fuente));//VALOR DEL IVA
		 */

		tableInfoAdicionalTotal.addCell(cellCodigoIMPUESTO);
		tableInfoAdicionalTotal.addCell(cellCodigoTARIFA);
		tableInfoAdicionalTotal.addCell(cellCodigoBASEIMPONIBLE);
		tableInfoAdicionalTotal.addCell(cellCodigoIMPORTE1);

		tableInfoAdicionalTotal.addCell(new Paragraph("IVA 12", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("12.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.BASE_IMPONIBLE, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_IMPUESTO, fuente));
		
		double valorTotal=0.00; 
		double finalValorTotal=0.00; 
		valorTotal=Double.sum(Double.parseDouble(listCabecera.TOTAL_CON_IMPUESTO),Double.parseDouble(listCabecera.PROPINA));
		finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;

		PdfPTable tableInfoAdicionalTotalFinal = new PdfPTable(2);
		tableInfoAdicionalTotalFinal.setWidthPercentage(100);
		tableInfoAdicionalTotalFinal.addCell(new Paragraph("TOTAL", fuente));
		tableInfoAdicionalTotalFinal.addCell(new Paragraph(String.valueOf(finalValorTotal), fuente));
		documento.add(tableInfoAdicionalTotal);
		documento.add(p);
		documento.add(tableInfoAdicionalTotalFinal);
		documento.close();
	}
	public void GenerarPdfNc(ExternalDataNc listCabecera, List<ExternalDataDetalleNc> result, String pathLogo,
			String ambiente, String tipoEmision, String pathPdf)
			throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException,
			ClassNotFoundException, InstantiationException, IllegalAccessException {

		String ambienteTexto = "";
		String tipoEmisionTexto = "";
		if (ambiente == "1") {
			ambienteTexto = "PRUEBAS";

		} else if (ambiente == "2") {
			ambienteTexto = "PRODUCCION";
		} else {
			ambienteTexto = "ERROR";
		}

		if (tipoEmision == "1") {
			tipoEmisionTexto = "EMISION NORMAL";

		} else {
			tipoEmisionTexto = "ERROR";
		}

		FileOutputStream archivo = new FileOutputStream(pathPdf + listCabecera.CLAVE_ACCESO + ".pdf");
		Document documento = new Document();
		PdfWriter writer = PdfWriter.getInstance(documento, archivo);
		documento.open();

		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
		Chunk c = new Chunk("ESPACIO EN BLANCO", f);
		Paragraph p = new Paragraph(c);

		Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
		Font font = new Font();
		Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);

		// CONFIGURACION FUENTE 1
		Font fuente = new Font();
		fuente.setColor(BaseColor.BLACK);
		fuente.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 2
		Font fuente1 = new Font();
		fuente1.setColor(BaseColor.BLACK);
		fuente1.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 3
		Font fuente2 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente2.setColor(BaseColor.BLACK);
		fuente2.setSize(12);
		// -------------

		// CONFIGURACION FUENTE 3
		Font fuente3 = new Font();
		fuente3.setColor(BaseColor.BLACK);
		fuente3.setSize(8);
		// -------------
		// CONFIGURACION FUENTE 4
		Font fuente4 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente4.setColor(BaseColor.BLACK);
		fuente4.setSize(12);

		// CONFIGURACION FUENTE 4
		Font fuente5 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.BLACK);
		fuente5.setColor(BaseColor.BLACK);
		fuente5.setSize(8);

		// CONFIGURACION FUENTE 4
		Font fuente6 = new Font(FontFamily.HELVETICA, 16.0f, Font.BOLD, BaseColor.WHITE);

		// CONFIGURACION FUENTE 4
		Font fuenteNew = new Font(FontFamily.HELVETICA, 8.0f, Font.BOLD, BaseColor.WHITE);

		BaseFont bf_russian = BaseFont.createFont("C:\\FAC\\assets\\calibri.ttf", "CP1251", BaseFont.EMBEDDED);
		Font russian = new Font(bf_russian, 12.0f, Font.BOLD, BaseColor.BLACK);
		Font russianP = new Font(bf_russian, 10.0f, Font.BOLD, BaseColor.BLACK);
		Font russian16 = new Font(bf_russian, 16.0f, Font.BOLD, BaseColor.WHITE);

		Barcode128 codeEAN = new Barcode128();

		codeEAN.setCode(listCabecera.CLAVE_ACCESO);
		Image img = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
		img.scaleAbsolute(250f, 35f);

		PdfPCell cellp;
		cellp = new PdfPCell(new Phrase("FACTURA", fuente6));
		cellp.setBackgroundColor(BaseColor.RED);
		cellp.setBorder(Rectangle.NO_BORDER);
		cellp.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellp.setColspan(2);

		PdfPCell cellpC;
		cellpC = new PdfPCell(new Phrase("CLAVE DE ACCESO", russian));
		cellpC.setColspan(2);

		PdfPCell cellImage;
		cellImage = new PdfPCell(img, true);
		cellImage.setColspan(2);

		PdfPTable tableInformacionDerecha = new PdfPTable(2);
		tableInformacionDerecha.addCell(new Paragraph("   R.U.C ", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.IDENTIFICACION_EMPRESA, russian));
		// tableInformacionDerecha.getDefaultCell().setColspan(2);

		tableInformacionDerecha.addCell(cellp);

		tableInformacionDerecha.getDefaultCell().setColspan(1);
		tableInformacionDerecha.addCell(new Paragraph("No.", russian));
		tableInformacionDerecha.addCell(new Paragraph(
				listCabecera.ESTABLECIMIENTO + "-" + listCabecera.PUNTO_EMISION + "-" + listCabecera.SECUENCIAL,
				russian));
		tableInformacionDerecha.getDefaultCell().setColspan(2);
		tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.CLAVE_ACCESO, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("   FECHA DE AUTORIZACION:", russian));
		tableInformacionDerecha.addCell(new Paragraph(listCabecera.FECHA_ENVIO_SRI, fuente1));
		tableInformacionDerecha.addCell("");

		tableInformacionDerecha.addCell("");
		// tableInformacionDerecha.getDefaultCell().setColspan(2);
		tableInformacionDerecha.addCell(cellImage);

		Paragraph parag1 = new Paragraph("DIRECCION: ", russianP);
		parag1.add(new Chunk(listCabecera.DIRECCION_EMPRESA, fuente1));
		Paragraph comb = new Paragraph();
		comb.add(parag1);

		Paragraph combceS = new Paragraph();
		Paragraph parag1cE = new Paragraph("Contribuyente especial: ", russianP);
		//parag1cE.add(new Chunk(listCabecera.CONTRIBUYENTE_ESPECIAL, fuente5));
		parag1cE.add(new Chunk("NO", fuente5));//averiguar que estan enviadno aqui

		combceS.add(parag1cE);

		Paragraph parag1TT = new Paragraph("Obligado a llevar contabilidad: ", russianP);
		parag1TT.add(new Chunk(listCabecera.OBLIGADO_CONTABILIDAD, fuente5));

		combceS.add(parag1TT);

		Paragraph combceSw = new Paragraph();
		Paragraph parag1cEW = new Paragraph("FECHA DE EMISION: ", russianP);
		parag1cEW.add(new Chunk(listCabecera.FECHA_EMISION, fuente5));
		combceSw.add(parag1cEW);

		// -----------------
		PdfPTable tableInformacionIzquiera = new PdfPTable(1);
		tableInformacionIzquiera.addCell(new Paragraph(listCabecera.RAZON_SOCIAL_EMPRESA, russian));
		tableInformacionIzquiera.addCell(new Paragraph(comb));

		tableInformacionIzquiera.addCell(new Paragraph("SISTEMA DE RENTAS INTERNAS", russian));
		tableInformacionIzquiera.addCell(new Paragraph(combceS));
		tableInformacionIzquiera.addCell(combceSw);

		// ----------------
		PdfPTable tableDivisionInicio = new PdfPTable(2);
		tableDivisionInicio.setWidthPercentage(100);
		Image i = Image.getInstance(pathLogo);
		i.scalePercent(10);

		PdfPCell cell;
		PdfPCell cellDerecha;
		PdfPCell cellIzquierda;
		cellDerecha = new PdfPCell(tableInformacionDerecha);
		cellIzquierda = new PdfPCell(tableInformacionIzquiera);
		cellDerecha.setRowspan(2);

		tableDivisionInicio.addCell(i);

		tableDivisionInicio.addCell(cellDerecha);
		tableDivisionInicio.addCell(cellIzquierda);

		documento.add(tableDivisionInicio);

		// ---------------------

		documento.add(p);// AGREGO ESPACION EN BLANCO

		Paragraph paragCli = new Paragraph("CLIENTE: ", russianP);
		paragCli.add(new Chunk(listCabecera.RAZON_SOCIAL_CLIENTE, fuente1));
		Paragraph paragCli1 = new Paragraph("RUC o CC:", russianP);
		paragCli1.add(new Chunk(listCabecera.IDENTIFICACION_CLIENTE, fuente1));
		Paragraph paragCli2 = new Paragraph("CORREO:", russianP);
		paragCli2.add(new Chunk(listCabecera.CORREO_CLIENTE, fuente1));
		Paragraph paragCli3 = new Paragraph("TELEFONO:", russianP);
		paragCli3.add(new Chunk(listCabecera.TELEFONO_CLIENTE, fuente1));
		Paragraph paragCli4 = new Paragraph("DIRECCION:", russianP);
		paragCli4.add(new Chunk(listCabecera.DIRECCION_CLIENTE, fuente1));

		PdfPTable tableEncabezado = new PdfPTable(2);
		tableEncabezado.setWidthPercentage(100);
		tableEncabezado.addCell(paragCli);
		tableEncabezado.addCell(paragCli1);
		tableEncabezado.addCell(paragCli2);
		tableEncabezado.addCell(paragCli3);
		tableEncabezado.addCell(paragCli4);
		tableEncabezado.addCell("");
		// documento.add( Chunk.NEWLINE );
		documento.add(tableEncabezado);

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableDetalle = new PdfPTable(6);

		tableDetalle.setWidthPercentage(100);

		PdfPCell cellCodigo;
		cellCodigo = new PdfPCell(new Phrase("CODIGO", fuenteNew));
		cellCodigo.setBackgroundColor(BaseColor.RED);
		cellCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCRIPCION;
		cellCodigoDESCRIPCION = new PdfPCell(new Phrase("DESCRIPCION", fuenteNew));
		cellCodigoDESCRIPCION.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCRIPCION.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCRIPCION.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoCANTIDAD;
		cellCodigoCANTIDAD = new PdfPCell(new Phrase("CANTIDAD", fuenteNew));
		cellCodigoCANTIDAD.setBackgroundColor(BaseColor.RED);
		cellCodigoCANTIDAD.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoCANTIDAD.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoPRECIO;
		cellCodigoPRECIO = new PdfPCell(new Phrase("PRECIO", fuenteNew));
		cellCodigoPRECIO.setBackgroundColor(BaseColor.RED);
		cellCodigoPRECIO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoPRECIO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCUENTO;
		cellCodigoDESCUENTO = new PdfPCell(new Phrase("DESCUENTO", fuenteNew));
		cellCodigoDESCUENTO.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCUENTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCUENTO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoIMPORTE;
		cellCodigoIMPORTE = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE.setHorizontalAlignment(Element.ALIGN_CENTER);

		tableDetalle.addCell(cellCodigo);
		tableDetalle.addCell(cellCodigoDESCRIPCION);
		tableDetalle.addCell(cellCodigoCANTIDAD);
		tableDetalle.addCell(cellCodigoPRECIO);
		tableDetalle.addCell(cellCodigoDESCUENTO);
		tableDetalle.addCell(cellCodigoIMPORTE);

		float[] columnWidths = new float[] { 12f, 15f, 15f, 25f, 25f, 25f };
		tableDetalle.setWidths(columnWidths);
		System.out.println("TAMAÑO RECORRIDO PDF:" + result.size());
		for (int x = 0; x < result.size(); x++) {
			tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_PRINCIPAL, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).DESCRIPCION_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).CANTIDAD_PRODUCTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).VALOR_DESCUENTO, fuente));
			tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO, fuente));
		}

		documento.add(tableDetalle);

		PdfPCell c2;
		PdfPCell c3;

		documento.add(p);

		PdfPCell cellCodigoIMPUESTO;
		cellCodigoIMPUESTO = new PdfPCell(new Phrase("IMPUESTO", fuenteNew));
		cellCodigoIMPUESTO.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPUESTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPUESTO.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoTARIFA;
		cellCodigoTARIFA = new PdfPCell(new Phrase("TARIFA", fuenteNew));
		cellCodigoTARIFA.setBackgroundColor(BaseColor.RED);
		cellCodigoTARIFA.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoTARIFA.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoBASEIMPONIBLE;
		cellCodigoBASEIMPONIBLE = new PdfPCell(new Phrase("BASE IMPONIBLE", fuenteNew));
		cellCodigoBASEIMPONIBLE.setBackgroundColor(BaseColor.RED);
		cellCodigoBASEIMPONIBLE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoBASEIMPONIBLE.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoIMPORTE1;
		cellCodigoIMPORTE1 = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE1.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE1.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable tableInfoAdicionalTotal = new PdfPTable(4);
		tableInfoAdicionalTotal.setWidthPercentage(100);
		/*
		 * tableInfoAdicionalTotal.addCell(new Paragraph("IMPUESTO",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("TARIFA",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("BASE IMPONIBLE",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("IMPORTE",fuente));//VALOR DEL IVA
		 */

		tableInfoAdicionalTotal.addCell(cellCodigoIMPUESTO);
		tableInfoAdicionalTotal.addCell(cellCodigoTARIFA);
		tableInfoAdicionalTotal.addCell(cellCodigoBASEIMPONIBLE);
		tableInfoAdicionalTotal.addCell(cellCodigoIMPORTE1);

		tableInfoAdicionalTotal.addCell(new Paragraph("IVA 12", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("12.00", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.BASE_IMPONIBLE, fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_IMPUESTO, fuente));

		PdfPTable tableInfoAdicionalTotalFinal = new PdfPTable(2);
		tableInfoAdicionalTotalFinal.setWidthPercentage(100);
		tableInfoAdicionalTotalFinal.addCell(new Paragraph("TOTAL", fuente));
		tableInfoAdicionalTotalFinal.addCell(new Paragraph(listCabecera.TOTAL_CON_IMPUESTO, fuente));
		documento.add(tableInfoAdicionalTotal);
		documento.add(p);
		documento.add(tableInfoAdicionalTotalFinal);
		documento.close();
	}

	public void GenerarPdf(String Ambiente, String tipoEmision, String razonSocial, String nombreComercial, String ruc,
			String claveAcceso, String codDoc, String estab, String ptoEmi, String secuencial, String dirMatriz,
			String fechaEmision, String dirEstablecimiento, String obligadoContabilidad,
			String tipoIdentificacionComprador, String razonSocialComprador, String identificacionComprador,
			String direccionComprador, String totalSinImpuestos, String totalDescuento, String codigo,
			String codigoPorcentaje, String baseImponible, String valor, String propina, String moneda,
			String codigoPrincipal, String descripcion, String cantidad, String precioUnitario, String descuento,
			String precioTotalSinImpuestos, String codigo1, String codigoPorcentaje1, String tarifa,
			String baseImponible1, String valor1, String importeTotal, String ambiente, String fechaAutorizacion,
			String nombreArchivo) throws FileNotFoundException, DocumentException, BadElementException, IOException,
			SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		FileOutputStream archivo = new FileOutputStream(pathPdf + nombreArchivo);
		Document documento = new Document();
		PdfWriter writer = PdfWriter.getInstance(documento, archivo);
		documento.open();

		Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
		Chunk c = new Chunk("ESPACIO EN BLANCO", f);
		Paragraph p = new Paragraph(c);

		Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 8);
		Font font = new Font();
		Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);

		// CONFIGURACION FUENTE 1
		Font fuente = new Font();
		fuente.setColor(BaseColor.BLACK);
		fuente.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 2
		Font fuente1 = new Font();
		fuente1.setColor(BaseColor.BLACK);
		fuente1.setSize(9);
		// -------------
		// CONFIGURACION FUENTE 3
		Font fuente2 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente2.setColor(BaseColor.BLACK);
		fuente2.setSize(12);
		// -------------

		// CONFIGURACION FUENTE 3
		Font fuente3 = new Font();
		fuente3.setColor(BaseColor.BLACK);
		fuente3.setSize(8);
		// -------------
		// CONFIGURACION FUENTE 4
		Font fuente4 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
		fuente4.setColor(BaseColor.BLACK);
		fuente4.setSize(12);

		// CONFIGURACION FUENTE 4
		Font fuente5 = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.BLACK);
		fuente5.setColor(BaseColor.BLACK);
		fuente5.setSize(8);

		// CONFIGURACION FUENTE 4
		Font fuente6 = new Font(FontFamily.HELVETICA, 16.0f, Font.BOLD, BaseColor.WHITE);

		// CONFIGURACION FUENTE 4
		Font fuenteNew = new Font(FontFamily.HELVETICA, 8.0f, Font.BOLD, BaseColor.WHITE);

		BaseFont bf_russian = BaseFont.createFont("C:\\FAC\\assets\\calibri.ttf", "CP1251", BaseFont.EMBEDDED);
		Font russian = new Font(bf_russian, 12.0f, Font.BOLD, BaseColor.BLACK);
		Font russianP = new Font(bf_russian, 10.0f, Font.BOLD, BaseColor.BLACK);
		Font russian16 = new Font(bf_russian, 16.0f, Font.BOLD, BaseColor.WHITE);

		Barcode128 codeEAN = new Barcode128();

		codeEAN.setCode("DHFJDSHFKJSDFKDSJKFJ");
		Image img = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
		img.scaleAbsolute(250f, 35f);

		/*
		 * Paragraph preface = new
		 * Paragraph("                    FACTURA",russian16);
		 * preface.setAlignment(Paragraph.ALIGN_CENTER);
		 * preface.setSpacingAfter(70);
		 */

		PdfPCell cellp;
		cellp = new PdfPCell(new Phrase("FACTURA", fuente6));
		cellp.setBackgroundColor(BaseColor.RED);
		cellp.setBorder(Rectangle.NO_BORDER);
		cellp.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellp.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellp.setColspan(2);

		PdfPCell cellpC;
		cellpC = new PdfPCell(new Phrase("CLAVE DE ACCESO", russian));
		// cellpC.setBackgroundColor(BaseColor.BLACK);
		// cellpC.setBorder(Rectangle.NO_BORDER);
		// cellpC.setVerticalAlignment(Element.ALIGN_MIDDLE);
		// cellpC.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellpC.setColspan(2);

		// table.addCell(celdaFinal);

		PdfPTable tableInformacionDerecha = new PdfPTable(2);
		/* tableInformacionDerecha.getDefaultCell().setBorder(1); */
		tableInformacionDerecha.addCell(new Paragraph("   R.U.C ", russian));
		tableInformacionDerecha.addCell(new Paragraph(ruc, russian));
		tableInformacionDerecha.getDefaultCell().setColspan(2);

		tableInformacionDerecha.addCell(cellp);

		tableInformacionDerecha.getDefaultCell().setColspan(1);
		tableInformacionDerecha.addCell(new Paragraph("No.", russian));
		tableInformacionDerecha.addCell(new Paragraph(estab + "-" + ptoEmi + "-" + secuencial, russian));
		tableInformacionDerecha.getDefaultCell().setColspan(2);
		tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n", russian));
		tableInformacionDerecha.addCell(new Paragraph(claveAcceso, fuente1));
		tableInformacionDerecha.addCell(new Paragraph("   FECHA DE AUTORIZACION:", russian));
		tableInformacionDerecha.addCell(new Paragraph(fechaAutorizacion, fuente1));
		tableInformacionDerecha.addCell(cellpC);
		tableInformacionDerecha.addCell(new PdfPCell(img));

		// tableInformacionDerecha.addCell(new PdfPCell(new Phrase("R2C2-5")) {
		// Colspan = 2 });

		// tableInformacionDerecha.addCell(new Paragraph("XXX",fuente1));

		/*
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   R.U.C .:"+ruc,fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new Paragraph("   FACTURA",fuente4));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * 
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   N°:"+estab+"-"+ptoEmi+"-"+secuencial,fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * 
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   NUMERO DE AUTORIZACION\n",fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new Paragraph(claveAcceso,fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   FECHA Y HORA DE\n   AUTORIZACION:"+fechaAutorizacion,
		 * fuente1)); tableInformacionDerecha.addCell(new
		 * Paragraph("BLANCO",fcell)); tableInformacionDerecha.addCell(new
		 * Paragraph("   AMBIENTE:"+ambiente,fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   EMISION: Normal",fuente1));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new
		 * Paragraph("   CLAVE DE ACCESO",fuente2));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
		 * tableInformacionDerecha.addCell(new PdfPCell(img));
		 * tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));¨
		 */

		/*
		 * PdfPCell celdaFinal = new PdfPCell(new
		 * Paragraph("Final de la tabla"));
		 * 
		 * // Indicamos cuantas columnas ocupa la celda
		 * celdaFinal.setColspan(2);
		 */

		Paragraph parag1 = new Paragraph("DIRECCION: ", russianP);
		parag1.add(new Chunk("AV. ILALO Y GRIBALDO MIÑO.", fuente1));
		Paragraph comb = new Paragraph();
		comb.add(parag1);
		// comb.add(parag2);

		/*
		 * Paragraph parag1c=new Paragraph("CORREO: ",russian); Paragraph
		 * parag2c=new Paragraph("hvivanco@3-s.com.ec.",fuente1); Paragraph
		 * combc=new Paragraph(); comb.add(parag1c); comb.add(parag2c);
		 * 
		 * Paragraph parag1T=new Paragraph("TELEFONO: ",russian); Paragraph
		 * parag2T=new Paragraph("222222222",fuente1); Paragraph combT=new
		 * Paragraph(); comb.add(parag1T); comb.add(parag2T);
		 */

		Paragraph combceS = new Paragraph();
		Paragraph parag1cE = new Paragraph("Contribuyente especial: ", russianP);
		parag1cE.add(new Chunk("NO", fuente5));
		// Paragraph parag2cE=new Paragraph("NO",fuente1);

		combceS.add(parag1cE);
		// combceS.add(parag2cE);

		Paragraph parag1TT = new Paragraph("Obligado a llevar contabilidad: ", russianP);
		parag1TT.add(new Chunk("SI", fuente5));
		// Paragraph parag2TT=new Paragraph("SI",fuente1);

		combceS.add(parag1TT);
		// combceS.add(parag2TT);

		Paragraph combceSw = new Paragraph();
		Paragraph parag1cEW = new Paragraph("FECHA DE EMISION: ", russianP);
		parag1cEW.add(new Chunk("18/10/2018", fuente5));
		// Paragraph parag2cEW=new Paragraph("18/10/2018",fuente1);
		combceSw.add(parag1cEW);
		// combceSw.add(parag2cEW);

		// -----------------
		PdfPTable tableInformacionIzquiera = new PdfPTable(1);
		tableInformacionIzquiera.addCell(new Paragraph("HERNY VIVANCO", russian));
		tableInformacionIzquiera.addCell(new Paragraph(comb));

		tableInformacionIzquiera.addCell(new Paragraph("SISTEMA DE RENTAS INTERNAS", russian));
		tableInformacionIzquiera.addCell(new Paragraph(combceS));
		tableInformacionIzquiera.addCell(combceSw);

		// ----------------
		PdfPTable tableDivisionInicio = new PdfPTable(2);
		tableDivisionInicio.setWidthPercentage(100);
		Image i = Image.getInstance(pathLogo);
		i.scalePercent(10);

		PdfPCell cell;
		PdfPCell cellDerecha;
		PdfPCell cellIzquierda;
		cellDerecha = new PdfPCell(tableInformacionDerecha);
		cellIzquierda = new PdfPCell(tableInformacionIzquiera);
		cellDerecha.setRowspan(2);
		// cellIzquierda.setRowspan(2);

		tableDivisionInicio.addCell(i);

		tableDivisionInicio.addCell(cellDerecha);
		tableDivisionInicio.addCell(cellIzquierda);
		// tableDivisionInicio.addCell(new Paragraph("\n\n\n\n DISASHOP\n\n\n\n
		// Dir Matriz:"+dirMatriz+" \n\n\n\n Dir Sucursal:"+dirEstablecimiento+"
		// \n\n\n\n Contribuyente Especial N \n\n\n\n OBLIGADO A LLEVAR
		// CONTABILIDAD "+obligadoContabilidad,fuente3));
		documento.add(tableDivisionInicio);

		// ---------------------

		documento.add(p);// AGREGO ESPACION EN BLANCO

		Paragraph paragCli = new Paragraph("CLIENTE: ", russianP);
		paragCli.add(new Chunk(razonSocialComprador, fuente1));
		Paragraph paragCli1 = new Paragraph("RUC o CC:", russianP);
		// paragCli.add(new Chunk(" RUC o CC:",fuente1));
		paragCli1.add(new Chunk(identificacionComprador, fuente1));
		// Paragraph combCli=new Paragraph();
		// combCli.add(paragCli);
		Paragraph paragCli2 = new Paragraph("CORREO:", russianP);
		paragCli2.add(new Chunk(razonSocialComprador, fuente1));
		Paragraph paragCli3 = new Paragraph("TELEFONO:", russianP);
		paragCli3.add(new Chunk(razonSocialComprador, fuente1));
		Paragraph paragCli4 = new Paragraph("DIRECCION:", russianP);
		paragCli4.add(new Chunk(razonSocialComprador, fuente1));

		PdfPTable tableEncabezado = new PdfPTable(2);
		tableEncabezado.setWidthPercentage(100);
		// tableEncabezado.addCell(new Paragraph("Razón Social / Nombres y
		// Apellidos:"+razonSocialComprador+"
		// Identificacion:"+identificacionComprador+" \n\n Fecha
		// Emision:"+fechaEmision+" Guia Remision:",fuente));
		tableEncabezado.addCell(paragCli);
		tableEncabezado.addCell(paragCli1);
		tableEncabezado.addCell(paragCli2);
		tableEncabezado.addCell(paragCli3);
		tableEncabezado.addCell(paragCli4);
		tableEncabezado.addCell("");
		// documento.add( Chunk.NEWLINE );
		documento.add(tableEncabezado);

		documento.add(p);// AGREGO ESPACION EN BLANCO

		PdfPTable tableDetalle = new PdfPTable(6);

		tableDetalle.setWidthPercentage(100);

		PdfPCell cellCodigo;
		cellCodigo = new PdfPCell(new Phrase("CODIGO", fuenteNew));
		cellCodigo.setBackgroundColor(BaseColor.RED);
		// cellCodigo.setBorder(Rectangle.NO_BORDER);
		cellCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCRIPCION;
		cellCodigoDESCRIPCION = new PdfPCell(new Phrase("DESCRIPCION", fuenteNew));
		cellCodigoDESCRIPCION.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCRIPCION.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCRIPCION.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoCANTIDAD;
		cellCodigoCANTIDAD = new PdfPCell(new Phrase("CANTIDAD", fuenteNew));
		cellCodigoCANTIDAD.setBackgroundColor(BaseColor.RED);
		cellCodigoCANTIDAD.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoCANTIDAD.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoPRECIO;
		cellCodigoPRECIO = new PdfPCell(new Phrase("PRECIO", fuenteNew));
		cellCodigoPRECIO.setBackgroundColor(BaseColor.RED);
		cellCodigoPRECIO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoPRECIO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoDESCUENTO;
		cellCodigoDESCUENTO = new PdfPCell(new Phrase("DESCUENTO", fuenteNew));
		cellCodigoDESCUENTO.setBackgroundColor(BaseColor.RED);
		cellCodigoDESCUENTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoDESCUENTO.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellCodigoIMPORTE;
		cellCodigoIMPORTE = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE.setHorizontalAlignment(Element.ALIGN_CENTER);

		tableDetalle.addCell(cellCodigo);
		// tableDetalle.addCell(new Paragraph("Cod.\nAuxiliar",fuente));
		tableDetalle.addCell(cellCodigoDESCRIPCION);
		tableDetalle.addCell(cellCodigoCANTIDAD);
		/*
		 * tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
		 * tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
		 * tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
		 */
		tableDetalle.addCell(cellCodigoPRECIO);
		tableDetalle.addCell(cellCodigoDESCUENTO);
		tableDetalle.addCell(cellCodigoIMPORTE);

		tableDetalle.addCell(new Paragraph(codigoPrincipal, fuente));
		// tableDetalle.addCell(new Paragraph("",fuente));
		tableDetalle.addCell(new Paragraph(descripcion, fuente));
		tableDetalle.addCell(new Paragraph(cantidad, fuente));

		// tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen
		// detalle adicional
		// tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen
		// detalle adicional
		// tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen
		// detalle adicional
		tableDetalle.addCell(new Paragraph(precioTotalSinImpuestos, fuente));
		tableDetalle.addCell(new Paragraph(descuento, fuente));
		tableDetalle.addCell(new Paragraph(precioTotalSinImpuestos, fuente));

		float[] columnWidths = new float[] { 12f, 15f, 15f, 25f, 25f, 25f };
		tableDetalle.setWidths(columnWidths);
		documento.add(tableDetalle);

		PdfPCell c2;
		PdfPCell c3;

		documento.add(p);

		/*
		 * PdfPTable tableInfoAdicional = new PdfPTable(1);
		 * tableInfoAdicional.setWidthPercentage(100);
		 * 
		 * tableInfoAdicional.addCell(new
		 * Paragraph("Información Adicional \n\nDirección: "+direccionComprador+
		 * "\n\nTeléfono:\n\nEmail:\n\n",fuente)); float[] columnWidthsInfoA =
		 * new float[] {50f}; tableInfoAdicional.setWidths(columnWidthsInfoA);
		 * 
		 * c2 =new PdfPCell(tableInfoAdicional);
		 */

		PdfPCell cellCodigoIMPUESTO;
		cellCodigoIMPUESTO = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPUESTO.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPUESTO.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPUESTO.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoTARIFA;
		cellCodigoTARIFA = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoTARIFA.setBackgroundColor(BaseColor.RED);
		cellCodigoTARIFA.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoTARIFA.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoBASEIMPONIBLE;
		cellCodigoBASEIMPONIBLE = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoBASEIMPONIBLE.setBackgroundColor(BaseColor.RED);
		cellCodigoBASEIMPONIBLE.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoBASEIMPONIBLE.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell cellCodigoIMPORTE1;
		cellCodigoIMPORTE1 = new PdfPCell(new Phrase("IMPORTE", fuenteNew));
		cellCodigoIMPORTE1.setBackgroundColor(BaseColor.RED);
		cellCodigoIMPORTE1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cellCodigoIMPORTE1.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPTable tableInfoAdicionalTotal = new PdfPTable(4);
		tableInfoAdicionalTotal.setWidthPercentage(100);
		tableInfoAdicionalTotal.addCell(new Paragraph("IMPUESTO", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("TARIFA", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("BASE IMPONIBLE", fuente));
		tableInfoAdicionalTotal.addCell(new Paragraph("IMPORTE", fuente));// VALOR
																			// DEL
																			// IVA

		tableInfoAdicionalTotal.addCell(cellCodigoIMPUESTO);
		tableInfoAdicionalTotal.addCell(cellCodigoTARIFA);
		tableInfoAdicionalTotal.addCell(cellCodigoBASEIMPONIBLE);
		tableInfoAdicionalTotal.addCell(cellCodigoIMPORTE1);

		// tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL",fuente));
		// tableInfoAdicionalTotal.addCell(new Paragraph(importeTotal,fuente));

		/*
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("SUBTOTAL "+DescripcionSql,fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph(totalSinImpuestos,fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL 0%",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("SUBTOTAL No objeto de IVA",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * 
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph("SUBTOTAL Exento de IVA",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("DESCUENTO",fuente));
		 * tableInfoAdicionalTotal.addCell(new
		 * Paragraph(totalDescuento,fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("ICE",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * 
		 * 
		 * tableInfoAdicionalTotal.addCell(new Paragraph("IRBPNR",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("PROPINA",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL",fuente));
		 * tableInfoAdicionalTotal.addCell(new Paragraph(importeTotal,fuente));
		 */
		// c3 =new PdfPCell(tableInfoAdicionalTotal);

		/*
		 * PdfPTable TableDivision=new PdfPTable(2);
		 * TableDivision.setWidthPercentage(100); TableDivision.addCell(c2);
		 * TableDivision.addCell(c3);
		 */
		PdfPTable tableInfoAdicionalTotalFinal = new PdfPTable(2);
		tableInfoAdicionalTotalFinal.setWidthPercentage(100);
		tableInfoAdicionalTotalFinal.addCell(new Paragraph("TOTAL", fuente));
		tableInfoAdicionalTotalFinal.addCell(new Paragraph("100", fuente));
		documento.add(tableInfoAdicionalTotal);
		documento.add(p);
		documento.add(tableInfoAdicionalTotalFinal);
		documento.close();
	}

}

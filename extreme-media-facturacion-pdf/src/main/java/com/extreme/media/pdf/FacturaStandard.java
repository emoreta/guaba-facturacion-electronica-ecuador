package com.extreme.media.pdf;

import com.extreme.media.entityes.ExternalData;
import com.extreme.media.entityes.ExternalDataDetalle;
import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaStandard {
	//private String pathLogo="src/imagenes/disashop.png";
	private String pathPdf="C:\\XMLPRUEBA\\";
	//private String nombrePdf="facturaPdf.pdf";
	private String DescripcionSql="por definir"; 
	private String pathLogo="C:\\firmasEnviar\\LOGO\\GuabaEstudioTranparente.png";
	
	
	public void GenerarPdf(ExternalData listCabecera,List<ExternalDataDetalle> result,String pathLogo,String ambiente,String tipoEmision,String pathPdf) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
            
        
                String ambienteTexto="";
                String tipoEmisionTexto="";
                if(ambiente.equals("1"))
                {
                	ambienteTexto="PRUEBAS";
                	
                }
                else if(ambiente.equals("2"))
                {
                	ambienteTexto="PRODUCCION";
                }
                else
                {
                	ambienteTexto="ERROR";
                }
                	
                
                if(tipoEmision.equals("1"))
                {
                	tipoEmisionTexto="EMISION NORMAL";
                	
                }
                else 
                {
                	tipoEmisionTexto="ERROR";
                }
               
               
            
          FileOutputStream archivo = new FileOutputStream(pathPdf+listCabecera.CLAVE_ACCESO+".pdf");       
	      Document documento = new Document();
	      PdfWriter writer= PdfWriter.getInstance(documento, archivo);       
	      documento.open(); 
	      
	      Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
	      Chunk c = new Chunk("ESPACIO EN BLANCO",f);
	      Paragraph p = new Paragraph(c);
	      
         //CONFIGURACION FUENTE 1
	      Font fuente=new Font();
	      fuente.setColor(BaseColor.BLACK);
	      fuente.setSize(9);
	      //-------------
	    //CONFIGURACION FUENTE 2
	      Font fuente1=new Font();
	      fuente1.setColor(BaseColor.BLACK);
	      fuente1.setSize(9);
	      //-------------
	    //CONFIGURACION FUENTE 3
	      Font fuente2=new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      fuente2.setColor(BaseColor.BLACK);
	      fuente2.setSize(12);
	      //-------------
	      
	    //CONFIGURACION FUENTE 3
	      Font fuente3=new Font();
	      fuente3.setColor(BaseColor.BLACK);
	      fuente3.setSize(8);
	      //-------------
              //CONFIGURACION FUENTE 4
	      Font fuente4=new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      fuente4.setColor(BaseColor.BLACK);
	      fuente4.setSize(12);
              
	      
	      Barcode128 codeEAN = new  Barcode128();

	      codeEAN.setCode(listCabecera.CLAVE_ACCESO);
	      Image img = codeEAN.createImageWithBarcode( writer.getDirectContent(), null, null);
	      img.scaleAbsolute(250f, 35f);

	      PdfPTable tableInformacionDerecha = new PdfPTable(1);
	      tableInformacionDerecha.getDefaultCell().setBorder(0);
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   R.U.C .:"+listCabecera.IDENTIFICACION_EMPRESA,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   FACTURA",fuente4));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      
	      tableInformacionDerecha.addCell(new Paragraph("   N°:"+listCabecera.ESTABLECIMIENTO+"-"+listCabecera.PUNTO_EMISION+"-"+listCabecera.SECUENCIAL,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));

	      tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n",fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph(listCabecera.CLAVE_ACCESO,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   FECHA Y HORA DE\n   AUTORIZACION:"+listCabecera.FECHA_ENVIO_SRI,fuente1));//viene del resultado del sri parametrizar
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   AMBIENTE:"+ambienteTexto,fuente1));// viene de configuraciones
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   EMISION: "+tipoEmisionTexto,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   CLAVE DE ACCESO",fuente2));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new PdfPCell(img));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      
	      //-----------------
	      
	      //----------------
	      PdfPTable tableDivisionInicio = new PdfPTable(2);
	      tableDivisionInicio.setWidthPercentage(100);
	      Image i=Image.getInstance(pathLogo);
	      i.scalePercent(10);
	      PdfPCell cell;
	      PdfPCell cellDerecha;
	      cellDerecha=new PdfPCell(tableInformacionDerecha);
	      cellDerecha.setRowspan(2);

	      tableDivisionInicio.addCell( i);
	      tableDivisionInicio.addCell( cellDerecha);
	      tableDivisionInicio.addCell(new Paragraph("\n\n\n\n   "+listCabecera.RAZON_SOCIAL_EMPRESA+"\n\n\n\n   Dir Matriz:"+listCabecera.DIRECCION_EMPRESA+"  \n\n\n\n   Dir Sucursal:"+listCabecera.DIRECCION_ESTABLECIMIENTO+"  \n\n\n\n   Contribuyente Especial N  \n\n\n\n   OBLIGADO A LLEVAR CONTABILIDAD "+listCabecera.OBLIGADO_CONTABILIDAD,fuente3));
	      documento.add(tableDivisionInicio);
	      
	      //---------------------
	      
	      documento.add(p);//AGREGO ESPACION EN BLANCO
	      
	      PdfPTable tableEncabezado = new PdfPTable(1);
	      tableEncabezado.setWidthPercentage(100); 
		  tableEncabezado.addCell(new Paragraph("Razón Social / Nombres y Apellidos:"+listCabecera.RAZON_SOCIAL_CLIENTE+"      Identificacion:"+listCabecera.IDENTIFICACION_CLIENTE+" \n\n Fecha Emision:"+listCabecera.FECHA_EMISION+"       Guia Remision:",fuente));
		  //documento.add( Chunk.NEWLINE );
		  documento.add(tableEncabezado);

		  documento.add(p);//AGREGO ESPACION EN BLANCO
			

	      PdfPTable tableDetalle = new PdfPTable(10);
	      
	      tableDetalle.setWidthPercentage(100);

	      tableDetalle.addCell(new Paragraph("Cod.\nPrincipal",fuente));
	      tableDetalle.addCell(new Paragraph("Cod.\nAuxiliar",fuente));
	      tableDetalle.addCell(new Paragraph("Cant",fuente));
	      tableDetalle.addCell(new Paragraph("Descripcion",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Precio\nUnitario",fuente));
	      tableDetalle.addCell(new Paragraph("Descuento",fuente));
	      tableDetalle.addCell(new Paragraph("Precio Total",fuente));
	      
	      float[] columnWidths = new float[] {12f, 15f, 15f, 25f,25f, 25f, 25f, 12f,12f,12f};
	      tableDetalle.setWidths(columnWidths);
	      System.out.println("TAMAÑO RECORRIDO PDF:"+result.size());
	      for(int x=0;x<result.size();x++)
	      {
	      tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_PRINCIPAL,fuente));
	      tableDetalle.addCell(new Paragraph(result.get(x).CODIGO_AUXILIAR,fuente));
	      tableDetalle.addCell(new Paragraph(result.get(x).CANTIDAD_PRODUCTO,fuente));
	      tableDetalle.addCell(new Paragraph(result.get(x).DESCRIPCION_PRODUCTO ,fuente));
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO,fuente));
	      tableDetalle.addCell(new Paragraph(result.get(x).VALOR_DESCUENTO,fuente));
	      tableDetalle.addCell(new Paragraph(result.get(x).PRECIO_UNITARIO,fuente));
	      
	      }
	      documento.add(tableDetalle);
	      
	      
	      
	      
	      PdfPCell c2;
	      PdfPCell c3;

	      PdfPTable tableInfoAdicional = new PdfPTable(1);
	      tableInfoAdicional.setWidthPercentage(100);
	     
	      tableInfoAdicional.addCell(new Paragraph("Información Adicional \n\nDirección: "+listCabecera.DIRECCION_CLIENTE+"\n\nTeléfono:"+listCabecera.TELEFONO_CLIENTE+"\n\nEmail:"+listCabecera.CORREO_CLIENTE+"\n\n",fuente));
	      float[] columnWidthsInfoA = new float[] {50f};
	      tableInfoAdicional.setWidths(columnWidthsInfoA);

	      c2 =new PdfPCell(tableInfoAdicional);
	      
	      double valorTotal=0.00; 
			double finalValorTotal=0.00; 
			valorTotal=Double.sum(Double.parseDouble(listCabecera.TOTAL_CON_IMPUESTO),Double.parseDouble(listCabecera.PROPINA));
			finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
	      
	      PdfPTable tableInfoAdicionalTotal = new PdfPTable(2);
	      //tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL "+DescripcionSql,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL ",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL 0%",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL No objeto de IVA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL SIN IMPUESTOS",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_SIN_IMPUESTO,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL Exento de IVA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("DESCUENTO",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_DESCUENTO,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("ICE",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("IVA ",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_IMPUESTO,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("IRBPNR",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("PROPINA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.PROPINA,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL",fuente));
	      //tableInfoAdicionalTotal.addCell(new Paragraph(listCabecera.TOTAL_CON_IMPUESTO,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(String.valueOf(finalValorTotal),fuente));
	      
	      c3 =new PdfPCell(tableInfoAdicionalTotal);
	    
	      PdfPTable TableDivision=new PdfPTable(2);
	      TableDivision.setWidthPercentage(100);
	      TableDivision.addCell(c2);
	      TableDivision.addCell(c3);
	      documento.add(TableDivision);
	      documento.close();   
    }
	
	public void GenerarPdf(String Ambiente,String tipoEmision,String razonSocial,String nombreComercial,
            String ruc,String claveAcceso,String codDoc,String estab,String ptoEmi,String secuencial,String dirMatriz,String fechaEmision,
            String dirEstablecimiento,String obligadoContabilidad,String tipoIdentificacionComprador,String razonSocialComprador,String identificacionComprador,
            String direccionComprador,String totalSinImpuestos,String totalDescuento,String codigo,String codigoPorcentaje,String baseImponible,String valor,
            String propina,String moneda,String codigoPrincipal,String descripcion,String cantidad,String precioUnitario,String descuento,String precioTotalSinImpuestos,
            String codigo1,String codigoPorcentaje1,String tarifa,String baseImponible1,String valor1,String importeTotal,String ambiente,String fechaAutorizacion,String nombreArchivo) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
            
        
                        
            
          FileOutputStream archivo = new FileOutputStream(pathPdf+nombreArchivo);       
	      Document documento = new Document();
	      PdfWriter writer= PdfWriter.getInstance(documento, archivo);       
	      documento.open(); 
	      
	      Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      Font fcell = new Font(FontFamily.TIMES_ROMAN, 9.0f, Font.BOLD, BaseColor.WHITE);
	      Chunk c = new Chunk("ESPACIO EN BLANCO",f);
	      Paragraph p = new Paragraph(c);
	      
         //CONFIGURACION FUENTE 1
	      Font fuente=new Font();
	      fuente.setColor(BaseColor.BLACK);
	      fuente.setSize(9);
	      //-------------
	    //CONFIGURACION FUENTE 2
	      Font fuente1=new Font();
	      fuente1.setColor(BaseColor.BLACK);
	      fuente1.setSize(9);
	      //-------------
	    //CONFIGURACION FUENTE 3
	      Font fuente2=new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      fuente2.setColor(BaseColor.BLACK);
	      fuente2.setSize(12);
	      //-------------
	      
	    //CONFIGURACION FUENTE 3
	      Font fuente3=new Font();
	      fuente3.setColor(BaseColor.BLACK);
	      fuente3.setSize(8);
	      //-------------
              //CONFIGURACION FUENTE 4
	      Font fuente4=new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, BaseColor.WHITE);
	      fuente4.setColor(BaseColor.BLACK);
	      fuente4.setSize(12);
              
	      
	      Barcode128 codeEAN = new  Barcode128();

	      codeEAN.setCode(claveAcceso);
	      Image img = codeEAN.createImageWithBarcode( writer.getDirectContent(), null, null);
	      img.scaleAbsolute(250f, 35f);

	      PdfPTable tableInformacionDerecha = new PdfPTable(1);
	      tableInformacionDerecha.getDefaultCell().setBorder(0);
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   R.U.C .:"+ruc,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   FACTURA",fuente4));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      
	      tableInformacionDerecha.addCell(new Paragraph("   N°:"+estab+"-"+ptoEmi+"-"+secuencial,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));

	      tableInformacionDerecha.addCell(new Paragraph("   NUMERO DE AUTORIZACION\n",fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph(claveAcceso,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   FECHA Y HORA DE\n   AUTORIZACION:"+fechaAutorizacion,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   AMBIENTE:"+ambiente,fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   EMISION: Normal",fuente1));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new Paragraph("   CLAVE DE ACCESO",fuente2));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      tableInformacionDerecha.addCell(new PdfPCell(img));
	      tableInformacionDerecha.addCell(new Paragraph("BLANCO",fcell));
	      
	      //-----------------
	      
	      //----------------
	      PdfPTable tableDivisionInicio = new PdfPTable(2);
	      tableDivisionInicio.setWidthPercentage(100);
	      Image i=Image.getInstance(pathLogo);
	      i.scalePercent(10);
	      PdfPCell cell;
	      PdfPCell cellDerecha;
	      cellDerecha=new PdfPCell(tableInformacionDerecha);
	      cellDerecha.setRowspan(2);

	      tableDivisionInicio.addCell( i);
	      tableDivisionInicio.addCell( cellDerecha);
	      tableDivisionInicio.addCell(new Paragraph("\n\n\n\n   DISASHOP\n\n\n\n   Dir Matriz:"+dirMatriz+"  \n\n\n\n   Dir Sucursal:"+dirEstablecimiento+"  \n\n\n\n   Contribuyente Especial N  \n\n\n\n   OBLIGADO A LLEVAR CONTABILIDAD "+obligadoContabilidad,fuente3));
	      documento.add(tableDivisionInicio);
	      
	      //---------------------
	      
	      documento.add(p);//AGREGO ESPACION EN BLANCO
	      
	      PdfPTable tableEncabezado = new PdfPTable(1);
	      tableEncabezado.setWidthPercentage(100); 
		  tableEncabezado.addCell(new Paragraph("Razón Social / Nombres y Apellidos:"+razonSocialComprador+"      Identificacion:"+identificacionComprador+" \n\n Fecha Emision:"+fechaEmision+"       Guia Remision:",fuente));
		  //documento.add( Chunk.NEWLINE );
		  documento.add(tableEncabezado);

		  documento.add(p);//AGREGO ESPACION EN BLANCO
			

	      PdfPTable tableDetalle = new PdfPTable(10);
	      
	      tableDetalle.setWidthPercentage(100);

	      tableDetalle.addCell(new Paragraph("Cod.\nPrincipal",fuente));
	      tableDetalle.addCell(new Paragraph("Cod.\nAuxiliar",fuente));
	      tableDetalle.addCell(new Paragraph("Cant",fuente));
	      tableDetalle.addCell(new Paragraph("Descripcion",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Detalle\nAdicional",fuente));
	      tableDetalle.addCell(new Paragraph("Precio\nUnitario",fuente));
	      tableDetalle.addCell(new Paragraph("Descuento",fuente));
	      tableDetalle.addCell(new Paragraph("Precio Total",fuente));
	      
	      tableDetalle.addCell(new Paragraph(codigoPrincipal,fuente));
	      tableDetalle.addCell(new Paragraph("",fuente));
	      tableDetalle.addCell(new Paragraph(cantidad,fuente));
	      tableDetalle.addCell(new Paragraph(descripcion ,fuente));
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph("",fuente));//en blanco no tienen detalle adicional
	      tableDetalle.addCell(new Paragraph(precioTotalSinImpuestos,fuente));
	      tableDetalle.addCell(new Paragraph(descuento,fuente));
	      tableDetalle.addCell(new Paragraph(precioTotalSinImpuestos,fuente));
	      
	      float[] columnWidths = new float[] {12f, 15f, 15f, 25f,25f, 25f, 25f, 12f,12f,12f};
	      tableDetalle.setWidths(columnWidths);
	      documento.add(tableDetalle);
	      
	      PdfPCell c2;
	      PdfPCell c3;

	      PdfPTable tableInfoAdicional = new PdfPTable(1);
	      tableInfoAdicional.setWidthPercentage(100);
	     
	      tableInfoAdicional.addCell(new Paragraph("Información Adicional \n\nDirección: "+direccionComprador+"\n\nTeléfono:\n\nEmail:\n\n",fuente));
	      float[] columnWidthsInfoA = new float[] {50f};
	      tableInfoAdicional.setWidths(columnWidthsInfoA);

	      c2 =new PdfPCell(tableInfoAdicional);
	      
	      PdfPTable tableInfoAdicionalTotal = new PdfPTable(2);
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL "+DescripcionSql,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(totalSinImpuestos,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL 0%",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL No objeto de IVA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL SIN IMPUESTOS",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(totalSinImpuestos,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("SUBTOTAL Exento de IVA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("DESCUENTO",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(totalDescuento,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("ICE",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("IVA "+DescripcionSql,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(valor,fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("IRBPNR",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("PROPINA",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("0.00",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph("VALOR TOTAL",fuente));
	      tableInfoAdicionalTotal.addCell(new Paragraph(importeTotal,fuente));
	      c3 =new PdfPCell(tableInfoAdicionalTotal);
	    
	      PdfPTable TableDivision=new PdfPTable(2);
	      TableDivision.setWidthPercentage(100);
	      TableDivision.addCell(c2);
	      TableDivision.addCell(c3);
	      documento.add(TableDivision);
	      documento.close();   
    }
	
	

}

package com.extreme.media.struct;

import java.text.DecimalFormat;
import java.util.List;

import com.extreme.media.entity.FacturaDinamico;
import com.extreme.media.entity.FacturaFijo;

public class Struct {
	
	public String StructFactura(FacturaFijo ff,List<FacturaDinamico> fd,String claveacceso)	{
		
		double valorTotal=0.00; 
		double finalValorTotal=0.00; 
		valorTotal=Double.sum(Double.parseDouble(ff.getImporteTotal()),Double.parseDouble(ff.getPropina()));
		finalValorTotal=Math.round( valorTotal * 100.00 ) / 100.00;
				
		
		StringBuilder sb=new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		//sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<factura id=\"comprobante\" version=\"1.0.0\">");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoTributaria>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ambiente>"+ff.getAmbiente()+"</ambiente>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tipoEmision>"+ff.getTipoEmision()+"</tipoEmision>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<razonSocial>"+ff.getRazonSocial()+"</razonSocial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<nombreComercial>"+ff.getNombreComercial()+"</nombreComercial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ruc>"+ff.getRuc()+"</ruc>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<claveAcceso>"+claveacceso+"</claveAcceso>");//verificar cuando envie la base de datos
		sb.append(System.getProperty("line.separator"));
		sb.append("<codDoc>"+ff.getCodDoc()+"</codDoc>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<estab>"+ff.getEstab()+"</estab>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ptoEmi>"+ff.getPtoEmi()+"</ptoEmi>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<secuencial>"+ff.getSecuencial()+"</secuencial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<dirMatriz>"+ff.getDirMatriz()+"</dirMatriz>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoTributaria>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoFactura>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<fechaEmision>"+ff.getFechaEmision()+"</fechaEmision>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<dirEstablecimiento>"+ff.getDirEstablecimiento()+"</dirEstablecimiento>");
		sb.append(System.getProperty("line.separator"));
		//sb.append("<contribuyenteEspecial>"+ff.getContribuyenteEspecial()+"</contribuyenteEspecial>");
		//sb.append(System.getProperty("line.separator"));
		sb.append("<obligadoContabilidad>"+ff.getObligadoContabilidad()+"</obligadoContabilidad>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tipoIdentificacionComprador>"+ff.getTipoIdentificacionComprador()+"</tipoIdentificacionComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<razonSocialComprador>"+ff.getRazonSocialComprador()+"</razonSocialComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<identificacionComprador>"+ff.getIdentificacionComprador()+"</identificacionComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<direccionComprador> </direccionComprador>");
		sb.append(System.getProperty("line.separator"));
		
		
		sb.append("<totalSinImpuestos>"+ff.getTotalSinImpuestos()+"</totalSinImpuestos>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<totalDescuento>"+ff.getTotalDescuento()+"</totalDescuento>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<totalConImpuestos>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<totalImpuesto>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigo>"+ff.getTotalImpuestoCodigo()+"</codigo>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigoPorcentaje>"+ff.getTotalImpuestoCodigoPorcentaje()+"</codigoPorcentaje>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<baseImponible>"+ff.getTotalImpuestoBaseImponible()+"</baseImponible>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<valor>"+ff.getTotalImpuestoValor()+"</valor>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</totalImpuesto>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</totalConImpuestos>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<propina>"+ff.getPropina()+"</propina>");
		sb.append(System.getProperty("line.separator"));
		//sb.append("<importeTotal>"+ff.getImporteTotal()+"</importeTotal>");
		sb.append("<importeTotal>"+finalValorTotal+"</importeTotal>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<moneda>"+ff.getMoneda()+"</moneda>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoFactura>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<detalles>");
		
		
		
		//dinamico
		int totalDetalles=fd.size();
		double valor=0.00; 
		double baseImponibleIndividual;
		double finalValue ; 
		
		System.out.println("TOTAL:"+totalDetalles);
		for(int i=0;i<totalDetalles;i++)
			{
			
			//CALCULO DE VALOR DETALLE YA QUE NO VIENE EN TABLA
			
			valor=Double.parseDouble(fd.get(i).getPrecioTotalSinImpuesto())*(Double.parseDouble(ff.getImpuestoTarifa())/100);
			finalValue=Math.round( valor * 100.00 ) / 100.00;
			//baseImponibleIndividual=
			
			sb.append(System.getProperty("line.separator"));
			sb.append("<detalle>");
			System.out.println("dentro de bucle");
			sb.append(System.getProperty("line.separator"));
			sb.append("<codigoPrincipal>"+fd.get(i).getCodigoPrincipal()+"</codigoPrincipal>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<codigoAuxiliar>"+fd.get(i).getCodigoAuxiliar()+"</codigoAuxiliar>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<descripcion>"+fd.get(i).getDescripcion()+"</descripcion>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<cantidad>"+fd.get(i).getCantidad()+"</cantidad>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<precioUnitario>"+fd.get(i).getPrecioUnitario()+"</precioUnitario>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<descuento>"+fd.get(i).getDescuento()+"</descuento>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<precioTotalSinImpuesto>"+fd.get(i).getPrecioTotalSinImpuesto()+"</precioTotalSinImpuesto>");
			
			sb.append(System.getProperty("line.separator"));
			sb.append("<impuestos>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<impuesto>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<codigo>"+ff.getImpuestoCodigo()+"</codigo>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<codigoPorcentaje>"+ff.getImpuestoCodigoPorcentaje()+"</codigoPorcentaje>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<tarifa>"+ff.getImpuestoTarifa()+"</tarifa>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<baseImponible>"+fd.get(i).getPrecioTotalSinImpuesto()+"</baseImponible>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<valor>"+finalValue+"</valor>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</impuesto>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</impuestos>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</detalle>");
			}
			
		
		
		sb.append(System.getProperty("line.separator"));
		sb.append("</detalles>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Direccion\" >"+ff.getCampoAdicionalDireccion()+"</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Telefono\" >"+ff.getCampoAdicionalTelefono()+"</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Email\" >"+ff.getCampoAdicionalEmail()+"</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</factura>");

		
		return sb.toString();
	}

}

package com.extreme.media.struct.nc;

import java.util.List;

import com.extreme.media.entity.NotaCreditoDinamico;
import com.extreme.media.entity.NotaCreditoFijo;

public class StructNc {
	public String StructNotaCredito(NotaCreditoFijo ff, List<NotaCreditoDinamico> fd, String claveacceso) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<notaCredito id=\"comprobante\" version=\"1.0.0\">");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoTributaria>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ambiente>" + ff.getAmbiente() + "</ambiente>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tipoEmision>" + ff.getTipoEmision() + "</tipoEmision>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<razonSocial>" + ff.getRazonSocial() + "</razonSocial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<nombreComercial>" + ff.getNombreComercial() + "</nombreComercial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ruc>" + ff.getRuc() + "</ruc>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<claveAcceso>" + ff.getClave_acceso_sri_nc() + "</claveAcceso>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codDoc>" + ff.getCodDoc() + "</codDoc>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<estab>" + ff.getEstab() + "</estab>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<ptoEmi>" + ff.getPtoEmi() + "</ptoEmi>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<secuencial>" + ff.getSecuencial() + "</secuencial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<dirMatriz>" + ff.getDirMatriz() + "</dirMatriz>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoTributaria>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoNotaCredito>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<fechaEmision>" + ff.getFechaEmision() + "</fechaEmision>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<dirEstablecimiento>" + ff.getDirEstablecimiento() + "</dirEstablecimiento>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tipoIdentificacionComprador>" + ff.getTipoIdentificacionComprador()+"</tipoIdentificacionComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<razonSocialComprador>" + ff.getRazonSocialComprador() + "</razonSocialComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<identificacionComprador>" + ff.getIdentificacionComprador() + "</identificacionComprador>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<contribuyenteEspecial>" + ff.getContribuyenteEspecial() + "</contribuyenteEspecial>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<obligadoContabilidad>" + ff.getObligadoContabilidad() + "</obligadoContabilidad>");
		//sb.append("<rise>" + ff.getRise() + "</rise>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codDocModificado>" + ff.getCodDocModificado() + "</codDocModificado>");//REVISAR
		sb.append(System.getProperty("line.separator"));
		sb.append("<numDocModificado>" + ff.getNumDocModificado() + "</numDocModificado>");//REVISAR
		sb.append(System.getProperty("line.separator"));
		//sb.append("<fechaEmisionDocSustento>" + ff.getFechaEmisionDocSustento() + "</fechaEmisionDocSustento>");
		sb.append("<fechaEmisionDocSustento>" + ff.getFechaEmision() + "</fechaEmisionDocSustento>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<totalSinImpuestos>" + ff.getTotalSinImpuestos() + "</totalSinImpuestos>");
		sb.append(System.getProperty("line.separator"));
		
		/*sb.append("<compensaciones>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<compensacion>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigo>1</codigo>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tarifa>49.50</tarifa>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<valor>50.00</valor>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</compensacion>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<compensacion>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigo>1</codigo>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<tarifa>49.50</tarifa>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<valor>50.00</valor>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</compensacion>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</compensaciones>");*/
		
		//sb.append(System.getProperty("line.separator"));
		sb.append("<valorModificacion>" + ff.getImporteTotal() + "</valorModificacion>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<moneda>" + ff.getMoneda() + "</moneda>");
		
		sb.append(System.getProperty("line.separator"));
		sb.append("<totalConImpuestos>");
		
		sb.append(System.getProperty("line.separator"));
		/*sb.append("<totalImpuesto>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigo>"+ff.getTotalImpuestoCodigo()+"</codigo>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigoPorcentaje>"+ff.getTotalImpuestoCodigoPorcentaje()+"</codigoPorcentaje>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<baseImponible>"+ff.getTotalImpuestoBaseImponible()+"</baseImponible>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<valor>"+ff.getTotalImpuestoValor()+"</valor>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</totalImpuesto>");*/
		sb.append("<totalImpuesto>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigo>"+ff.getImpuestoCodigo()+"</codigo>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<codigoPorcentaje>"+ff.getImpuestoCodigoPorcentaje()+"</codigoPorcentaje>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<baseImponible>"+ff.getTotalImpuestoBaseImponible()+"</baseImponible>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<valor>"+ff.getTotalImpuestoValor()+"</valor>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</totalImpuesto>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</totalConImpuestos>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<motivo>" + ff.getMotivo() + "</motivo>");
		
		
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoNotaCredito>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<detalles>");
		sb.append(System.getProperty("line.separator"));

		
		//dinamico
				int totalDetalles=fd.size();
				double valor=0.00; 
				double baseImponibleIndividual;
				double finalValue ;

		System.out.println("TOTAL DETALLE NC:" + totalDetalles);
		for (int i = 0; i < totalDetalles; i++) {
			
			//valor=Double.parseDouble(fd.get(i).getPrecioTotalSinImpuesto())*(Double.parseDouble(ff.getImpuestoTarifa())/100);
			//finalValue=Math.round( valor * 100.00 ) / 100.00;
			
			sb.append("<detalle>");
			sb.append(System.getProperty("line.separator"));
			sb.append("<codigoInterno>" + fd.get(i).getCodigoInterno() + "</codigoInterno>");
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
			sb.append("<valor>"+fd.get(i).getPrecioUnitario()+"</valor>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</impuesto>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</impuestos>");
			sb.append(System.getProperty("line.separator"));
			sb.append("</detalle>");
			sb.append(System.getProperty("line.separator"));
		}

		sb.append("</detalles>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<infoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Direccion\">" + ff.getCampoAdicionalDireccion() + "</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Telefono\">" + ff.getCampoAdicionalTelefono() + "</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("<campoAdicional nombre=\"Email\">" + ff.getCampoAdicionalEmail() + "</campoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</infoAdicional>");
		sb.append(System.getProperty("line.separator"));
		sb.append("</notaCredito>");

		return sb.toString();

	}

}

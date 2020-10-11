package com.extreme.media.entity;

public class FacturaDinamico {
	
	    String idCabeceraPadre;
	    public String getIdCabeceraPadre() {
			return idCabeceraPadre;
		}
		public void setIdCabeceraPadre(String idCabeceraPadre) {
			this.idCabeceraPadre = idCabeceraPadre;
		}
		public String getCodigoPrincipal() {
			return codigoPrincipal;
		}
		public void setCodigoPrincipal(String codigoPrincipal) {
			this.codigoPrincipal = codigoPrincipal;
		}
		public String getCodigoAuxiliar() {
			return codigoAuxiliar;
		}
		public void setCodigoAuxiliar(String codigoAuxiliar) {
			this.codigoAuxiliar = codigoAuxiliar;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getCantidad() {
			return cantidad;
		}
		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}
		public String getPrecioUnitario() {
			return precioUnitario;
		}
		public void setPrecioUnitario(String precioUnitario) {
			this.precioUnitario = precioUnitario;
		}
		public String getDescuento() {
			return descuento;
		}
		public void setDescuento(String descuento) {
			this.descuento = descuento;
		}
		public String getPrecioTotalSinImpuesto() {
			return precioTotalSinImpuesto;
		}
		public void setPrecioTotalSinImpuesto(String precioTotalSinImpuesto) {
			this.precioTotalSinImpuesto = precioTotalSinImpuesto;
		}
		//detalles
		//detalle
		String codigoPrincipal;
		String codigoAuxiliar;
		String descripcion;
		String cantidad;
		String precioUnitario;
		String descuento;
		String precioTotalSinImpuesto;

}

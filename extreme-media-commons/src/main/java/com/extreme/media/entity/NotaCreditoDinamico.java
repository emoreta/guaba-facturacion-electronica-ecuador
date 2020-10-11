package com.extreme.media.entity;

public class NotaCreditoDinamico {
	
	String codigoInterno;
	String codigoAdicional;
	String descripcion;
	String cantidad;
	String precioUnitario;
	String descuento;
	String precioTotalSinImpuesto;
	//detalles adicionales
	
	String nombre;
	String valor;
	
	public String getCodigoInterno() {
		return codigoInterno;
	}
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	public String getCodigoAdicional() {
		return codigoAdicional;
	}
	public void setCodigoAdicional(String codigoAdicional) {
		this.codigoAdicional = codigoAdicional;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}
	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}
	public String getTarifa() {
		return tarifa;
	}
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	public String getBaseImponible() {
		return baseImponible;
	}
	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}
	public String getValorImpuesto() {
		return valorImpuesto;
	}
	public void setValorImpuesto(String valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}
	//impuesto
	String codigo;
	String codigoPorcentaje;
	String tarifa;
	String baseImponible;
	String valorImpuesto;
	
	

}

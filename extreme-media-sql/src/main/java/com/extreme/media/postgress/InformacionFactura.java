package com.extreme.media.postgress;

import java.sql.ResultSet;

public class InformacionFactura {
	public ResultSet getDinamico() {
		return dinamico;
	}
	public void setDinamico(ResultSet dinamico) {
		this.dinamico = dinamico;
	}
	public ResultSet getEstatico() {
		return estatico;
	}
	public void setEstatico(ResultSet estatico) {
		this.estatico = estatico;
	}
	ResultSet dinamico;
	ResultSet estatico;

}

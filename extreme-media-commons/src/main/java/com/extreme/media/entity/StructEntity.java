package com.extreme.media.entity;

import java.util.List;

public class StructEntity {
	FacturaFijo ff;
	FacturaDinamico fd;
	List<FacturaDinamico>  list;
	public List<FacturaDinamico> getList() {
		return list;
	}
	public void setList(List<FacturaDinamico> list) {
		this.list = list;
	}
	public FacturaFijo getFf() {
		return ff;
	}
	public void setFf(FacturaFijo ff) {
		this.ff = ff;
	}
	public FacturaDinamico getFd() {
		return fd;
	}
	public void setFd(FacturaDinamico fd) {
		this.fd = fd;
	}
	
	

}

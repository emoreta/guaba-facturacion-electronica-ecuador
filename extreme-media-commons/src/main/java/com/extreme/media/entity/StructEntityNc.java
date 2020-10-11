package com.extreme.media.entity;

import java.util.List;

public class StructEntityNc {
	NotaCreditoFijo ff;
	NotaCreditoDinamico fd;
	List<NotaCreditoDinamico>  list;
	public NotaCreditoFijo getFf() {
		return ff;
	}
	public void setFf(NotaCreditoFijo ff) {
		this.ff = ff;
	}
	public NotaCreditoDinamico getFd() {
		return fd;
	}
	public void setFd(NotaCreditoDinamico fd) {
		this.fd = fd;
	}
	public List<NotaCreditoDinamico> getList() {
		return list;
	}
	public void setList(List<NotaCreditoDinamico> list) {
		this.list = list;
	}

}

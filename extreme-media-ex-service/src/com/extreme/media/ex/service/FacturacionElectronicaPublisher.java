package com.extreme.media.ex.service;

import javax.xml.ws.Endpoint;

public class FacturacionElectronicaPublisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:8080/ws/hello", new FacturacionElectronicaImpl());

	}

}

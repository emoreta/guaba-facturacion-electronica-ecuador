package com.extreme.media.ex.service;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface FacturacionElectronicaSoap {

	@WebMethod String getHelloWorldAsString(String name);
    

}

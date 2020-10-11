package com.extreme.media.test;

import java.io.IOException;

import com.extreme.media.send.SendDocument;

public class Test {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		SendDocument sd=new SendDocument();
		//sd.enviarComprantesWebServices("C:\\XMLPRUEBA\\2606201801171892136200110010010000000081234567812_Sign.xml", 1);
		
		sd.consultaComprobantesWebServices("2510201801179204086800120010010000667030006670311");
											
		                                    
		

	}

}

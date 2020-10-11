package com.extreme.media.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.extreme.media.pdf.Factura;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Factura f=new Factura();
		try {
			f.GenerarPdf("1", "", "", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD", "DD.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

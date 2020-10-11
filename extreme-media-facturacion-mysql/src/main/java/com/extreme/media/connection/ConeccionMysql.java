package com.extreme.media.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

import com.extreme.media.commons.Constants;
import com.extreme.media.commons.ReadXml;
import com.extreme.media.decrypt.Decrypt;

public class ConeccionMysql {
	static final String TuIoPlMnFXxX = "Facturacion2018@Integracion";
	public  String driver="com.mysql.jdbc.Driver";
	Connection connection = null;
	public  ConeccionMysql() throws Exception
	{
		Decrypt cd=new Decrypt();
		Map<String, String> pathConfig = new HashMap<String, String>();
		Document ed;
		ReadXml rx = new ReadXml();
		Constants constants = new Constants();
		ed = rx.getDoc(constants.ROOT + constants.PATHINFRAESTRUCTURE + constants.APPCONFIG);
        pathConfig=rx.readXmlMapAppConfig(ed);
		String cadenaBd = pathConfig.get("bdMysql");
		String decryptCadenaBd=Decrypt.Desencriptar(cadenaBd, TuIoPlMnFXxX);
		
		String string = decryptCadenaBd;
		String[] parts = string.split(";");
		String part0 = parts[0]; 
		String part1 = parts[1]; 
		String part2 = parts[2];
		
			Class.forName(driver).newInstance();
			connection=DriverManager.getConnection(part0,part1,part2);
			System.out.println("CONEXION:"+connection);
		
	
	}
	public Connection getConnection(){
	      return connection;
	   }
	 public void UpdateTableDocuments(String fecha,String ruc_cedula,String nombres,String clave_acceso,String tipo_documento,String pdf,String xml,String estado,String fecha_subida,String establecimiento,String valor) throws SQLException{
		 
		 String query = "SELECT COUNT(clave_acceso)AS clave_acceso  FROM documents WHERE clave_acceso='"+clave_acceso+"'";
		 Statement st = getConnection().createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      int count=0;
	      Boolean val=false;
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	   count = rs.getInt("clave_acceso");
	      }
		 
	      if(count==0)
			 {
		 Statement estatuto = getConnection().createStatement();
		 estatuto.executeUpdate("insert into documents (fecha,ruc_cedula,nombres,clave_acceso,tipo_documento,pdf,xml,estado,fecha_subida,establecimiento,valor)values('"+fecha+"','"+ruc_cedula+"','"+nombres+"','"+clave_acceso+"','"+tipo_documento+"','"+pdf+"','"+xml+"',1,'"+fecha_subida+"','"+establecimiento+"','"+valor+"')");
	        
	            System.out.println("REGISTRO GUARDADO DOCUMENTS");
	            estatuto.close();
			 }
	      else
	      {
	    	  System.out.println("REGISTRO YA INGRESADO");
	      }
	            //desconectar();
	 }
	 
	 public Boolean InsertUserPortal(String primerNombre,String SegundoNombre,String email,String userName,String contrasena) throws SQLException
	 {
		 String query = "SELECT COUNT(user_name)AS countUser  FROM membership WHERE user_name='"+userName+"'";
		 Statement st = getConnection().createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      int count=0;
	      Boolean val=false;
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	   count = rs.getInt("countUser");
	      }
	      st.close();
		 if(count==0)
		 {
		 Statement estatuto = getConnection().createStatement();
		 estatuto.executeUpdate("insert into membership (first_name,last_name,email_addres,user_name,pass_word)values('"+primerNombre+"','"+SegundoNombre+"','"+email+"','"+userName+"','"+contrasena+"')");
	        
	            System.out.println("USUARIO INSERTADO ");
	            estatuto.close();
	            
	            val=true;
		 }
		 else
		 {
			 
			    System.out.println("USUARIO YA EXISTE EN BD");
		 }
		 
		 return val;
	 }

}

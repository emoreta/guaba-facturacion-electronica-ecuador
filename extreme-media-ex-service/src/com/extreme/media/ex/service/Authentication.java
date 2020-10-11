/*
 * Copyright (c) 2017 Extreme Media SA, All Rights Reserved.
 *
 * This code is confidential to Extreme Media and shall not be disclosed outside Extreme Media
 * without the prior written permission of the Technology Center.
 *
 * In the event that such disclosure is permitted the code shall not be copied
 * or distributed other than on a need-to-know basis and any recipients may be
 * required to sign a confidentiality undertaking in favor of Extreme Media SA.
 *
 * Extreme Media Enterprise Ecuador SA
 *
 */
package com.extreme.media.ex.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.extreme.media.ex.util.AuthenticateAttribute;
import com.extreme.media.sql.connection.Test;
import com.extreme.media.ex.util.Perfil;

@Path("/service")
public class Authentication {
	@GET
	@Path("/hello")
	@Produces
	public Response hello() {

		return Response.status(200).entity("hello").build();
	}

	@Path("/public/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public Response Authenticate(String loginRequest, @Context HttpServletRequest httpRequest) {
		boolean stateAuthenticate = false;

		//JSONArray jsonArray = new JSONArray(loginRequest);
		JSONObject jsnobject = new JSONObject(loginRequest.toString());
		System.out.println("login: " + jsnobject.getString("login"));
		//JSONArray jsonArray = jsnobject.getJSONArray("login");
		//for (int i = 0; i < jsonArray.length(); i++) {
		//	JSONObject explrObject = jsonArray.getJSONObject(i);
		//}
		
		//System.out.println("Json object :: "+jsonArray);

		// JSONObject jsonObj = new JSONObject(loginRequest);
		// sysout
		// String statistics = jsonObj.getString("login");
		System.out.println(loginRequest);

		// System.out.println(httpRequest.authenticate(arg0));
		AuthenticateAttribute aa = new AuthenticateAttribute();
		// com.extreme.media.sql.connection.Connection con = new
		// com.extreme.media.sql.connection.Connection();
		// stateAuthenticate = con.authenticate(login, password);
		Test test = new Test();
		stateAuthenticate = test.authenticate(jsnobject.getString("login"),jsnobject.getString("password"));

		if (stateAuthenticate) {
			aa.setMessages("LOGIN SUCCESS");
			aa.setSuccess(true);
			aa.setNextStep("home");
			aa.setTokenId("1234567890");
			aa.setTheme("extrememedias");
			aa.setNumberOfTries("");
			aa.setCulture("es");
			aa.setLanguage("es");
		} else {
			aa.setMessages("ERROR CREDENTIAL");
			aa.setSuccess(false);
			aa.setNextStep("login");
			aa.setTokenId("");
			aa.setTheme("");
			aa.setNumberOfTries("");
			aa.setCulture("");
			aa.setLanguage("");
		}

		return Response.status(200).entity(aa).build();
	}
	
	@Path("/private/newPerfil")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response newPerfil(String jsonPerfil, @Context HttpServletRequest httpRequest) {
		System.out.println("json"+jsonPerfil);
		Perfil p=new Perfil();
		JSONObject jsnobject = new JSONObject(jsonPerfil.toString());
		boolean stateQuery=false;
		p.setNombre(jsnobject.getString("NOMBRE_PERFIL"));
		p.setDescripcion(jsnobject.getString("DESCRIPCION_PERFIL"));
		p.setEstado(jsnobject.getInt("ESTADO_PERFIL"));
		Test newPerfilTest = new Test();
		//
		
		stateQuery=newPerfilTest.newPerfil(p);
		
		return Response.status(200).entity(stateQuery).build();
		
	}
	@Path("/private/updatePerfil")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response updatePerfil(String jsonPerfil, @Context HttpServletRequest httpRequest) {
		System.out.println("json"+jsonPerfil);
		Perfil p=new Perfil();
		JSONObject jsnobject = new JSONObject(jsonPerfil.toString());
		boolean stateQuery=false;
		p.setId(jsnobject.getInt("ID_PERFIL"));
		p.setNombre(jsnobject.getString("NOMBRE_PERFIL"));
		p.setDescripcion(jsnobject.getString("DESCRIPCION_PERFIL"));
		p.setEstado(jsnobject.getInt("ESTADO_PERFIL"));
		Test updatePerfilTest = new Test();
		//
		
		stateQuery=updatePerfilTest.updatePerfilSql(p);
		
		return Response.status(200).entity(stateQuery).build();
		
	}
	@Path("/private/deletePerfil")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response deletePerfil(String jsonPerfil, @Context HttpServletRequest httpRequest) {
		System.out.println("json"+jsonPerfil);
		Perfil p=new Perfil();
		JSONObject jsnobject = new JSONObject(jsonPerfil.toString());
		boolean stateQuery=false;
		p.setId(jsnobject.getInt("ID_PERFIL"));
		Test deletePerfilTest = new Test();
		stateQuery=deletePerfilTest.deletePerfilSql(p);
		
		return Response.status(200).entity(stateQuery).build();
		
	}
	
	
	
	@Path("/private/perfil")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String PerfilList(@Context HttpServletRequest httpRequest) {
		String listPerfil="";
		ResultSet r;
		Test test = new Test();
		listPerfil=test.PerfilResulset();
		
	
		//getFormattedResult(r);
		
		return listPerfil;

		//return getFormattedResult(r);
	}
	public static List<JSONObject> getFormattedResult(ResultSet rs) {
	    List<JSONObject> resList = new ArrayList<JSONObject>();
	    try {
	        // get column names
	        ResultSetMetaData rsMeta = rs.getMetaData();
	        int columnCnt = rsMeta.getColumnCount();
	        List<String> columnNames = new ArrayList<String>();
	        for(int i=1;i<=columnCnt;i++) {
	            columnNames.add(rsMeta.getColumnName(i).toUpperCase());
	        }

	        while(rs.next()) { // convert each object to an human readable JSON object
	            JSONObject obj = new JSONObject();
	            for(int i=1;i<=columnCnt;i++) {
	                String key = columnNames.get(i - 1);
	                String value = rs.getString(i);
	                obj.put(key, value);
	            }
	            resList.add(obj);
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return resList;
	}

}

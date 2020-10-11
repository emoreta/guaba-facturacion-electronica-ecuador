package com.extreme.media.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.extreme.media.ex.util.Perfil;
import com.extreme.media.sql.connection.Test;

@Path("/fac")
public class FacturacionElectronica {
	@GET
	@Path("/hello")
	@Produces
	public Response hello() {

		return Response.status(200).entity("hello fac").build();
	}
	@Path("/private/document")
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

}

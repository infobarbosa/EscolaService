package com.barbosa.service;

import com.barbosa.dao.CursoDao;
import com.barbosa.model.Cursos;

import javax.ws.rs.Path;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/cursos")
public class CursoService {
	
	@GET
	@Produces("application/xml")
	public Cursos listaCursos(@DefaultValue("0") @QueryParam("i") int i, @DefaultValue("10") @QueryParam("o") int o ){
		CursoDao dao = new CursoDao();
		Cursos cursos = dao.listaCursos(i, o);
		
		return cursos;
	}
}

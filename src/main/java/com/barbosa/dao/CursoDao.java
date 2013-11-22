package com.barbosa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.barbosa.model.Curso;
import com.barbosa.model.Cursos;

public class CursoDao {
	private static Logger logger = Logger.getLogger(CursoDao.class);
	
	public Cursos listaCursos(int i, int o){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cursos cursos = new Cursos();
		
		try{
			String sql = "select id, nome from curso order by id limit ?, ?";
			
			stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setInt(1, i);
			stmt.setInt(2, o);
			rs = stmt.executeQuery();
			
			Curso curso = null;
			while(rs.next()){
				curso = new Curso();
				curso.setId( rs.getInt("ID") );
				curso.setDescricao( rs.getString("NOME") );
				
				cursos.addCurso(curso);
			}
			
		}catch(Exception e){
			logger.error("Problemas obtendo lista de cursos", e);
		}finally{
			try{
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
				
				stmt = null;
				rs = null;
			}catch(Exception e){
				logger.error("Problemas liberando recursos", e);
			}
		}
		
		return cursos;
	}
}

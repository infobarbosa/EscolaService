package com.barbosa.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.barbosa.model.Curso;
import com.barbosa.model.Interessado;

public class InteressadoDao {
	private static final Logger logger = Logger.getLogger(InteressadoDao.class);
	
	public void insereInteressado(Interessado interessado){
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		try{
			if(interessado == null)
				throw new Exception("Interessado inv‡lido!");
			
			String sql1 = "insert into interessado(nome, telefone, email) values(?, ?, ?)";
			
			stmt1 = Conexao.getConnection().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			
			stmt1.setString(1, interessado.getNome());
			stmt1.setString(2, interessado.getTelefone());
			stmt1.setString(3, interessado.getEmail());
			
			stmt1.executeUpdate();

		    ResultSet rs = stmt1.getGeneratedKeys();

		    if ( rs.next() ) {
		        interessado.setId( rs.getInt(1) );
		    } else {
		    	throw new Exception("Nenhum registro inserido.");
		    }

		    rs.close();
			
		    String sql2 = "insert into interessado_curso(id_interessado, id_curso) values(?, ?)";
		    
		    stmt2 = Conexao.getConnection().prepareStatement(sql2);
		    
		    for(Curso curso: interessado.getInteresses()){
		    	stmt2.setInt(1, interessado.getId() );
		    	stmt2.setInt(2, curso.getId() );
		    	stmt2.executeUpdate();
		    }
		    
			Conexao.getConnection().commit();
			
		}catch(Exception e){
			try{
				Conexao.getConnection().rollback();
				logger.error("Problemas inserindo interessado.", e);
			}catch(Exception eRollback){
				logger.error("Problemas com rollback", eRollback);
			}
		}finally{
			try{
				if(stmt1 != null) stmt1.close();
			}catch(Exception eSql){
				logger.error("Erro liberando recursos", eSql);
			}
		}
	}
	
	public Interessado obtemInteressado(Integer id){
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Interessado interessado = null;
		
		try{
			if(id == null){
				throw new Exception("ID nulo nao vale");
			}
			
			String sql = "select * from interessado where id = ?";
			stmt1 = Conexao.getConnection().prepareStatement(sql);
			stmt1.setInt(1, id);
			
			rs1 = stmt1.executeQuery();
			
			if(rs1.next()){
				interessado = new Interessado();
				interessado.setId( rs1.getInt("ID") );
				interessado.setNome( rs1.getString("NOME") );
				interessado.setTelefone( rs1.getString("TELEFONE") );
				interessado.setEmail( rs1.getString("EMAIL") );
			}
			
			String sql2 = "select c.id, c.nome from interessado_curso ic inner join curso c on c.id = ic.id_curso where id_interessado = ?";
			
			stmt2 = Conexao.getConnection().prepareStatement(sql2);
			stmt2.setInt(1, id );
			rs2 = stmt2.executeQuery();
			
			Curso c1;
			while(rs2.next()){
				c1 = new Curso( rs2.getInt("id"), rs2.getString("nome") );
				interessado.setInteresse(c1);
			}
			
		}catch(Exception e){
			logger.error("Erro obtendo interessado: " + id, e);
		}finally{
			try{
				if(rs1 != null) rs1.close();
				if(stmt1 != null) rs1.close();
				
				rs1 = null;
				stmt1 = null;
						
			}catch(Exception eFinally){
				logger.error("Erro liberando recursors.", eFinally);
			}
		}
		
		return interessado;
	}
	
	public void atualizaInteressado(Interessado interessado){
		PreparedStatement stmt = null;
		
		try{
			String sql = "update interessado set nome=?, telefone=?, email=? where id=?";
			
			stmt = Conexao.getConnection().prepareStatement(sql);
			
			stmt.setString(1, interessado.getNome());
			stmt.setString(2, interessado.getTelefone());
			stmt.setString(3, interessado.getEmail());
			stmt.setInt(5, interessado.getId());
			
			stmt.executeUpdate();
			
			Conexao.getConnection().commit();
		}catch(Exception e){
			try{
				Conexao.getConnection().rollback();
				logger.error("Problemas atualizando interessado.", e);
			}catch(Exception eRollback){
				logger.error("Problemas com rollback", eRollback);
			}
		}finally{
			try{
				if(stmt != null) stmt.close();
			}catch(Exception eSql){
				logger.error("Erro liberando recursos", eSql);
			}
		}
	}
	
	public void excluiInteressado(Integer id){
		PreparedStatement stmt = null;
		
		try{
			String sql = "delete from interessado where id=?";
			
			stmt = Conexao.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
			
			Conexao.getConnection().commit();
		}catch(Exception e){
			try{
				Conexao.getConnection().rollback();
				logger.error("Problemas excluindo interessado.", e);
			}catch(Exception eRollback){
				logger.error("Problemas com rollback", eRollback);
			}
		}finally{
			try{
				if(stmt != null) stmt.close();
			}catch(Exception eSql){
				logger.error("Erro liberando recursos", eSql);
			}
		}
	}
}

package com.barbosa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Conexao {
	
	private static Connection conn;
		
	static{
		try{
			if(conn == null || conn.isClosed()){
				openConnection();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try{
			if(conn == null || conn.isClosed()){
				System.out.println("abrindo conexao...");
				openConnection();
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return conn;
	}
	
	public static void openConnection(){
		String dbUrl = "jdbc:mysql://localhost/test";
		String dbClass = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "";
		
		try {
			
			Class.forName(dbClass);
			conn = DriverManager.getConnection(dbUrl, username, password);
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

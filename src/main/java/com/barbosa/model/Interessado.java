package com.barbosa.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="interessado")
public class Interessado {
	@XmlElement
	private Integer id;
	@XmlElement
	private String nome;
	@XmlElement
	private String telefone;
	@XmlElement
	private String email;
	@XmlElement
	private ArrayList<Curso> interesses;
	
	public Interessado(){}
	
	public Interessado(Integer id, String nome, String telefone, String email, ArrayList<Curso> interesses){
		this.id = id;
		this.telefone = telefone;
		this.email = email;
		this.interesses = interesses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Curso> getInteresses() {
		return interesses;
	}

	public void setInteresses(ArrayList<Curso> interesses) {
		this.interesses = interesses;
	}
	
	public void setInteresse(Curso curso){
		this.interesses.add(curso);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("id: ").append(this.id)
		.append(", nome: ").append(this.nome)
		.append("telefone: ").append(this.telefone)
		.append(", email: ").append(this.email)
		.append(", cursos: ");
		
		for(Curso curso: interesses)
			sb.append(" ").append(curso.getDescricao());
		
		return sb.toString();
		
	}
}

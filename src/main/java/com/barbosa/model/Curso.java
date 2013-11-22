package com.barbosa.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="curso")
public class Curso {
	
	@XmlAttribute
	private Integer id;
	
	@XmlElement
	private String descricao;
	
	public Curso(){}
	
	public Curso(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString(){
		return "id: " + this.id + "; descricao: " + this.descricao;
	}
}

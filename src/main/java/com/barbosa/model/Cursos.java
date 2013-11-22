package com.barbosa.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="cursos")
public class Cursos {
	
	@XmlElement(name="curso")
	private List<Curso> cursos = new ArrayList<Curso>();
	
	public Cursos(){}
	
	public Cursos(List<Curso> cursos){
		this.cursos = cursos;
	}
	
	public List<Curso> getCursos(){
		return this.cursos;
	}
	
	public void setCursos(List<Curso> cursos){
		this.cursos = cursos;
	}
	
	public void addCurso(Curso curso){
		this.cursos.add( curso );
	}
}

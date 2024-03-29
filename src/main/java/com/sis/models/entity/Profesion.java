package com.sis.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Profesion {

	@Id
	@SequenceGenerator(name="seq", initialValue=500, allocationSize=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int idProfesion;
	private String nombre;
	
	public int getIdProfesion() {
		return idProfesion;
	}

	public void setIdProfesion(int idProfesion) {
		this.idProfesion = idProfesion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

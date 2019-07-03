package com.sis.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Vereda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "exception_seq_generator")
	private int idVereda;
	@NotNull
	private String nombre;

	public int getIdVereda() {
		return idVereda;
	}

	public void setIdVereda(int idVereda) {
		this.idVereda = idVereda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
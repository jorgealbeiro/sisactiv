package com.sis.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;


@Entity

public class Vereda {

	@Id
	@SequenceGenerator(name="seq", initialValue=300, allocationSize=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int idVereda;
	@NotNull()
	@Column(unique= true)
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
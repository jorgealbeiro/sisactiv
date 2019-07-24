package com.sis.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class CategoriaActividad {

	@Id
	@SequenceGenerator(name="seq", initialValue=700, allocationSize=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int idCategoria;
	@NotNull()
	@Column(unique= true)
	private String nombre;	

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

package com.sis.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TipoNarracion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "exception_seq_generator")
	private long idTipoNarracion;
	@NotNull
	private String nombre;

	public long getIdTipoNarracion() {
		return idTipoNarracion;
	}

	public void setIdTipoNarracion(long idTipoNarracion) {
		this.idTipoNarracion = idTipoNarracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
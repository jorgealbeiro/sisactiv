package com.sis.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@Table(name = "actividades")
public class Actividad {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long id;
	@ManyToOne
	@JoinColumn(name="categoria_id", referencedColumnName = "idCategoria")
	private CategoriaActividad categoriaActividad;	
	@ManyToOne
	@JoinColumn(name="persona_cedula", referencedColumnName = "cedulaPersona")
	private Persona personaEncargada;
	private String descripcion;
	private double presupuesto;
	private String lugar;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone="GMT-5")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Enumerated(EnumType.STRING)
	private EstadoActividad estadoActividad;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public CategoriaActividad getCategoriaActividad() {
		return categoriaActividad;
	}

	public void setCategoriaActividad(CategoriaActividad categoriaActividad) {
		this.categoriaActividad = categoriaActividad;
	}

	
	public Persona getCedula() {
		return personaEncargada;
	}

	public void setCedula(Persona cedula) {
		this.personaEncargada = cedula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public EstadoActividad getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(EstadoActividad estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
	
	
}

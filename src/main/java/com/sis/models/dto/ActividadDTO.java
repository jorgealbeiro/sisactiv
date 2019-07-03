package com.sis.models.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class ActividadDTO{

	private long id;
	private long cedulaPersona;
	private String descripcion;
	private double presupuesto;
	private String lugar;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone="GMT-5")
	private Date fecha;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCedulaPersona() {
		return cedulaPersona;
	}
	public void setCedulaPersona(long cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
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

	
}

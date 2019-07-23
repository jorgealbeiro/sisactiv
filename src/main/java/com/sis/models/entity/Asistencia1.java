package com.sis.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Asistencia1 {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long id;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_actividad", referencedColumnName = "id")
	private Actividad idActividad;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "cedula_adulto", referencedColumnName = "cedula")
	private AdultoMayor cedulaAdulto;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone = "GMT-5")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaInicio;

	public Actividad getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Actividad idActividad) {
		this.idActividad = idActividad;
	}

	public AdultoMayor getCedulaAdulto() {
		return cedulaAdulto;
	}

	public void setCedulaAdulto(AdultoMayor cedulaAdulto) {
		this.cedulaAdulto = cedulaAdulto;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

}
package com.sis.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Narracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "exception_seq_generator")
	private long idNarracion;
	@ManyToOne
	@JoinColumn(name="cedula_adulto", referencedColumnName = "cedula")
	private AdultoMayor AdultoMayor;
	@ManyToOne
	@JoinColumn(name="tipo_narracion", referencedColumnName = "idTipoNarracion")
	private TipoNarracion tipoNarracion;
	private String nombre;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone="GMT-5")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private String descripcion;
	private String rutaAudio;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;

	public long getIdNarracion() {
		return idNarracion;
	}

	public void setIdNarracion(long idNarracion) {
		this.idNarracion = idNarracion;
	}

	public AdultoMayor getCedulaAdulto() {
		return AdultoMayor;
	}

	public void setCedulaAdulto(AdultoMayor cedulaAdulto) {
		this.AdultoMayor = cedulaAdulto;
	}

	public TipoNarracion getTipoNarracion() {
		return tipoNarracion;
	}

	public void setTipoNarracion(TipoNarracion tipoNarracion) {
		this.tipoNarracion = tipoNarracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRutaAudio() {
		return rutaAudio;
	}

	public void setRutaAudio(String rutaAudio) {
		this.rutaAudio = rutaAudio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}

package com.sis.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//@Table(name = "Adultos")
public class AdultoMayor {

	@Id
	private long cedula;
	private String nombre;
	private String apellido;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss", timezone="GMT-5")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	private float sisben;
	private char genero;
	@ManyToOne
	@JoinColumn(name="vereda_id", referencedColumnName = "idVereda")
	private Vereda vereda;
	private long celular;
	
	@Column(unique= true)
	private String manilla;
	
	private String rutaImagen;
	
	@Enumerated(EnumType.STRING)
	private EstadoAfiliacion estadoAfiliacion;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Narracion> adultoNarracion;

	public long getCedulaAdulto() {
		return cedula;
	}

	public void setCedulaAdulto(long cedulaAdulto) {
		this.cedula = cedulaAdulto;
	}
	
	

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public Vereda getVereda() {
		return vereda;
	}

	public void setVereda(Vereda idVereda) {
		this.vereda = idVereda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public float getSisben() {
		return sisben;
	}

	public void setSisben(float sisben) {
		this.sisben = sisben;
	}

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public EstadoAfiliacion getEstadoAfiliacion() {
		return estadoAfiliacion;
	}

	public void setEstadoAfiliacion(EstadoAfiliacion estadoAfiliacion) {
		this.estadoAfiliacion = estadoAfiliacion;
	}

	public String getManilla() {
		return manilla;
	}

	public void setManilla(String manilla) {
		this.manilla = manilla;
	}
		
	
}

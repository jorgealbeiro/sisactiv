package com.sis.models.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

@Entity
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private long cedulaPersona;
	private String nombre;
	private String apellido;
	private long celular;	
	@NotNull()
	@Column(unique= true)
	private String correo;
	private String contrasenia;
	@ManyToOne
	@JoinColumn(name="profesion_id", referencedColumnName = "idProfesion")
	private Profesion profesion;
	
	@Enumerated(EnumType.STRING)
	private RolPersona rolPersona;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Actividad> listaActividades;
	
	public long getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(long cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
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

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

	public Profesion getProfesion() {
		return profesion;
	}

	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public RolPersona getRolPersona() {
		return rolPersona;
	}

	public void setRolPersona(RolPersona rolPersona) {
		this.rolPersona = rolPersona;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	
}

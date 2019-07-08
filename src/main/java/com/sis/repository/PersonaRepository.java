package com.sis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sis.models.entity.Estado;
import com.sis.models.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long>{

	@Query(value = "SELECT * FROM persona a WHERE a.correo = (:correo) AND a.contrasenia = (:contrasenia)", nativeQuery = true)
	Collection<Persona> autenticar(String correo, String contrasenia);

	Persona findByCorreo(String correo);
	Persona findByContrasenia(String contrasenia);
	

	@Query("SELECT a FROM Persona a WHERE a.estado =  com.sis.models.entity.Estado.ACTIVO")
	List<Persona> getByEstadoActivos();

	@Query("SELECT a FROM Persona a WHERE a.estado =  com.sis.models.entity.Estado.ELIMINADO")
	List<Persona> getByEstadoEliminado();
}


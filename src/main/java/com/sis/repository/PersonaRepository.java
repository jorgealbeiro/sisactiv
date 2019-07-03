package com.sis.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{

	@Query(value = "SELECT * FROM persona a WHERE a.correo = (:correo) AND a.contrasenia = (:contrasenia)", nativeQuery = true)
	Collection<Persona> autenticar(String correo, String contrasenia);
}

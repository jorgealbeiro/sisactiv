package com.sis.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Persona;
import com.sis.models.entity.Profesion;

public interface ProfesionRepository extends CrudRepository<Profesion, Integer> {

	
	@Query(value = "SELECT pe.nombre from "
			+ "profesion pr join persona pe on pr.id_profesion=pe.profesion_id "			
			+ "WHERE pr.id_profesion = (:id)", nativeQuery = true)
	List<String> obtenerPersonaslis(int id);

	@Query(value = "SELECT pe.nombre, pe.apellido from "
			+ "profesion pr join persona pe on pr.id_profesion=pe.profesion_id "			
			+ "WHERE pr.id_profesion = (:id)", nativeQuery = true)
	Collection<Object> obtenerPersonas(int id);	

}

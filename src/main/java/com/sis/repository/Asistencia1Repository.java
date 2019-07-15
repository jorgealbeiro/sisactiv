package com.sis.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sis.models.entity.Asistencia1;

@Repository
public interface Asistencia1Repository extends CrudRepository<Asistencia1, Long> {

	@Query("SELECT a FROM Asistencia1 a WHERE a.idActividad.id = (:id)")
	List<Asistencia1> obtenerAsistenciaActividad(long id);
	

	
}

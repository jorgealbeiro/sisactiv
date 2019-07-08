package com.sis.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Actividad;


public interface ActividadRepository extends CrudRepository<Actividad, Long>{

	@Query(value = "SELECT a FROM actividad a WHERE a.fecha = (:fecha)", nativeQuery = true)
	Collection<Actividad> filtrarFecha(Date fecha);	
	

}

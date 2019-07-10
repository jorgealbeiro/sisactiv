package com.sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Narracion;

public interface NarracionRepository extends CrudRepository<Narracion, Long>{

	@Query("SELECT a FROM Narracion a WHERE a.estado =  com.sis.models.entity.Estado.ACTIVO")
	List<Narracion> getByEstadoActivos();
	
	@Query("SELECT a FROM Narracion a WHERE a.estado =  com.sis.models.entity.Estado.ELIMINADO")
	List<Narracion> getByEstadoEliminado();

}

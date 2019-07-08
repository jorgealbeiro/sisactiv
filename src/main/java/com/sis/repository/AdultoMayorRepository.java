package com.sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sis.models.entity.AdultoMayor;
import com.sis.models.entity.EstadoAfiliacion;

@Repository
public interface AdultoMayorRepository extends CrudRepository<AdultoMayor, Long>{

		
	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.ACTIVO")
	List<AdultoMayor> getByEstadoAfiliacion();

	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.INACTIVO")
	List<AdultoMayor> getByEstadoAfiliacionInactivo();

	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.DESAFILIADO")
	List<AdultoMayor> getByEstadoAfiliacionDesafiliado();
	
	

}
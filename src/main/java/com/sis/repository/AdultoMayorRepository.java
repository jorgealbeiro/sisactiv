package com.sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sis.models.entity.AdultoMayor;

@Repository
public interface AdultoMayorRepository extends CrudRepository<AdultoMayor, Long> {

	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.ACTIVO")
	List<AdultoMayor> getByEstadoAfiliacion();

	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.INACTIVO")
	List<AdultoMayor> getByEstadoAfiliacionInactivo();

	@Query("SELECT a FROM AdultoMayor a WHERE a.estadoAfiliacion =  com.sis.models.entity.EstadoAfiliacion.DESAFILIADO")
	List<AdultoMayor> getByEstadoAfiliacionDesafiliado();

	@Query("SELECT a FROM AdultoMayor a WHERE a.sisben =?1")
	List<AdultoMayor> getAdultoSisben(float id);

	@Query("SELECT a FROM AdultoMayor a WHERE a.vereda.idVereda =?1")
	List<AdultoMayor> getAdultoVereda(int ve);

	@Query("SELECT a FROM AdultoMayor a WHERE a.manilla =(:manilla)")
	AdultoMayor obtenerAdultoManilla(String manilla);

	@Query(value = "select * from adulto_mayor a WHERE a.cedula = ?1 ", nativeQuery = true)	
	List<AdultoMayor> obtenerAdultocc(long cedula);

}

package com.sis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sis.models.entity.AdultoMayor;
import com.sis.models.entity.Asistencia1;

@Repository
public interface Asistencia1Repository extends CrudRepository<Asistencia1, Long> {

	@Query("SELECT a FROM Asistencia1 a WHERE a.idActividad.id = (:id)")
	List<Asistencia1> obtenerAsistenciaActividad(long id);

	@Query(value = "SELECT asi.id from actividad ac join asistencia1 asi on ac.id=asi.id_actividad "
			+ "WHERE ac.id = (:id)", nativeQuery = true)
	Collection<Object> obtenerAsistenciaPorActividad(long id);

	
	@Query(value = "SELECT categoria_actividad.nombre, actividad.descripcion, asistencia1.id, adulto_mayor.apellido from "
			+ "categoria_actividad  join actividad on categoria_actividad.id_categoria=actividad.categoria_id "
			+ "join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula", nativeQuery = true)
	Collection<Object> obtenerPersonas();

	@Query(value = "SELECT adm from actividad ac join asistencia1 asi on ac.id=asi.id_actividad "
			+"join adulto_mayor adm on asi.cedula_adulto=adm.cedula "
			+ "WHERE ac.id = (:id)", nativeQuery = true)
	List<AdultoMayor> obtenerPersonasPorActividad(long id);
	
	@Query(value = "SELECT adm.nombre, adm.apellido, adm.cedula, adm.celular, ver.nombre AS nombreVereda from actividad ac join asistencia1 asi on ac.id=asi.id_actividad "
			+"join adulto_mayor adm on asi.cedula_adulto=adm.cedula join vereda ver  on adm.vereda_id=ver.id_vereda "
			+ "WHERE ac.id = (:id)", nativeQuery = true)
	Collection<Object> obtenerPersonasPorActividad1(long id);
	
	@Query(value = "SELECT * FROM asistencia1 a1 WHERE a1.id_actividad = (:id) AND a1.cedula_adulto = (:cedula) ", nativeQuery = true)	
	Collection<Asistencia1> validarasistencia(long id, long cedula);

	

}

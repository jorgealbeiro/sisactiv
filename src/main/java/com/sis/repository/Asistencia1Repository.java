package com.sis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sis.models.entity.Asistencia1;

@Repository
public interface Asistencia1Repository extends CrudRepository<Asistencia1, Long> {

	@Query("SELECT a FROM Asistencia1 a WHERE a.idActividad.id = (:id)")
	List<Asistencia1> obtenerAsistenciaActividad(long id);

	@Query(value = "SELECT as.id from " + "actividad a join asistencia1 as on a.id=as.id_actividad "
			+ "WHERE a.id = (:id)", nativeQuery = true)
	Collection<Object> obtenerPersonasPorCategoria(long id);

//	@Query(value = "SELECT actividad.descripcion, asistencia1.id from "
//			+ "actividad  join asistencia1  on actividad.id=asistencia1.id_actividad ", nativeQuery = true)
//	Collection<Object> obtenerPersonas();

	@Query(value = "SELECT categoria_actividad.nombre, actividad.descripcion, asistencia1.id, adulto_mayor.apellido from "
			+ "categoria_actividad  join actividad on categoria_actividad.id_categoria=actividad.categoria_id "
			+ "join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula", nativeQuery = true)
	Collection<Object> obtenerPersonas();

	@Query(value = "SELECT categoria_actividad.nombre, actividad.descripcion, asistencia1.id, adulto_mayor.apellido from "
			+ "categoria_actividad  join actividad on categoria_actividad.id_categoria=actividad.categoria_id "
			+ "join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula", nativeQuery = true)
	Collection<Object> obtenerPersonas1();

}

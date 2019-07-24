package com.sis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sis.models.entity.AdultoMayor;
import com.sis.models.entity.Asistencia1;
import com.sis.models.entity.CategoriaActividad;

@Repository
public interface Asistencia1Repository extends CrudRepository<Asistencia1, Long> {

	@Query("SELECT a FROM Asistencia1 a WHERE a.idActividad.id = (:id)")
	List<Asistencia1> obtenerAsistenciaActividad(long id);

	@Query(value = "SELECT asi.id from actividad ac join asistencia1 asi on ac.id=asi.id_actividad "
			+ "WHERE ac.id = (:id)", nativeQuery = true)
	Collection<Object> obtenerAsistenciaPorActividad(long id);
	
//	@Query(value = "SELECT categoria_actividad.nombre, actividad.descripcion, asistencia1.id, adulto_mayor.apellido from "
//			+ "categoria_actividad  join actividad on categoria_actividad.id_categoria=actividad.categoria_id "
//			+ "join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula", nativeQuery = true)
//	Collection<Object> obtenerPersonas();
	
	@Query(value = "SELECT categoria_actividad.nombre, categoria_actividad.id_categoria , actividad.descripcion, asistencia1.id, adulto_mayor.apellido from categoria_actividad  join actividad on categoria_actividad.id_categoria=actividad.categoria_id \r\n" + 
			"			join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula\r\n" + 
			"            where categoria_actividad.id_categoria =(:id) ", nativeQuery = true)
	Collection<Object> obtenerPersonas(int id);

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

	@Query(value = "select categoria_actividad.id_categoria from categoria_actividad", nativeQuery = true)
	List<Integer> obtenerlistaCategorias();

	@Query(value = "select categoria_actividad.nombre from categoria_actividad", nativeQuery = true)
	List<String> obtenerlistaCategorias1();

	@Query(value = "SELECT categoria_actividad.nombre as nombreCategoria ,adulto_mayor.nombre, adulto_mayor.apellido,  adulto_mayor.genero from  categoria_actividad join actividad on categoria_actividad.id_categoria=actividad.categoria_id " + 
			"			join asistencia1 on actividad.id=asistencia1.id_actividad join adulto_mayor on asistencia1.cedula_adulto=adulto_mayor.cedula " + 
			"            where categoria_actividad.id_categoria =(:id) ", nativeQuery = true)
	List<Object> obtenerPersonasPorCategoriaId(int id);

	

}

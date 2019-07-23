package com.sis.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.CategoriaActividad;

public interface CategoriaRepository extends CrudRepository<CategoriaActividad, Integer> {

	List<CategoriaActividad> findByNombreIgnoreCaseContaining(String nombre);

	@Query(value = "SELECT a.descripcion, a.lugar from "
			+ "categoria_actividad c join actividad a on c.id_categoria=a.categoria_id "
			+ "WHERE c.id_categoria = (:id)", nativeQuery = true)
	Collection<Object> obtenerActividades(int id);



}

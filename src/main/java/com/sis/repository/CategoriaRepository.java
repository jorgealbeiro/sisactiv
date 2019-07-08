package com.sis.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.CategoriaActividad;

public interface CategoriaRepository extends CrudRepository<CategoriaActividad, Integer> {

	List<CategoriaActividad> findByNombreIgnoreCaseContaining(String nombre);
	

}

package com.sis.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Vereda;

public interface VeredaRepository extends CrudRepository<Vereda, Integer>{

	@Query(value = "SELECT * FROM Vereda s WHERE s.nombre = ?1", nativeQuery = true)
	Collection<Vereda> findAllServicesByName(String name);
}

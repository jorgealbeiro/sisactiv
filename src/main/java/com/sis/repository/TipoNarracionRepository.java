package com.sis.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.TipoNarracion;

public interface TipoNarracionRepository extends CrudRepository<TipoNarracion, Long> {

	@Query(value = "SELECT n.nombre, n.cedula_adulto from "
			+ "tipo_narracion tn join narracion n on tn.id_tipo_narracion=n.tipo_narracion "
			+ "WHERE tn.id_tipo_narracion = (:id)", nativeQuery = true)
	Collection<Object> obtenerNarraciones(long id);

}

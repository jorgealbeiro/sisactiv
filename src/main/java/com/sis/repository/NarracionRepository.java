package com.sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Actividad;
import com.sis.models.entity.Narracion;

public interface NarracionRepository extends CrudRepository<Narracion, Long>{

	@Query("SELECT a FROM Narracion a WHERE a.estado =  com.sis.models.entity.Estado.ACTIVO")
	List<Narracion> getByEstadoActivos();
	
	@Query("SELECT a FROM Narracion a WHERE a.estado =  com.sis.models.entity.Estado.ELIMINADO")
	List<Narracion> getByEstadoEliminado();

	@Query("SELECT a FROM Narracion a WHERE a.cedulaPersona.cedulaPersona = ?1 ")
	List<Narracion> obtenerNarracionesColaborador(long id);

	@Query("SELECT a FROM Narracion a WHERE a.AdultoMayor.cedula = ?1")
	List<Narracion> getNarracionesAdultoMayor(long id);
	
//	@Query("SELECT a FROM Actividad a WHERE a.personaEncargada.cedulaPersona =?1")
//	List<Actividad> getActividadesColaborador(long id);
	
	

}

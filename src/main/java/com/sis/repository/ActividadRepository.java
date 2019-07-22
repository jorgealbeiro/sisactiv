package com.sis.repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Actividad;


public interface ActividadRepository extends CrudRepository<Actividad, Long>{

	@Query(value = "SELECT * FROM actividad a WHERE a.fecha = (:fecha)", nativeQuery = true)
	Collection<Actividad> filtrarFecha(Date fecha);

	@Query(value = "SELECT * FROM actividad a WHERE a.persona_cedula = (:id)", nativeQuery = true)
	List<Actividad> activiCedula(Long id);

	@Query("SELECT a FROM Actividad a WHERE a.estado =  com.sis.models.entity.Estado.ACTIVO")
	List<Actividad> getByEstadoActivos();

	@Query("SELECT a FROM Actividad a WHERE a.estado =  com.sis.models.entity.Estado.ELIMINADO")
	List<Actividad> getByEstadoEliminado();
	
	@Query("SELECT a FROM Actividad a WHERE a.estadoActividad =  com.sis.models.entity.EstadoActividad.REALIZADA")
	List<Actividad> getByEstadoRealizadas();

	@Query("SELECT a FROM Actividad a WHERE a.estadoActividad =  com.sis.models.entity.EstadoActividad.SINREALIZAR AND a.estado =  com.sis.models.entity.Estado.ACTIVO")
	List<Actividad> getByEstadoSinRealizar();

	@Query("SELECT a FROM Actividad a WHERE a.personaEncargada.cedulaPersona =?1")
	List<Actividad> getActividadesColaborador(long id);

	@Query("SELECT a FROM Actividad a WHERE a.estadoActividad =  com.sis.models.entity.EstadoActividad.SINREALIZAR AND a.estado =  com.sis.models.entity.Estado.ELIMINADO")
	List<Actividad> getByEstadoSinRealizarEliminadas();

	@Query(value = "SELECT * from Actividad a "			
			+ "WHERE a.fecha < (:diaFinal) AND a.fecha >= (:diaInicial)", nativeQuery = true)
	Collection<Object> filterHorario(String diaInicial, String diaFinal);
	

	@Query(value = "SELECT * from Actividad a "			
			+ "WHERE a.fecha < (:diaFinal) AND a.fecha >= (:diaInicial)", nativeQuery = true)
	List<Actividad> getrActividaFecha(String diaInicial, String diaFinal);
	
	
	
	
	
	
//	@Query("SELECT * FROM Actividad a WHERE a.fecha <= :fecha)")
//	List<Actividad> porFecha(Date fecha);	
	

}

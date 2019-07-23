package com.sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sis.models.entity.Actividad;
import com.sis.models.entity.Persona;
import com.sis.models.entity.Profesion;

public interface ProfesionRepository extends CrudRepository<Profesion, Integer> {

//	@Query(value = "SELECT h.hora_inicio, h.hora_fin, c.descripcion, s.nombre_servicio, e.nombre_entrenador from "
//			+ "horario h join clase_horario_clase ch on h.id_horario=ch.horario_clase_id_horario "
//			+ "join clase c on c.id_clase=ch.clase_id_clase join servicio s on c.servicio_id_servicio=s.id_servicio "
//			+ "join entrenador e on c.entrendor_dni_entrenador=e.dni_entrenador "
//			+ "WHERE h.dia <= (:diaFinal) AND h.dia >= (:diaInicial)", nativeQuery = true)
//	
	
	@Query(value = "SELECT * FROM actividad a WHERE a.persona_cedula = (:id)", nativeQuery = true)
	List<Actividad> activiCedula(Long id);
	
	@Query(value = "SELECT pe.nombre from "
			+ "profesion pr join persona pe on pr.idProfesion=pe.profesion.idProfesion"			
			+ "WHERE pr.idProfesion = ?1", nativeQuery = true)
	List<String> obtenerPersonaslis(int id);

}

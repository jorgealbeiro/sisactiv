package com.sis.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.Actividad;
import com.sis.models.entity.Persona;
import com.sis.models.entity.Profesion;
import com.sis.persistence.JsonManager;
import com.sis.repository.ProfesionRepository;

/**
 * Clase que contiene los servicios de profesion
 * @author Yuliana Boyaca
 *
 */
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class ProfesionServicio {

	@Autowired
	private ProfesionRepository profesionRepository;
	
	/**
	 * Servicio que obtiene lista de profesiones
	 * @return
	 */
	@RequestMapping(value = "/obtenerProfesiones", method = RequestMethod.GET)
	public String obtenerProfesiones() {
		return JsonManager.toJson(profesionRepository.findAll());
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos una profesion
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarProfesion", method = RequestMethod.POST)
	public String registrarProfesion(@Valid @RequestBody Profesion profesion) {
		profesionRepository.save(profesion);
		return "Guardado";
	}
	
	@RequestMapping(value = "/obtenerProfesion/{id}", method = RequestMethod.GET)
	public String obtenerProfesion(@PathVariable int id) {
		return JsonManager.toJson(profesionRepository.findById(id));
	}

	@RequestMapping(value = "/borrarProfesion/{id}", method = RequestMethod.DELETE)
	public String borrarProfesion(@PathVariable int id) {
		List<String> mas = profesionRepository.obtenerPersonaslis(id);
//		profesionRepository.deleteById(id);
		return "Borrado"+ mas.size()+ "  "+ mas.get(0) ;
	}

	@RequestMapping(value = "/editarProfesion", method = RequestMethod.PUT)
	public String editarProfesion(@Valid @RequestBody Profesion profesion) {
		return JsonManager.toJson(profesionRepository.save(profesion));
	}
	
}

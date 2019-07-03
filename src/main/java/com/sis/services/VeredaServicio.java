package com.sis.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.Vereda;
import com.sis.persistence.JsonManager;
import com.sis.repository.VeredaRepository;

/**
 * Clase que contiene los servicios de Vereda
 * @author Yuliana Boyaca
 *
 */
@RestController
public class VeredaServicio {

	@Autowired
	private VeredaRepository veredaRepository;
	
	/**
	 * Servicio que registra y agrega a la base de datos una vereda
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarVereda", method = RequestMethod.POST)
	public String registrarVereda(@Valid @RequestBody Vereda p) {
		veredaRepository.save(p);
		return "Guardado";
	}

	/**
	 * Servicio que obtiene lista de veredas
	 * @return
	 */
	@RequestMapping(value = "/obtenerVeredas", method = RequestMethod.GET)
	public String obtenerListaVeredas() {
		return JsonManager.toJson(veredaRepository.findAll());
	}
	
	/**
	 * Servicio que obtiene una vereda de acuerdo al ingresado
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/obtenerVereda/{id}", method = RequestMethod.GET)
	public String obtenerVereda(@PathVariable int id) {
		return JsonManager.toJson(veredaRepository.findById(id));
	}

	@RequestMapping(value = "/borrarVereda/{id}", method = RequestMethod.DELETE)
	public String borrarVereda(@PathVariable int id) {
		veredaRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarVereda", method = RequestMethod.PUT)
	public String editarVereda(@Valid @RequestBody Vereda vereda) {
		return JsonManager.toJson(veredaRepository.save(vereda));
	}
	

}

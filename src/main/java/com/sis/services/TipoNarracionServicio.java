package com.sis.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.TipoNarracion;
import com.sis.persistence.JsonManager;
import com.sis.repository.TipoNarracionRepository;

@RestController
public class TipoNarracionServicio {

	@Autowired
	private TipoNarracionRepository tipoNarracionRepository;
	
	/**
	 * Agregar un nuevo tipo de narracion 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarTipoNarracion", method = RequestMethod.POST)
	public String registrarTipoNarracion(@Valid @RequestBody TipoNarracion p) {
		tipoNarracionRepository.save(p);
		return "Guardado";
	}
	
	
	/**
	 * Servicio que obtiene lista de todos los tipos de narraciones
	 * @return
	 */
	@RequestMapping(value = "/obtenerTiposNarraciones", method = RequestMethod.GET)
	public String getVereda() {
		return JsonManager.toJson(tipoNarracionRepository.findAll());
	}
	
	/**
	 * Servicio que obtiene un tipo de narracion de acuerdo al id ingresado
	 * @param id
	 * @return
	 */	
	@RequestMapping(value = "/obtenerTipoNarracion/{id}", method = RequestMethod.GET)
	public String obtenerTipoNarracion(@PathVariable long id) {
		return JsonManager.toJson(tipoNarracionRepository.findById(id));
	}

	@RequestMapping(value = "/borrarTipoNarracion/{id}", method = RequestMethod.DELETE)
	public String borrarTipoNarracion(@PathVariable long id) {
		tipoNarracionRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarTipoNarracion", method = RequestMethod.PUT)
	public String editarTipoNarracion(@Valid @RequestBody TipoNarracion tipoNarracion) {
		return JsonManager.toJson(tipoNarracionRepository.save(tipoNarracion));
	}
}

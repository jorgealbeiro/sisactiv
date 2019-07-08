package com.sis.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin(origins= {"http://localhost:4200"})
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
	
	@PostMapping("/registrarVereda2")
	public ResponseEntity<?> registrarVereda2(@Valid @RequestBody Vereda v){
		Vereda vereda = veredaRepository.findByNombre(v.getNombre());
		if (vereda==null) {
			veredaRepository.save(v);
			return new ResponseEntity<String>("La vereda ha sido guardada", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("La vereda ya existe", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/registrarVereda3")
	public ResponseEntity<?> registrarVereda3(@Valid @RequestBody Vereda v){
		Map<String,Object> response = new HashMap<String,Object>();
		Vereda vereda = veredaRepository.findByNombre(v.getNombre());
		if (vereda==null) {
			veredaRepository.save(v);
			
			response.put("mensaje", "La vereda ha sido guardada");
			response.put("vereda", v);
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}else {
			response.put("error", "La vereda ya existe");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/obtenerPorNombre/{nombre}")
	public List<Vereda> obtenerPorNombre(@PathVariable String nombre){
		return veredaRepository.findByNombreIgnoreCaseContaining(nombre);
	}
	

	/**
	 * Servicio que obtiene lista de veredas
	 * @return
	 */
	@RequestMapping(value = "/obtenerVeredas", method = RequestMethod.GET)
	public Iterable<Vereda> obtenerListaVeredas() {
		return veredaRepository.findAll();
		//return JsonManager.toJson(veredaRepository.findAll());
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

	@RequestMapping(value = "/editarVereda/{id}", method = RequestMethod.PUT)
	public String editarVereda(@Valid @RequestBody Vereda vereda) {
		return JsonManager.toJson(veredaRepository.save(vereda));
		
	}
	
	
	
	
}

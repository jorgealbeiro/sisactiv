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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.CategoriaActividad;
import com.sis.persistence.JsonManager;
import com.sis.repository.CategoriaRepository;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class CategoriaServicio {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@RequestMapping(value = "/registrarCategoria", method = RequestMethod.POST)
	public String registrarVereda(@Valid @RequestBody CategoriaActividad p) {
		categoriaRepository.save(p);
		return "Guardado";
	}

	@GetMapping("/obtenerPorNombreCategoria/{nombre}")
	public List<CategoriaActividad> obtenerPorNombre(@PathVariable String nombre) {
		return categoriaRepository.findByNombreIgnoreCaseContaining(nombre);
	}

	/**
	 * Servicio que obtiene lista de categorias
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerCategorias", method = RequestMethod.GET)
	public Iterable<CategoriaActividad> obtenerListaCategorias() {
		return categoriaRepository.findAll();
	}

	/**
	 * Servicio que obtiene una vereda de acuerdo al ingresado
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/obtenerCategoria/{id}", method = RequestMethod.GET)
	public String obtenerCategoria(@PathVariable int id) {
		return JsonManager.toJson(categoriaRepository.findById(id));
	}

	@RequestMapping(value = "/borrarCategoria/{id}", method = RequestMethod.DELETE)
	public String borrarCategoria(@PathVariable int id) {
		categoriaRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarCategoria/{id}", method = RequestMethod.PUT)
	public String editarVereda(@Valid @RequestBody CategoriaActividad vereda) {
		return JsonManager.toJson(categoriaRepository.save(vereda));
	}

}

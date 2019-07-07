package com.sis.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.Actividad;
import com.sis.models.entity.AdultoMayor;
import com.sis.models.entity.Narracion;
import com.sis.models.entity.Persona;
import com.sis.persistence.JsonManager;
import com.sis.repository.ActividadRepository;
import com.sis.repository.AdultoMayorRepository;
import com.sis.repository.NarracionRepository;
import com.sis.repository.PersonaRepository;


@CrossOrigin(origins= {"http://localhost:4200", "http://192.168.0.28:4200"})
@RestController
public class SisActivaApplicationController {


	@Autowired
	private NarracionRepository narracionRepository;
	@Autowired
	private AdultoMayorRepository adultoMayorRepository;
	@Autowired
	private ActividadRepository actividadRepository;
	@Autowired
	private PersonaRepository personaRepository;
	

	/**
	 * Servicio que obtiene lista de personas
	 * @return
	 */
	@RequestMapping(value = "/obtenerPersonas", method = RequestMethod.GET)
	public String obtenerListaPersonas() {
		return JsonManager.toJson(personaRepository.findAll());
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos una persona
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarPersona", method = RequestMethod.POST)
	public String registrarPersona(@Valid @RequestBody Persona persona) {
		personaRepository.save(persona);
		System.out.println("Personita guardada" + JsonManager.toJson(persona));
		return JsonManager.toJson(persona);
	}
	
	/**
	 * Metodo que  autentica el ingreso al sistema de acuerdo a las credenciales correo y contrasenia
	 * @param usuario
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login/{correo}/{contrasenia}", method = RequestMethod.GET)
	public String login( @PathVariable("correo") String usuario, @PathVariable("contrasenia") String password) {
		Collection<Persona> admin = personaRepository.autenticar(usuario, password);
		if (admin.size() > 0) {
			return JsonManager.toJson(admin);
		}
		return "Usuario no existe";
	}
	
	@RequestMapping(value = "/obtenerPersona/{id}", method = RequestMethod.GET)
	public String obtenerPersona(@PathVariable Long id) {
		return JsonManager.toJson(personaRepository.findById(id));
	}

	@RequestMapping(value = "/borrarPersona/{id}", method = RequestMethod.DELETE)
	public String borrarPersona(@PathVariable Long id) {
		personaRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarPersona", method = RequestMethod.PUT)
	public String editarPersona(@Valid @RequestBody Persona persona) {
		return JsonManager.toJson(personaRepository.save(persona));
	}
	
	
	
	//-------------------------------------Actividades-----------------------------------------------//
	/**
	 * Servicio que obtiene lista de actividades
	 * @return
	 */
	@RequestMapping(value = "/obtenerActividades", method = RequestMethod.GET)
	public String obtenerActividades() {
		return JsonManager.toJson(actividadRepository.findAll());
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos una actividad
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarActividad/cedula/{cedula}", method = RequestMethod.POST)
	public String registrarActividad(@Valid @RequestBody Actividad actividad, @PathVariable("cedula") Long cedulaPersona) {
		System.out.println("buuu" + JsonManager.toJson(actividad));
		Persona persona =  personaRepository.findById(cedulaPersona).get();
		System.out.println("personita..." + persona.getNombre());
		actividad.setCedula(persona);
		actividadRepository.save(actividad);
		return "Guardado";
	}
	
	@RequestMapping(value = "/obtenerActividad/{id}", method = RequestMethod.GET)
	public String obtenerActividad(@PathVariable Long id) {
		return JsonManager.toJson(actividadRepository.findById(id));
	}

	@RequestMapping(value = "/borrarActividad/{id}", method = RequestMethod.DELETE)
	public String borrarActividad(@PathVariable Long id) {
		actividadRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarActividad/{cedula}", method = RequestMethod.PUT)
	public String editarActividad(@Valid @RequestBody Actividad actividad,  @PathVariable("cedula") Long cedulaPersona) {
		System.out.println("A editar" + JsonManager.toJson(actividad));
		Persona persona =  personaRepository.findById(cedulaPersona).get();
		actividad.setCedula(persona);
		
		return JsonManager.toJson(actividadRepository.save(actividad));
	}
	
	//------------------------------ADULTO MAYOR--------------------------------------------------
	/**
	 * Servicio que obtiene lista de adultos
	 * @return
	 */
	@RequestMapping(value = "/obtenerAdultosMayores", method = RequestMethod.GET)
	public String obtenerAdultosMayores() {
		return JsonManager.toJson(adultoMayorRepository.findAll());
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos un adulto mayor
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarAdultoMayor", method = RequestMethod.POST)
	public String registrarVereda(@Valid @RequestBody AdultoMayor adultoMayor) {
		adultoMayorRepository.save(adultoMayor);
		return "Guardado";
	}
	
	@RequestMapping(value = "/obtenerAdulto/{id}", method = RequestMethod.GET)
	public String obtenerAdulto(@PathVariable Long id) {
		return JsonManager.toJson(adultoMayorRepository.findById(id));
	}

	@RequestMapping(value = "/borrarAdulto/{id}", method = RequestMethod.DELETE)
	public String borrarAdulto(@PathVariable Long id) {
		adultoMayorRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarAdulto", method = RequestMethod.PUT)
	public String editarAdulto(@Valid @RequestBody AdultoMayor adultoMayor) {
		return JsonManager.toJson(adultoMayorRepository.save(adultoMayor));
	}

	//-----------------------------------------NARRACIONES--------------------------------------------
	/**
	 * Servicio que obtiene lista de narraciones
	 * @return
	 */
	@RequestMapping(value = "/obtenerNarraciones", method = RequestMethod.GET)
	public String obtenerNarraciones() {
		return JsonManager.toJson(narracionRepository.findAll());
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos una narracion
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarNarracion/cedula/{cedula}", method = RequestMethod.POST)
	public String registrarNarracion(@Valid @RequestBody Narracion narracion , @PathVariable("cedula") Long cedula) {
		System.out.println("buuu" + JsonManager.toJson(narracion));
		AdultoMayor adultoMayor = adultoMayorRepository.findById(cedula).get();
		System.out.println("personita..." + adultoMayor.getNombre());
		narracion.setCedulaAdulto(adultoMayor);
		narracionRepository.save(narracion);
		return "Guardado ";
	}
	
	@RequestMapping(value = "/obtenerNarracion/{id}", method = RequestMethod.GET)
	public String obtenerNarracion(@PathVariable Long id) {
		return JsonManager.toJson(narracionRepository.findById(id));
	}

	@RequestMapping(value = "/borrarNarracion/{id}", method = RequestMethod.DELETE)
	public String borrarNarracion(@PathVariable Long id) {
		narracionRepository.deleteById(id);
		return "Borrado";
	}

	@RequestMapping(value = "/editarNarracion", method = RequestMethod.PUT)
	public String editarNarracion(@Valid @RequestBody Narracion narracion) {
		return JsonManager.toJson(narracionRepository.save(narracion));
	}
	
}

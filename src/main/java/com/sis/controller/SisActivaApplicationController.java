package com.sis.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sis.models.entity.Actividad;
import com.sis.models.entity.AdultoMayor;
import com.sis.models.entity.Estado;
import com.sis.models.entity.EstadoAfiliacion;
import com.sis.models.entity.Narracion;
import com.sis.models.entity.Persona;
import com.sis.persistence.JsonManager;
import com.sis.repository.ActividadRepository;
import com.sis.repository.AdultoMayorRepository;
import com.sis.repository.NarracionRepository;
import com.sis.repository.PersonaRepository;

@CrossOrigin(origins = { "http://localhost:4200", "http://192.168.0.28:4200" })
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerPersonas", method = RequestMethod.GET)
	public String obtenerListaPersonas() {
		return JsonManager.toJson(personaRepository.findAll());
	}

	/**
	 * metodo que obtiene a todas las personas  activas
	 */
	@RequestMapping(value = "/obtenerPersonasActivas", method = RequestMethod.GET)
	public @ResponseBody List<Persona> obtenerPersonas() {
		return  personaRepository.getByEstadoActivos();
	}
	
	/**
	 * metodo que obtiene a todas las personas  eliminadas
	 */
	@RequestMapping(value = "/obtenerPersonasEliminadas", method = RequestMethod.GET)
	public @ResponseBody List<Persona> obtenerPersonasEliminadas() {
		return  personaRepository.getByEstadoEliminado();
	}
	
	/**
	 * Servicio que registra y agrega a la base de datos una persona
	 * 
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
	 * Metodo que autentica el ingreso al sistema de acuerdo a las credenciales
	 * correo y contrasenia
	 * 
	 * @param usuario
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login/{correo}/{contrasenia}", method = RequestMethod.GET)
	public String login(@PathVariable(value = "correo") String usuario,
			@PathVariable(value = "contrasenia") String password) {
		Collection<Persona> admin = personaRepository.autenticar(usuario, password);
//		Persona p = personaRepository.autenticar1(usuario, password);
		if (admin.size() > 0) {
			return JsonManager.toJson(admin);
		}
		return "Usuario no existe";
	}


	@RequestMapping(value = "/obtenerPersona/{id}", method = RequestMethod.GET)
	public String obtenerPersona(@PathVariable Long id) {
		return JsonManager.toJson(personaRepository.findById(id));
	}

	/**
	 * Metodo que elimina persona en la base de datos 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/borrarPersona/{id}", method = RequestMethod.DELETE)
	public String borrarPersona(@PathVariable Long id) {
		personaRepository.deleteById(id);
		return "Borrado";
	}
	
	/**
	 * Metodo para eliminar pesona pero no de la base de datos
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/eliminarPersona/{id}", method = RequestMethod.DELETE)
	public @ResponseBody List<Actividad> eliminarPersona(@PathVariable Long id) {
		Persona a = personaRepository.findById(id).get();		
		a.setEstado(Estado.ELIMINADO);
		personaRepository.deleteById(id);
		personaRepository.save(a);	
		List<Actividad> ma = eliminarActividadesDEPersona(id);		
		return ma;
	}

private List<Actividad> eliminarActividadesDEPersona(Long id) {
	List<Actividad> m = actividadRepository.activiCedula(id);
	for (Actividad actividad : m) {			
		Actividad h = actividadRepository.findById(actividad.getId()).get();
		h.setEstado(Estado.ELIMINADO);
		actividadRepository.deleteById(actividad.getId());
		actividadRepository.save(h);
	}
	List<Actividad> n = actividadRepository.activiCedula(id);
		return n;
	}


	@RequestMapping(value = "/editarPersona", method = RequestMethod.PUT)
	public String editarPersona(@Valid @RequestBody Persona persona) {
		return JsonManager.toJson(personaRepository.save(persona));
	}
	
	

	// -------------------------------------Actividades-----------------------------------------------//
	/**
	 * Servicio que obtiene lista de actividades
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerActividades", method = RequestMethod.GET)
	public String obtenerActividades() {
		return JsonManager.toJson(actividadRepository.findAll());
	}
	
	/**
	 * metodo que obtiene a todas las actividades  activas
	 */
	@RequestMapping(value = "/obtenerActividadesActivas", method = RequestMethod.GET)
	public @ResponseBody List<Actividad> obtenerActividadesActivas() {
		return  actividadRepository.getByEstadoActivos();
	}
	
	/**
	 * metodo que obtiene a todas las Actividades  eliminadas
	 */
	@RequestMapping(value = "/obtenerActividadesEliminadas", method = RequestMethod.GET)
	public @ResponseBody List<Actividad> obtenerActividadesEliminadas() {
		return  actividadRepository.getByEstadoEliminado();
	}
	
	/**
	 * metodo que obtiene a todas las actividades  realizadas
	 */
	@RequestMapping(value = "/obtenerActividadesRealizadas", method = RequestMethod.GET)
	public @ResponseBody List<Actividad> obtenerActividadesRealizadas() {
		return  actividadRepository.getByEstadoRealizadas();
	}
	
	/**
	 * metodo que obtiene a todas las actividades  sin realizar y que aun estan activas 
	 */
	@RequestMapping(value = "/obtenerActividadesSinRealizar", method = RequestMethod.GET)
	public @ResponseBody List<Actividad> obtenerActividadesSinRealizar() {
		return  actividadRepository.getByEstadoSinRealizar();
	}
	
	
	/**
	 * Servicio que registra y agrega a la base de datos una actividad
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarActividad/cedula/{cedula}", method = RequestMethod.POST)
	public String registrarActividad(@Valid @RequestBody Actividad actividad,
			@PathVariable("cedula") Long cedulaPersona) {
		Persona persona = personaRepository.findById(cedulaPersona).get();
		actividad.setCedula(persona);
		actividadRepository.save(actividad);
		return "Guardado";
	}

	@RequestMapping(value = "/obtenerActividad/{id}", method = RequestMethod.GET)
	public String obtenerActividad(@PathVariable Long id) {
		return JsonManager.toJson(actividadRepository.findById(id));
	}
	
	
	@RequestMapping(value ="/obtenerActividadFecha/{since}", method = RequestMethod.GET)
    public String showAddedSince(@PathVariable("since") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date since) {
        return since + "";
    }
	
	

	@RequestMapping(value = "/borrarActividad/{id}", method = RequestMethod.DELETE)
	public String borrarActividad(@PathVariable Long id) {
		actividadRepository.deleteById(id);
		return "Borrado";
	}
	
	/**
	 * Metodo principal para eliminar una actividad sin borrarlo de la base de datos 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/eliminarActividad/{id}", method = RequestMethod.DELETE)
	public String eliminarActividad(@PathVariable Long id) {
		Actividad h = actividadRepository.findById(id).get();
		h.setEstado(Estado.ELIMINADO);
		actividadRepository.deleteById(id);
		actividadRepository.save(h);		
		return "Actividad borrada";
	}
	

	@RequestMapping(value = "/editarActividad/{cedula}", method = RequestMethod.PUT)
	public String editarActividad(@Valid @RequestBody Actividad actividad, @PathVariable("cedula") Long cedulaPersona) {
		System.out.println("A editar" + JsonManager.toJson(actividad));
		Persona persona = personaRepository.findById(cedulaPersona).get();
		actividad.setCedula(persona);

		return JsonManager.toJson(actividadRepository.save(actividad));
	}

	@RequestMapping(value = "/obtenerActividadDelDia/{fecha}", method = RequestMethod.GET)
	public String obtenerActividadDelDIa(@PathVariable Date fecha) {
		Collection<Actividad> admin = actividadRepository.filtrarFecha(fecha);
		if (admin.size() > 0) {
			return JsonManager.toJson(admin);
		}
		return "Usuario no existe";
	}

	// ------------------------------ADULTO
	// MAYOR--------------------------------------------------
	/**
	 * Servicio que obtiene lista general de adultos 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerAdultosMayores", method = RequestMethod.GET)
	public String obtenerAdultosMayores() {
		return JsonManager.toJson(adultoMayorRepository.findAll());
	}
	
	/**
	 * metodo que obtiene a todos los adultos mayores activos
	 */
	@RequestMapping(value = "/obtenerAdultosActivos", method = RequestMethod.GET)
	public @ResponseBody List<AdultoMayor> obtenerAdultos() {
		return  adultoMayorRepository.getByEstadoAfiliacion();
	}

	/**
	 * metodo que obtiene a todos los adultos mayores INACTIVOS
	 */
	@RequestMapping(value = "/obtenerAdultosInactivos", method = RequestMethod.GET)
	public @ResponseBody List<AdultoMayor> obtenerAdultosInactivos() {
		return  adultoMayorRepository.getByEstadoAfiliacionInactivo();
	}
	
	/**
	 * metodo que obtiene a todos los adultos mayores DESAFILIADOS
	 */
	@RequestMapping(value = "/obtenerAdultosDesafiliados", method = RequestMethod.GET)
	public @ResponseBody List<AdultoMayor> obtenerAdultosDesafiliados() {
		return  adultoMayorRepository.getByEstadoAfiliacionDesafiliado();
	}
	
	
	/**
	 * Servicio que registra y agrega a la base de datos un adulto mayor
	 * 
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

	/**
	 * Metodo principal para eliminar una abuelito sin borrarlo de la base de datos 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/eliminarAdulto/{id}", method = RequestMethod.DELETE)
	public String eliminarAdulto(@PathVariable Long id) {
		AdultoMayor a = adultoMayorRepository.findById(id).get();
		a.setEstadoAfiliacion(EstadoAfiliacion.INACTIVO);
		adultoMayorRepository.deleteById(id);
		adultoMayorRepository.save(a);		
		return "Borrado";
	}


	@RequestMapping(value = "/editarAdulto/{cedula}", method = RequestMethod.PUT)
	public String editarAdulto(@Valid @RequestBody AdultoMayor adultoMayor, @PathVariable("cedula") Long cedula) {
		adultoMayorRepository.deleteById(cedula);
		return JsonManager.toJson(adultoMayorRepository.save(adultoMayor));
	}


	// -----------------------------------------NARRACIONES--------------------------------------------
	/**
	 * Servicio que obtiene lista de narraciones
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obtenerNarraciones", method = RequestMethod.GET)
	public String obtenerNarraciones() {
		return JsonManager.toJson(narracionRepository.findAll());
	}
	 
	/**
	 * OBTENER NARRACION POR ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/obtenerNarracion/{id}", method = RequestMethod.GET)
	public String obtenerNarracion(@PathVariable Long id) {
		return JsonManager.toJson(narracionRepository.findById(id));
	}
	
	
	/**
	 * metodo que obtiene a todas las NARRACIONES  activas
	 */
	@RequestMapping(value = "/obtenerNarracionesActivas", method = RequestMethod.GET)
	public @ResponseBody List<Narracion> obtenerNarracionesActivas() {
		return  narracionRepository.getByEstadoActivos();
	}
	
	/**
	 * metodo que obtiene a todas las NARRACIONES  eliminadas
	 */
	@RequestMapping(value = "/obtenerNarracionesEliminadas", method = RequestMethod.GET)
	public @ResponseBody List<Narracion> obtenerNarracionesEliminadas() {
		return  narracionRepository.getByEstadoEliminado();
	}
	

	/**
	 * Servicio que registra y agrega a la base de datos una narracion
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/registrarNarracion", method = RequestMethod.POST)
	public String registrarVereda(@Valid @RequestBody Narracion narracion) {
		narracionRepository.save(narracion);
		return "Guardado";
	}
	
	
	@RequestMapping(value = "/registrarNarracion/cedula/{cedula}", method = RequestMethod.POST)
	public String registrarNarracion(@Valid @RequestBody Narracion narracion , @PathVariable("cedula") Long cedula) {
		AdultoMayor adultoMayor = adultoMayorRepository.findById(cedula).get();
		narracion.setCedulaAdulto(adultoMayor);
		narracionRepository.save(narracion);
		return "Guardado";
	}
	
	

	/*
	 * Metodo para eliminar narracion, pero no de la base de datos
	 */
	@RequestMapping(value = "/borrarNarracion/{id}", method = RequestMethod.DELETE)
	public String borrarNarracion(@PathVariable Long id) {
		Narracion aux = narracionRepository.findById(id).get();
		aux.setEstado(Estado.ELIMINADO);		
		narracionRepository.deleteById(id);
		narracionRepository.save(aux);
		return "Narracion Borrada";
	}
	
	

	@RequestMapping(value = "/editarNarracion", method = RequestMethod.PUT)
	public String editarNarracion(@Valid @RequestBody Narracion narracion) {
		return JsonManager.toJson(narracionRepository.save(narracion));
	}
		
	/*
	 * Metodo para editar narracion full
	 */
	@RequestMapping(value = "/editarNarracion/{id}", method = RequestMethod.PUT)
	public String editarNarracion(@Valid @RequestBody Narracion narracion, @PathVariable("id") Long id) {
		narracionRepository.deleteById(id);
		return JsonManager.toJson(narracionRepository.save(narracion));
	}
	
	
	
	

}

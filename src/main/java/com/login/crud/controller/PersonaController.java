package com.login.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.login.crud.model.Persona;
import com.login.crud.service.PersonaService;



@Controller
@RequestMapping("/api/persona/")
public class PersonaController {

	@Autowired
	private PersonaService personSer;
	
	@PostMapping("nueva")
	private String guardar(Persona p){
		Persona temp = personSer.create(p);
		
		return temp == null ? "error_page" : "redirect:/api/persona/listar";
		
	}
	
	@RequestMapping("add")
	public String formularioAgregar() {
		return "form_agregar";
	}
	
	@GetMapping("listar")
	private String listarPersonas(Model model){
		List<Persona> personas = personSer.getPersonas();
		model.addAttribute("personas", personas);
		
		return "lista";
	}
	
	@RequestMapping("editar/{id}")
	public ModelAndView formularioEditar(@PathVariable(name = "id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_persona");
		
		Optional<Persona> p = personSer.getById(id); 
		modelo.addObject("persona", p);
		
		return modelo;
	}
	
	@RequestMapping("eliminar/{id}")
	public String eliminarPersona(@PathVariable(name = "id") Long id) {
		personSer.delete(id);
		return "redirect:/api/persona/listar";
	}
	
	@DeleteMapping
	private ResponseEntity<Void> eliminarPersona(Persona p){
		personSer.delete(p);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "{id}")
	private ResponseEntity<Optional<Persona>> getPersona(@PathVariable("id") Long id){
		return ResponseEntity.ok(personSer.getById(id));
	}
}
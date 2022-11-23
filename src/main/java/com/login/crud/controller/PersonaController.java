package com.login.crud.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.crud.model.Persona;
import com.login.crud.service.PersonaService;



@RestController
@RequestMapping("/api/persona/")
public class PersonaController {

	@Autowired
	private PersonaService personSer;
	
	@PostMapping
	private ResponseEntity<Persona> guardar(@RequestBody Persona p){
		Persona temp = personSer.create(p);
		
		try {
			return ResponseEntity.created(new URI("/api/persona" + temp.getId())).body(temp);
		}
		catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping
	private ResponseEntity<List<Persona>> listarPersonas(){
		return ResponseEntity.ok(personSer.getPersonas());
	}
	
	
	@DeleteMapping
	private ResponseEntity<Void> eliminarPersona(@RequestBody Persona p){
		personSer.delete(p);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "{id}")
	private ResponseEntity<Optional<Persona>> getPersona(@PathVariable("id") Long id){
		return ResponseEntity.ok(personSer.getById(id));
	}
}
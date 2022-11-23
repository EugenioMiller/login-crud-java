package com.login.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.crud.model.Persona;
import com.login.crud.repository.PersonaRepository;


@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personRepo;
	
	
	public Persona create (Persona p) {
		return personRepo.save(p);
	}
	
	public List<Persona> getPersonas(){
		return personRepo.findAll();
	}
	
	public void delete (Persona p) {
		personRepo.delete(p);
	}
	
	public Optional<Persona> getById(Long id) {
		return personRepo.findById(id);
	}
}

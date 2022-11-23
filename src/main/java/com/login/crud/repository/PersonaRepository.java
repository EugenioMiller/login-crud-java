package com.login.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.crud.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
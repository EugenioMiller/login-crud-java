package com.login.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.crud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByEmailAndPassword(String email, String password);
}

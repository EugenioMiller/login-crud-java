package com.login.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.crud.model.Usuario;
import com.login.crud.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepo;
	
	public Usuario registrarUsuario(String password, String email, String nombre) {
		if(password != null && email !=null) {
			Usuario temp = new Usuario();
			temp.setEmail(email);
			temp.setPassword(password);
			temp.setNombre(nombre);
			return userRepo.save(temp);
		}else {
			return null;
		}
	}

	
	public Usuario autentica(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}
}
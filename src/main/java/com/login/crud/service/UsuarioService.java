package com.login.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.crud.model.Usuario;
import com.login.crud.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepo;
	
	public Usuario create (Usuario u) {
		return userRepo.save(u);
	}
	
	public Usuario registrarUsuario(String nombre, String email, String password) {
		if(password != null && email !=null) {
			Usuario temp = new Usuario();
			temp.setNombre(nombre);
			temp.setEmail(email);
			temp.setPassword(password);
			return userRepo.save(temp);
		}else {
			return null;
		}
	}
	
	public Usuario autentica(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}
	
	public Usuario guardar(Usuario u) {
		return userRepo.save(u);
	}
}
package com.login.crud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.login.crud.model.Usuario;
import com.login.crud.service.UsuarioService;

@Controller
@RequestMapping("/api/usuario/")
public class UsuarioController {
	
	@Autowired
	private UsuarioService userServ;
	
	@PostMapping
	private ResponseEntity<Usuario> guardar(@RequestBody Usuario u){
		Usuario temp = userServ.create(u);
		
		try {
			return ResponseEntity.created(new URI("/api/usuario" + temp.getId())).body(temp);
		}
		catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@RequestMapping("registro")
	public String getPaginaRegistro(Model model) {
		model.addAttribute("registerRequest", new Usuario());
		return "pagina_registro";
	}

	@RequestMapping("login")
	public String getPaginaPrincipal(Model model) {
		model.addAttribute("loginRequest", new Usuario());
		return "home";
	}
	
	@PostMapping("registro")
	public String registrar(@ModelAttribute Usuario u) {
		Usuario temp = userServ.registrarUsuario(u.getNombre(), u.getEmail(), u.getPassword());
		return temp == null ? "error_page" : "redirect:/";
	}
	
	@PostMapping("login")
	public String login(@ModelAttribute Usuario u, Model model) {
		Usuario autenticado = userServ.autentica(u.getEmail(), u.getPassword());
		if (autenticado != null) {
			model.addAttribute("userLogin", autenticado.getNombre());
			return "home";
		}else {
			return "error_page";
		}
	}
	
}
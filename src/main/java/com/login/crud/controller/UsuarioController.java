package com.login.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {

	@RequestMapping("/registro")
	public String getPaginaRegistro() {
		return "pagina_registro";
	}

}
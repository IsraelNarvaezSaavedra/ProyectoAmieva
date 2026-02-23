package com.proyecto.amieva.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.amieva.entity.Profesor;
import com.proyecto.amieva.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AuthController {
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@GetMapping("/login")
	public String showLogin() {
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public String showLogout() {
		return "auth/logout";
	}
	
	@GetMapping({"/", "/index","/home"})
	public String showIndex() {
		return "auth/index";
	}
	
	@GetMapping("/error")
	public String showError() {
		return "error/error";
	}
	
	@GetMapping("/perfil")
	public String showPerfil(Model model) {
		//Obtiene el usuario que esta en la sesion
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		
		//Busca por email el profesor
		Profesor profesor = profesorRepository.findByEmail(email).orElse(null);
		
		if (profesor == null) {
			return "redirect:/logout";
		}
		
		model.addAttribute("profesor", profesor);
		return "auth/perfil";
	}
}

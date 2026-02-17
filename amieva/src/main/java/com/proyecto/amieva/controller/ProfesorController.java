package com.proyecto.amieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.amieva.service.ProfesorService;

@Controller
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	
	@GetMapping("/profesores")
	public String mostrarProfesores() {
		return "pages/profesores";
	}
}

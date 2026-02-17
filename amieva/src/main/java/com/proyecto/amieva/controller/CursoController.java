package com.proyecto.amieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.amieva.service.CursoService;

@Controller
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/cursos")
	public String mostrarCursos() {
		return "pages/cursos";
	}
}

package com.proyecto.amieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.amieva.service.AlumnoService;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/alumnos")
	public String mostrarAlumnos() {
		return "pages/alumnos";
	}
}

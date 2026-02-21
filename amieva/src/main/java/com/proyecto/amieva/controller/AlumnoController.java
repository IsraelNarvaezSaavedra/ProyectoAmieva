package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.service.AlumnoService;

import org.springframework.ui.Model;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/alumnos")
	public String mostrarAlumnos(Model model) {
		List<Alumno> alumnos = alumnoService.listarAlumnos();
		model.addAttribute("alumnos", alumnos);
		return "pages/alumnos/alumnos";
	}
	
	
	@GetMapping("/alumnos/eliminar")
	public String eliminarAlumno(Long id) {
		alumnoService.eliminarAlumnoPorId(id);
		return "redirect:/alumnos";
	}
}

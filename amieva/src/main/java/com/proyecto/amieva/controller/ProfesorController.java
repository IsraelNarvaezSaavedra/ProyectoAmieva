package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.amieva.entity.Profesor;
import com.proyecto.amieva.service.ProfesorService;

@Controller
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	
	@GetMapping("/profesores")
	public String mostrarProfesores(Model model) {
		List<Profesor> profesores = profesorService.listarProfesores();
		model.addAttribute("profesores", profesores);
		return "pages/profesores/profesores";
	}
	
	@GetMapping("/profesores/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "pages/profesores/crear";
	}
	
	@PostMapping("/profesores/crear")
	public String crearProfesor(Profesor profesor) {
		profesorService.guardarProfesor(profesor);
		return "redirect:/profesores";
	}
	
	@GetMapping("/profesores/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Profesor profesor = profesorService.getProfesorPorId(id);
		if (profesor == null) {
			return "redirect:/profesores";
		}
		model.addAttribute("profesor", profesor);
		return "pages/profesores/editar";
	}
	
	@PostMapping("/profesores/editar/{id}")
	public String editarProfesor(@PathVariable Long id, Profesor profesor) {
		//Reservamos la contraseña
		Profesor profesorExistente = profesorService.getProfesorPorId(id);
		
		if (profesorExistente != null) {
			//Si está vacia se deja la anterior, si no se actualiza
			if (profesor.getContrasena() == null || profesor.getContrasena().trim().isEmpty()) {
				profesor.setContrasena(profesorExistente.getContrasena());
			}
			profesor.setId(id);
			profesorService.guardarProfesor(profesor);
		}
		
		return "redirect:/profesores";
	}
	
	@GetMapping("/profesores/eliminar")
	public String eliminarProfesor(Long id) {
		profesorService.eliminarProfesorPorId(id);
		return "redirect:/profesores";
	}
}

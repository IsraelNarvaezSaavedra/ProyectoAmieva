package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.service.CursoService;

@Controller
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/cursos")
	public String mostrarCursos(Model model) {
		List<Curso> cursos = cursoService.listarCursos();
		model.addAttribute("cursos", cursos);
		return "pages/cursos/cursos";
	}
	
	@GetMapping("/cursos/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("curso", new Curso());
		return "pages/cursos/crear";
	}
	
	@PostMapping("/cursos/crear")
	public String crearCurso(Curso curso) {
		cursoService.guardarCurso(curso);
		return "redirect:/cursos";
	}
	
	@GetMapping("/cursos/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Curso curso = cursoService.getCursoPorId(id);
		if (curso == null) {
			return "redirect:/cursos";
		}
		model.addAttribute("curso", curso);
		return "pages/cursos/editar";
	}
	
	@PostMapping("/cursos/editar/{id}")
	public String editarCurso(@PathVariable Long id, Curso curso) {
		curso.setId(id);
		cursoService.guardarCurso(curso);
		
		return "redirect:/cursos";
	}
	
	@GetMapping("/cursos/eliminar")
	public String eliminarCurso(Long id) {
		//Verifica si hay alumnos en el curso
		if (cursoService.tieneAlumnosAsociados(id)) {
			return "redirect:/cursos?error=tiene-alumnos";
		}
		
		cursoService.eliminarCursoPorId(id);
		return "redirect:/cursos";
	}
}

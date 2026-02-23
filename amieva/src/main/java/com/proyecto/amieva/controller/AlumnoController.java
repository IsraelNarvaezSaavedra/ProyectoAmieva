package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.service.AlumnoService;
import com.proyecto.amieva.service.CursoService;

import org.springframework.ui.Model;

@Controller
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/alumnos")
	public String mostrarAlumnos(Model model) {
		List<Alumno> alumnos = alumnoService.listarAlumnos();
		model.addAttribute("alumnos", alumnos);
		return "pages/alumnos/alumnos";
	}
	
	@GetMapping("/alumnos/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Alumno alumno = alumnoService.getAlumnoPorId(id);
		if (alumno == null) {
			return "redirect:/alumnos";
		}
		model.addAttribute("alumno", alumno);
		model.addAttribute("cursosDisponibles", cursoService.listarCursos());
		return "pages/alumnos/editar";
	}
	
	@PostMapping("/alumnos/editar/{id}")
	public String editarAlumno(@PathVariable Long id, Alumno alumno, @RequestParam(required = false) Long idCurso) {
		alumno.setId(id);
		if (idCurso != null) {
			alumno.setCurso(cursoService.getCursoPorId(idCurso));
		}
		alumnoService.guardarAlumno(alumno);
		
		return "redirect:/alumnos";
	}
	
	@GetMapping("/alumnos/eliminar")
	public String eliminarAlumno(Long id) {
		alumnoService.eliminarAlumnoPorId(id);
		return "redirect:/alumnos";
	}
	
	@GetMapping("/alumnos/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("cursosDisponibles", cursoService.listarCursos());
		return "pages/alumnos/crear";
	}
	
	@PostMapping("/alumnos/crear")
	public String crearAlumno(Alumno alumno, 
							 @RequestParam(required = false) Long idCurso) {
		if (idCurso != null) {
			alumno.setCurso(cursoService.getCursoPorId(idCurso));
		}
		alumnoService.guardarAlumno(alumno);
		
		return "redirect:/alumnos";
	}
}

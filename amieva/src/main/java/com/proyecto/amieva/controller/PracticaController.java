package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.service.AlumnoService;
import com.proyecto.amieva.service.EmpresaService;
import com.proyecto.amieva.service.PracticaService;

@Controller
public class PracticaController {

	@Autowired
	private PracticaService practicaService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/practicas")
	public String mostrarPracticas(Model model) {
		List<Practica> practicas = practicaService.listarPracticas();
		model.addAttribute("practicas", practicas);
		return "pages/practicas/practicas";
	}
	
	@GetMapping("/practicas/crear")
	public String mostrarFormularioCrearPractica(Model model) {
		List<Alumno> alumnosDisponibles = alumnoService.listarAlumnos();
		List<Empresa> empresasDisponibles = empresaService.listarEmpresas();
		model.addAttribute("alumnosDisponibles", alumnosDisponibles);
		model.addAttribute("empresasDisponibles", empresasDisponibles);
		model.addAttribute("practica", new Practica());
		return "pages/practicas/crear";
	}
	
	@GetMapping("/practicas/editar")
	public String mostrarFormularioEditarPractica(Long id, Model model) {
		Practica practica = practicaService.getPracticaPorId(id);
		if (practica == null) {
            return "redirect:/practicas";
        }
		List<Alumno> alumnosDisponibles = alumnoService.listarAlumnos();
		List<Empresa> empresasDisponibles = empresaService.listarEmpresas();
		model.addAttribute("practica", practica);
		model.addAttribute("alumnosDisponibles", alumnosDisponibles);
		model.addAttribute("empresasDisponibles", empresasDisponibles);
		return "pages/practicas/editar";
	}
	
	@PostMapping("/practicas/editar/{id}")
	public String guardarCambiosPractica(@PathVariable Long id, Practica practica, 
										@RequestParam(required = false) Long alumnoId, 
										@RequestParam(required = false) Long empresaId) {
		practica.setId(id);
		if (alumnoId != null) {
			Alumno alumno = alumnoService.getAlumnoPorId(alumnoId);
			practica.setAlumno(alumno);
		}
		if (empresaId != null) {
			Empresa empresa = empresaService.getEmpresaPorId(empresaId);
			practica.setEmpresa(empresa);
		}
		practicaService.guardarPractica(practica);
		return "redirect:/practicas";
	}
	
	@PostMapping("/practicas/crear")
	public String crearPractica(Practica practica, 
								@RequestParam(required = false) Long alumnoId, 
								@RequestParam(required = false) Long empresaId) {
		if (alumnoId != null) {
			Alumno alumno = alumnoService.getAlumnoPorId(alumnoId);
			practica.setAlumno(alumno);
		}
		if (empresaId != null) {
			Empresa empresa = empresaService.getEmpresaPorId(empresaId);
			practica.setEmpresa(empresa);
		}
		practicaService.guardarPractica(practica);
		return "redirect:/practicas";
	}
	
	@GetMapping("/practicas/eliminar")
	public String eliminarPractica(Long id) {
		practicaService.eliminarPracticaPorId(id);
		return "redirect:/practicas";
	}
}


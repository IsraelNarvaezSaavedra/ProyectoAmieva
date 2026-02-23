package com.proyecto.amieva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.service.EmpresaService;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/empresas")
	public String mostrarEmpresas(Model model) {
		List<Empresa> empresas = empresaService.listarEmpresas();
		model.addAttribute("empresas", empresas);
		return "pages/empresas/empresas";
	}
	
	@GetMapping("/empresas/crear")
	public String mostrarFormularioCrear(Model model) {
		model.addAttribute("empresa", new Empresa());
		return "pages/empresas/crear";
	}
	
	@PostMapping("/empresas/crear")
	public String crearEmpresa(Empresa empresa) {
		empresaService.guardarEmpresa(empresa);
		return "redirect:/empresas";
	}
	
	@GetMapping("/empresas/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
		Empresa empresa = empresaService.getEmpresaPorId(id);
		if (empresa == null) {
			return "redirect:/empresas";
		}
		model.addAttribute("empresa", empresa);
		return "pages/empresas/editar";
	}
	
	@PostMapping("/empresas/editar/{id}")
	public String editarEmpresa(@PathVariable Long id, Empresa empresa) {
		empresa.setId(id);
		empresaService.guardarEmpresa(empresa);
		
		return "redirect:/empresas";
	}
	
	//Mira si hay emporesas con practicas asoiciadas
	@GetMapping("/empresas/eliminar")
	public String eliminarEmpresa(Long id) {
		
		if (empresaService.tienePracticasAsociadas(id)) {
			return "redirect:/empresas?error=tiene-practicas";
		}
		
		empresaService.eliminarEmpresaPorId(id);
		return "redirect:/empresas";
	}
}

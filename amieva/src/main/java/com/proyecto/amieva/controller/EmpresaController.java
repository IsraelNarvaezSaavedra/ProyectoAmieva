package com.proyecto.amieva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.amieva.service.EmpresaService;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/empresas")
	public String mostrarEmpresas() {
		return "pages/empresas";
	}
}

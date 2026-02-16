package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.repository.EmpresaRepository;


@Service
public class EmpresaService {
	private final EmpresaRepository empresaRepository;
	
	@Autowired
	public EmpresaService(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}
	
	//Listar todas las empresas
	public List<Empresa> listarEmpresas() {
		return empresaRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra la empresa con el id especificado
	public Empresa getEmpresaPorId(Long id, Empresa empresa) {
		return empresaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
	}
	
	//Eliminar empresa por id
	public void eliminarEmpresaPorId(Long id) {
		empresaRepository.deleteById(id);
	}
	
}

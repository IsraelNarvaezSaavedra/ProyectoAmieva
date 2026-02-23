package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.repository.EmpresaRepository;
import com.proyecto.amieva.repository.PracticaRepository;


@Service
public class EmpresaService {
	private final EmpresaRepository empresaRepository;
	private final PracticaRepository practicaRepository;
	
	@Autowired
	public EmpresaService(EmpresaRepository empresaRepository, PracticaRepository practicaRepository) {
		this.empresaRepository = empresaRepository;
		this.practicaRepository = practicaRepository;
	}
	
	//Listar todas las empresas
	@Transactional(readOnly = true)
	public List<Empresa> listarEmpresas() {
		return empresaRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra la empresa con el id especificado
	@Transactional(readOnly = true)
	public Empresa getEmpresaPorId(Long id) {
		return empresaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
	}
	
	//Eliminar empresa por id
	@Transactional
	public void eliminarEmpresaPorId(Long id) {
		empresaRepository.deleteById(id);
	}
	
	//Verificar si una empresa tiene prácticas asociadas
	@Transactional(readOnly = true)
	public boolean tienePracticasAsociadas(Long empresaId) {
		return practicaRepository.countByEmpresaId(empresaId) > 0;
	}
	
	//Obtener el número de prácticas asociadas a una empresa
	@Transactional(readOnly = true)
	public long contarPracticasAsociadas(Long empresaId) {
		return practicaRepository.countByEmpresaId(empresaId);
	}
	
	//Guardar empresa
	@Transactional
	public Empresa guardarEmpresa(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
}

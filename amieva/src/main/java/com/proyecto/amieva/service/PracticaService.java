package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.repository.PracticaRepository;

@Service
public class PracticaService {
	private final PracticaRepository practicaRepository;
	
	@Autowired
	public PracticaService(PracticaRepository practicaRepository) {
		this.practicaRepository = practicaRepository;
	}
	
	//Listar todas las practicas
	@Transactional(readOnly = true)
	public List<Practica> listarPracticas() {
		return practicaRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra la practica con el id especificado
	@Transactional(readOnly = true)
	public Practica getPracticaPorId(Long id) {
		return practicaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Practica no encontrada con ID: " + id));
	}
	
	//Guardar una practica nueva o actualizar una existente
	@Transactional
	public Practica guardarPractica(Practica practica) {
		return practicaRepository.save(practica);
	}
	
	//Eliminar practica por id
	@Transactional
	public void eliminarPracticaPorId(Long id) {
		practicaRepository.deleteById(id);
	}
}

package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.repository.PracticaRepository;

public class PracticaService {
	private final PracticaRepository practicaRepository;
	
	@Autowired
	public PracticaService(PracticaRepository practicaRepository) {
		this.practicaRepository = practicaRepository;
	}
	
	//Listar todas las practicas
	public List<Practica> listarPracticas() {
		return practicaRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra la practica con el id especificado
	public Practica getPracticaPorId(Long id, Practica practica) {
		return practicaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Practica no encontrada con ID: " + id));
	}
	
	//Eliminar practica por id
	public void eliminarPracticaPorId(Long id) {
		practicaRepository.deleteById(id);
	}
}

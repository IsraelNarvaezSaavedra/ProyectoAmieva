package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.amieva.entity.Profesor;
import com.proyecto.amieva.repository.ProfesorRepository;

@Service
public class ProfesorService {
private final ProfesorRepository profesorRepository;
	
	@Autowired
	public ProfesorService(ProfesorRepository profesorRepository) {
		this.profesorRepository = profesorRepository;
	}
	
	//Listar todos los profesores
	public List<Profesor> listarProfesores() {
		return profesorRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra el profesor con el id especificado
	public Profesor getProfesorPorId(Long id, Profesor profesor) {
		return profesorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
	}
	
	//Eliminar profesor por id
	public void eliminarProfesorPorId(Long id) {
		profesorRepository.deleteById(id);
	}
}

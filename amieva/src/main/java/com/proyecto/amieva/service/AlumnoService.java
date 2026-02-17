package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.entity.Alumno;

@Service
public class AlumnoService {
	private final AlumnoRepository alumnoRepository;
	
	@Autowired
	public AlumnoService(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}
	
	//Listar todos los alumnos
	public List<Alumno> listarAlumnos() {
		return alumnoRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra el alumno con el id especificado
	public Alumno getAlumnoPorId(Long id) {
		return alumnoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + id));
	}
	
	//Eliminar alimno por id
	public void eliminarAlumnoPorId(Long id) {
		alumnoRepository.deleteById(id);
	}
	
	
}

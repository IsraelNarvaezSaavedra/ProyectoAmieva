package com.proyecto.amieva.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.repository.CursoRepository;

@Service
public class CursoService {
	private final CursoRepository cursoRepository;
	
	@Autowired
	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}
	
	//Listar todos los cursos
	public List<Curso> listarCursos() {
		return cursoRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra el curso con el id especificado
	public Curso getCursoPorId(Long id, Curso curso) {
		return cursoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
	}
	
	//Eliminar curso por id
	public void eliminarCursoPorId(Long id) {
		cursoRepository.deleteById(id);
	}	
	
	//Guardar curso
	public Curso guardarCurso(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	
}

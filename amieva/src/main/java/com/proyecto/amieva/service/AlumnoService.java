package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.repository.CursoRepository;
import com.proyecto.amieva.repository.PracticaRepository;
import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Curso;

@Service
public class AlumnoService {
	private final AlumnoRepository alumnoRepository;
	private final PracticaRepository practicaRepository;
	private final CursoRepository cursoRepository;
	
	@Autowired
	public AlumnoService(AlumnoRepository alumnoRepository, PracticaRepository practicaRepository, CursoRepository cursoRepository) {
		this.alumnoRepository = alumnoRepository;
		this.practicaRepository = practicaRepository;
		this.cursoRepository = cursoRepository;
	}
	
	//Listar todos los alumnos
	@Transactional(readOnly = true)
	public List<Alumno> listarAlumnos() {
		return alumnoRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra el alumno con el id especificado
	@Transactional(readOnly = true)
	public Alumno getAlumnoPorId(Long id) {
		return alumnoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + id));
	}
	
	//Eliminar alumno por id
	//El .orElseThrow es necesario porque el findById devuelve un Optional
	/*
	 * Este metodo obtiene el id, mira si el alumno pertenece a un 
	 * curso (que siempre deberia pertenecer a uno) y si pertenece lo elinmina
	 * despues se borraria la practica en la que este porque sino da fallo
	 * y por ultimo se borra el alumno
	 */
	@Transactional
	public void eliminarAlumnoPorId(Long id) {
		// Obtener el alumno
		Alumno alumno = alumnoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + id));
		
		// Eliminar el alumno del curso si pertenece a alguno
		if (alumno.getCurso() != null) {
			Curso curso = alumno.getCurso();
			curso.removeAlumno(alumno);
			cursoRepository.save(curso);
		}
		
		// Eliminar todas las prácticas asociadas
		practicaRepository.deleteByAlumnoId(id);
		
		// Finalmente eliminar el alumno
		alumnoRepository.deleteById(id);
	}
	
	
}

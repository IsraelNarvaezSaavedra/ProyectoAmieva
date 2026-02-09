package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {	
	List<Alumno> findByNombre(String nombre);
}

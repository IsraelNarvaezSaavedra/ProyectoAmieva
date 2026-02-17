package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Practica;

public interface PracticaRepository extends JpaRepository<Practica, Long> {
	List<Practica> findByAlumno(Alumno alumno);

}

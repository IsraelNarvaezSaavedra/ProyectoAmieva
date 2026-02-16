package com.proyecto.amieva.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	List<Profesor> findByFechaInicio(LocalDate fechaInicio);
	List<Profesor> findByFechaFin(LocalDate fechaFin);
	List<Profesor> findByAlumno(String alumno);
	List<Profesor> findByEmpresa(String empresa);
}

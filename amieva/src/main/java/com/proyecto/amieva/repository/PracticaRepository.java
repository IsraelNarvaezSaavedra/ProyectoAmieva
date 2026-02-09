package com.proyecto.amieva.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Practica;

public interface PracticaRepository extends JpaRepository<Practica, Long> {
	List<Practica> findByFechaInicio(LocalDate fechaInicio);
	List<Practica> findByFechaFin(LocalDate fechaFin);
	List<Practica> findByAlumno(String alumno);
	List<Practica> findByEmpresa(String empresa);
}

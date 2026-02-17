package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	
	List<Profesor> findByNombre(String nombre);
}

package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	List<Curso> findByTitulo(String nombre);
}

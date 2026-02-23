package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Practica;

public interface PracticaRepository extends JpaRepository<Practica, Long> {
	List<Practica> findByAlumno(Alumno alumno);
	
	long countByEmpresaId(Long empresaId);
	
	@Modifying
	@Query("DELETE FROM Practica p WHERE p.alumno.id = :alumnoId")
	void deleteByAlumnoId(@Param("alumnoId") Long alumnoId);
	
	@Modifying
	@Query("DELETE FROM Practica p WHERE p.empresa.id = :empresaId")
	void deleteByEmpresaId(@Param("empresaId") Long empresaId);

}

package com.proyecto.amieva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.amieva.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	List<Empresa> findByNombre(String nombre);
}

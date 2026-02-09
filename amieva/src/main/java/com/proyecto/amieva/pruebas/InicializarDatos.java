package com.proyecto.amieva.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.github.javafaker.Faker;
import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.repository.CursoRepository;
import com.proyecto.amieva.repository.EmpresaRepository;
import com.proyecto.amieva.repository.PracticaRepository;

public class InicializarDatos implements CommandLineRunner {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private PracticaRepository practicaRepository;

	@Override
	 public void run(String... args) throws Exception {
		Faker faker = new Faker();
		
		for (int i = 0; i < 10; i++) {
			// Crear y guardar empresas ficticias
			Empresa empresa = new Empresa();
			empresa.setNombre(faker.company().name());
			empresa.setDireccion(faker.address().fullAddress());
			empresaRepository.save(empresa);
			
			// Crear y guardar cursos ficticios
						Curso curso = new Curso();
						curso.setNombre(faker.educator().course());
			
			for (int j = 0; j < 5; j++) {
						// Crear y guardar alumnos ficticios
						Alumno alumno = new Alumno();
						alumno.setNombre(faker.name().fullName());
						alumno.setApellidos(faker.name().lastName());
								curso.setAlumnos(alumno);
						alumnoRepository.save(alumno);
			}
			
			
			cursoRepository.save(curso);
			
			
		}
	}

}

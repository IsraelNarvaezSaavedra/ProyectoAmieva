package com.proyecto.amieva.pruebas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.github.javafaker.Faker;
import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.entity.Practica;
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
			empresa.setDescripcion(faker.company().catchPhrase());
			empresa.setTutorNombre(faker.name().fullName());
			empresa.setTutorEmail(faker.internet().emailAddress());
			empresaRepository.save(empresa);
			
			// Crear y guardar cursos ficticios
			Curso curso = new Curso();
			curso.setNombre(faker.educator().course());
			List<Alumno> alumnos = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
						// Crear y guardar alumnos ficticios
						Alumno alumno = new Alumno();
						alumno.setNombre(faker.name().fullName());
						alumno.setApellidos(faker.name().lastName());
						alumno.setEmail(faker.internet().emailAddress());
						alumno.setFechaNacimiento(faker.date().birthday().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
						alumnos.add(alumno);
						alumnoRepository.save(alumno);
						//Crear practicas ficticias
						Practica practica = new Practica();
						practica.setEmpresa(empresa);
						practica.setAlumno(null);
						practica.setComentario(faker.lorem().sentence());
						practica.setFechaFin(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
						practica.setFechaInicio(faker.date().past(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
						practicaRepository.save(practica);
			}		
			
			curso.setAlumnos(alumnos);
			cursoRepository.save(curso);
			
		}
	}

}

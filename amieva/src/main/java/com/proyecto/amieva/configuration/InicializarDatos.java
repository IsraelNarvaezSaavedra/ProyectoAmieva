package com.proyecto.amieva.configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.repository.CursoRepository;
import com.proyecto.amieva.repository.EmpresaRepository;
import com.proyecto.amieva.repository.PracticaRepository;

@Component
@Transactional
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
	@Transactional
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		
		for (int i = 0; i < 10; i++) {
            //Empresa
            Empresa empresa = new Empresa();
            empresa.setNombre(faker.company().name());
            empresa.setDescripcion(faker.company().catchPhrase());
            empresa.setTutorNombre(faker.name().fullName());
            empresa.setTutorEmail(faker.internet().emailAddress());
            empresa = empresaRepository.save(empresa);

            //Curso
            Curso curso = new Curso();
            curso.setNombre(faker.educator().course());

            List<Alumno> alumnosDelCurso = new ArrayList<>();
            
            for (int j = 0; j < 5; j++) {
                //Alumno
                Alumno alumno = new Alumno();
                alumno.setNombre(faker.name().firstName());
                alumno.setApellidos(faker.name().lastName());
                alumno.setEmail(faker.internet().emailAddress());
                alumno.setFechaNacimiento(
                    faker.date().birthday(18, 30).toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate()
                );

                alumno.setCurso(curso);
                alumno = alumnoRepository.save(alumno); 
                alumnosDelCurso.add(alumno);

                //Practica
                Practica practica = new Practica();
                practica.setAlumno(alumno);               
                practica.setEmpresa(empresa);
                practica.setComentario(faker.lorem().sentence(10));
                
                LocalDate inicio = faker.date().past(60, TimeUnit.DAYS)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                practica.setFechaInicio(inicio);
                practica.setFechaFin(inicio.plusDays(faker.number().numberBetween(30, 180)));

                practicaRepository.save(practica);
            }

            curso.setAlumnos(alumnosDelCurso);
            curso = cursoRepository.save(curso);
        }
	}

}

package com.proyecto.amieva.configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;
import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Curso;
import com.proyecto.amieva.entity.Empresa;
import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.entity.Profesor;
import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.repository.CursoRepository;
import com.proyecto.amieva.repository.EmpresaRepository;
import com.proyecto.amieva.repository.PracticaRepository;
import com.proyecto.amieva.repository.ProfesorRepository;

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
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//Si la base de datos ya tiene profesores no inicializa los datos para que no se sobre escriban
		if (profesorRepository.count() > 0) {
			return; 
		}
		
		Faker faker = new Faker();
		
		//Usuarios de prueba con atributos conocidos para facilitar el testing
		crearProfesorPrueba("Juan", "García", "juan@email.com", "juan123", true);
		crearProfesorPrueba("María", "López", "maria@email.com", "maria123", false);
		crearProfesorPrueba("Carlos", "Rodríguez", "carlos@email.com", "carlos123", false);
		
		
		for (int i = 0; i < 7; i++) {
			//Profesor
			Profesor profesor = new Profesor();
			profesor.setNombre(faker.name().firstName());
			profesor.setApellidos(faker.name().lastName());
			profesor.setEmail(faker.internet().emailAddress());
			String contrasenaPlana = faker.internet().password(8, 16);
			profesor.setContrasena(passwordEncoder.encode(contrasenaPlana));
			profesor.setDirectiva(faker.bool().bool());
			profesorRepository.save(profesor);
			
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

	private void crearProfesorPrueba(String nombre, String apellidos, String email, String contrasena, boolean esDirectivo) {
		Profesor profesor = new Profesor();
		profesor.setNombre(nombre);
		profesor.setApellidos(apellidos);
		profesor.setEmail(email);
		profesor.setContrasena(passwordEncoder.encode(contrasena));
		profesor.setDirectiva(esDirectivo);
		profesorRepository.save(profesor);
	}

}

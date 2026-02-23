package com.proyecto.amieva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.amieva.entity.Alumno;
import com.proyecto.amieva.entity.Practica;
import com.proyecto.amieva.repository.AlumnoRepository;
import com.proyecto.amieva.repository.PracticaRepository;

@Service
public class PracticaService {
	private final PracticaRepository practicaRepository;
	
	@Autowired
    private EmailService emailService;

    @Autowired
    private AlumnoRepository alumnoRepository;
    
	@Autowired
	public PracticaService(PracticaRepository practicaRepository) {
		this.practicaRepository = practicaRepository;
	}
	
	//Listar todas las practicas
	@Transactional(readOnly = true)
	public List<Practica> listarPracticas() {
		return practicaRepository.findAll();
	}
	
	//El .orElseThrow es necesario porque el findById devuelve un Optional, y el orElseThrow se encarga de lanzar una excepcion si no se encuentra la practica con el id especificado
	@Transactional(readOnly = true)
	public Practica getPracticaPorId(Long id) {
		return practicaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Practica no encontrada con ID: " + id));
	}
	
	//Guardar una practica nueva o actualizar una existente
	@Transactional
	public Practica guardarPractica(Practica practica) {
		return practicaRepository.save(practica);
	}
	
	//Eliminar practica por id
	@Transactional
	public void eliminarPracticaPorId(Long id) {
		practicaRepository.deleteById(id);
	}
	
	@Transactional
    public Practica crearPractica(Practica practica) {
        Practica nueva = practicaRepository.save(practica);

        //Carga el alumno (para obtener su email)
        Alumno alumno = alumnoRepository.findById(practica.getAlumno().getId())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        //Prepara el email
        String subject = "Nueva práctica asignada en " + practica.getEmpresa().getNombre();
        String body = """
            Hola %s %s,

            Se te ha asignado una práctica en la empresa **%s**.

            Fechas: desde %s hasta %s.

            Comentarios: %s

            ¡Éxito en tu formación!
            """.formatted(
                alumno.getNombre(), alumno.getApellidos(),
                practica.getEmpresa().getNombre(),
                practica.getFechaInicio(), practica.getFechaFin(),
                practica.getComentario() != null ? practica.getComentario() : "Ninguno"
            );

        //Paras enviar el email
        emailService.enviarEmail(alumno.getEmail(), subject, body);

        return nueva;
    }
}

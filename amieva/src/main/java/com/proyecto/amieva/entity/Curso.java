package com.proyecto.amieva.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 50/*, unique = true*/)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
	
	@OneToMany (mappedBy = "curso")
    private List<Alumno> alumnos = new ArrayList<>();
	
	/*########################### GETTER Y SETTERS ###########################*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	/*########################### FUNCION BORRAR Y CREAR ALUMNO ###########################*/
	
	public void addAlumno(Alumno alumno) {
	    alumnos.add(alumno);
	    alumno.setCurso(this);
	}

	public void removeAlumno(Alumno alumno) {
	    alumnos.remove(alumno);
	    alumno.setCurso(null);
	}
}

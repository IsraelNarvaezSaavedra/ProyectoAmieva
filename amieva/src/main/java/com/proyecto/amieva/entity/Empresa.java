package com.proyecto.amieva.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "empresas")
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
	
	@Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
    @Column(name = "descripcion")
    private String descripcion;
	
	@NotBlank(message = "El nombre del tutor laboral es obligatorio")
	@Column(name = "tutor_nombre", nullable = false, length = 100)
	private String tutorNombre;

	@NotBlank(message = "El email del tutor es obligatorio")
	@Email
	@Column(name = "tutor_email", nullable = false, unique = true, length = 120)
	private String tutorEmail;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTutorNombre() {
		return tutorNombre;
	}

	public void setTutorNombre(String tutorNombre) {
		this.tutorNombre = tutorNombre;
	}

	public String getTutorEmail() {
		return tutorEmail;
	}

	public void setTutorEmail(String tutorEmail) {
		this.tutorEmail = tutorEmail;
	}
	
	
}

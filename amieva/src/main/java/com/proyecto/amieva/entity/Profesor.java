package com.proyecto.amieva.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "profesores")
public class Profesor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
	
	@Column(nullable = false, length = 70)
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellidos;
	
	@Email(message = "Correo electrónico no válido")
    @Column(nullable = false, unique = true, length = 120)
    private String email;
	
	@Column(nullable = false)
	@NotBlank( message = "La contraseña no puede estar vacía")
	private String contrasena;
	
	@Column(name = "es_directiva")
	private boolean es_directivo;

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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}

	public boolean isDirectiva() {
		return es_directivo;
	}

	public void setDirectiva(boolean tipo) {
		this.es_directivo = tipo;
	}
	
	
}

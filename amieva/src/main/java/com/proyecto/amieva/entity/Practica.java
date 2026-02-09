package com.proyecto.amieva.entity;


import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "practicas")
public class Practica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)          
	@JoinColumn(name = "alumno_id")     
	private Alumno alumno;
	
	@ManyToOne(fetch = FetchType.LAZY)          
	@JoinColumn(name = "empresa_id")     
	private Empresa empresa;
	
	@NotNull(message = "La fecha de inicio es obligatoria")
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;
	
	@NotNull(message = "La fecha final es obligatoria")
	@Column(name = "fecha_fin")
	private LocalDate fechaFin;
	
	@Size(max = 200, message = "El comentario tiene como máximo 200 caracteres")
    @Column(name = "comentario")
	private String comentario;

	/*########################### GETTER Y SETTERS ###########################*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}

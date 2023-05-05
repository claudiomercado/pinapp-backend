package com.pinapp.backend.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private int edad;
	
	@Column
	private LocalDate fecha_nacimiento;

	public Cliente(String nombre, String apellido, int edad, LocalDate fecha_nacimiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
}

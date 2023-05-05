package com.pinapp.backend.app.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClienteDTO {
	
	private String nombre;
	private String apellido;
	private int edad;
	private LocalDate fecha_nacimiento;
	private LocalDate fecha_posible_muerte;
}

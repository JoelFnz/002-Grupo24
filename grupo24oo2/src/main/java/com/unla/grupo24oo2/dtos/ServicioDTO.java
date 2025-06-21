package com.unla.grupo24oo2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class ServicioDTO {
	@NotBlank
	private String nombreServicio;
	
	@NotBlank
	private String descripcion;
	
	@NotNull(message = "El DNI del empleado es obligatorio")
    private Integer dniEmpleado;
}


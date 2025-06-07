package com.unla.grupo24oo2.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class ServicioDTO {
	@NotBlank
	private String nombreServicio;
	
	@NotBlank
	private String descripcion;
}

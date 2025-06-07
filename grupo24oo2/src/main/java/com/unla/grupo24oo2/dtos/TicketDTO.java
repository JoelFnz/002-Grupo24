package com.unla.grupo24oo2.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class TicketDTO {
	@NotBlank
	private String nombreServicio;
	
	@Min(value = 1, message = "El número de dni debe ser válido.")
	private int dniCliente;
}

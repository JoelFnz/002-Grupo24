package com.unla.grupo24oo2.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class IntervencionDTO {
	@NotNull
	@Size(min=1, max=300)
	private String descripcion;
	
	@NotNull
	@Size(min=1, max=300)
	private String nroEmpleado;
	
	@NotNull
	@Size(min=1, max=300)
	private String nroTicket;
	
	private String nombreEmpleado;
}

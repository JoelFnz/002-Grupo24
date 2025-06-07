package com.unla.grupo24oo2.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class TicketResponseDTO {
	private String nroTicket;
	private LocalDateTime fechaYHoraDeCreacion;
	private LocalDateTime fechaYHoraDeCaducidad;
	private int dniCliente;
}

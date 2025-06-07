package com.unla.grupo24oo2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;


public interface ITicketService {
	TicketResponseDTO crearTicket(TicketDTO ticketDTO); 
	
	Page<TicketResponseDTO> obtenerTicketsPorFiltro(TicketFilterDTO filter, int dniCliente , Pageable pageable);
}

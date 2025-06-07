package com.unla.grupo24oo2.services;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;


public interface ITicketService {
	TicketResponseDTO crearTicket(TicketDTO ticketDTO); 
}

package com.unla.grupo24oo2.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;


public interface ITicketService {
	TicketResponseDTO crearTicket(TicketDTO ticketDTO); 
	
	Page<TicketResponseDTO> obtenerTicketsPorFiltro(TicketFilterDTO filter, int dniCliente , Pageable pageable);
		
	TicketResponseDTO obtenerTicketPorNroTicket(String nroTicket);

	List<TicketResponseDTO> obtenerTicketsPorEstado(TipoDeEstado estado);

	void asignarTicketAEmpleado(String nroTicket, int dniEmpleado);

	void finalizarTicket(String nroTicket);

	List<TicketResponseDTO> obtenerTicketsAsignados(int dniEmpleado);

	List<TicketResponseDTO> obtenerTicketsFinalizados(int dniEmpleado);
	
	List<TicketResponseDTO> obtenerTicketsActivos();
}

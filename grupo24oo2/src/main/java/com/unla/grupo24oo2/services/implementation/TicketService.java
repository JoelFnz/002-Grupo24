package com.unla.grupo24oo2.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Servicio;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.IServicioRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;
import com.unla.grupo24oo2.services.ITicketService;

@Service
public class TicketService implements ITicketService {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private ITicketRepository ticketRepository;
	
	@Autowired
	private IServicioRepository servicioRepository;

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public TicketResponseDTO crearTicket(TicketDTO ticketDTO) {
		Servicio servicio = servicioRepository.findByNombreServicio(ticketDTO.getNombreServicio())
				.orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

		Cliente cliente = clienteRepository.findByDni(ticketDTO.getDniCliente())
				.orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

		Ticket ticket = new Ticket(cliente, servicio);
		ticket = ticketRepository.save(ticket);

		String nroTicket = "TKT-" + ticket.getIdTicket() + "-SVC-" + servicio.getIdServicio();
		ticket.setNroTicket(nroTicket);
		ticket = ticketRepository.save(ticket);
		
		TicketResponseDTO ticketResponse = modelMapper.map(ticket, TicketResponseDTO.class);
		ticketResponse.setDniCliente(ticketDTO.getDniCliente());
		
		return ticketResponse;
	}

}

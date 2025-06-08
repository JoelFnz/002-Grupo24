package com.unla.grupo24oo2.services.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Estado;
import com.unla.grupo24oo2.entities.Servicio;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;
import com.unla.grupo24oo2.exceptions.NoRegisterFoundException;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IServicioRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;
import com.unla.grupo24oo2.services.IMailService;
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
	
	@Autowired
	private IEmpleadoRepository empleadoRepository;
	
	@Override
	public TicketResponseDTO crearTicket(TicketDTO ticketDTO) {
		Servicio servicio = servicioRepository.findByNombreServicio(ticketDTO.getNombreServicio())
				.orElseThrow(() -> new NoRegisterFoundException("Servicio no encontrado"));

		Cliente cliente = clienteRepository.findByDni(ticketDTO.getDniCliente())
				.orElseThrow(() -> new NoRegisterFoundException("Cliente no encontrado"));
		
		Ticket ticket = new Ticket(cliente, servicio);
		Estado estado = new Estado();
		estado.setEstado(TipoDeEstado.INICIADO);
		estado.setTicket(ticket);
		ticket.setEstado(estado);
		
		ticket = ticketRepository.save(ticket);

		String nroTicket = "TKT-" + ticket.getIdTicket() + "-SVC-" + servicio.getIdServicio();
		ticket.setNroTicket(nroTicket);
		ticket = ticketRepository.save(ticket);
		
		TicketResponseDTO ticketResponse = modelMapper.map(ticket, TicketResponseDTO.class);
		ticketResponse.setDniCliente(ticketDTO.getDniCliente());
		
		return ticketResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TicketResponseDTO> obtenerTicketsPorFiltro(TicketFilterDTO filter, int dniCliente, Pageable pageable) {
	    Cliente cliente = clienteRepository.findByDni(dniCliente)
	            .orElseThrow(() -> new NoRegisterFoundException("Cliente no encontrado"));
	    
	    Page<Ticket> tickets = ticketRepository.findByClienteAndFilter(cliente, filter, pageable);
	    
	    return tickets.map(ticket -> {
	    	TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	    	dto.setEstado(ticket.getEstado().getEstado().toString());
	    	return dto;
	    });
	}

	@Override
	public Page<TicketResponseDTO> obtenerTicketsPorNroEmpleado(String nroEmpleado, Pageable pageable) {
		Empleado empleado = empleadoRepository.findByNroEmpleado(nroEmpleado)
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		
		Page<Ticket> tickets = ticketRepository.findTicketAsociadosByEmpleado(empleado, pageable);
		
		return tickets.map(ticket -> {
	    	TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	    	dto.setEstado(ticket.getEstado().getEstado().toString());
	    	return dto;
	    });
	}

}

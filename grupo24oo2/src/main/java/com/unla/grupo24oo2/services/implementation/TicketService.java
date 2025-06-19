package com.unla.grupo24oo2.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

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
	public TicketResponseDTO obtenerTicketPorNroTicket(String nroTicket) {
		Ticket ticket = ticketRepository.findByNroTicket(nroTicket)
				.orElseThrow(() -> new NoRegisterFoundException("Ticket no encontrado"));
		
		TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
		dto.setEstado(ticket.getEstado().getEstado().toString());
		
		return dto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> obtenerTicketsPorEstado(TipoDeEstado estado) {
	    List<Ticket> tickets = ticketRepository.findByEstado_Estado(estado);

	    return tickets.stream()
	        .map(ticket -> {
	            TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	            dto.setEstado(ticket.getEstado().getEstado().name());
	            return dto;
	        })
	        .toList();
	}

	@Override
	@Transactional
	public void asignarTicketAEmpleado(String nroTicket, int dniEmpleado) {
	    Ticket ticket = ticketRepository.findByNroTicket(nroTicket)
	        .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

	    Empleado empleado = empleadoRepository.findByDni(dniEmpleado)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

	    // Evitar duplicados si ya está asignado
	    if (!ticket.getEmpleadosAsignados().contains(empleado)) {
	        ticket.getEmpleadosAsignados().add(empleado);
	    }

	    // Actualiza o crea estado
	    Estado estado = ticket.getEstado();
	    if (estado == null) {
	        estado = new Estado();
	        estado.setTicket(ticket);
	    }
	    estado.setEstado(TipoDeEstado.INTERVENIDO);
	    ticket.setEstado(estado);

	    ticketRepository.save(ticket);
	}


	@Override
	@Transactional
	public void finalizarTicket(String nroTicket) {
	    Ticket ticket = ticketRepository.findByNroTicket(nroTicket)
	        .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

	    Estado estado = ticket.getEstado();
	    if (estado == null) {
	        estado = new Estado();
	        estado.setTicket(ticket);
	    }

	    estado.setEstado(TipoDeEstado.FINALIZADO);
	    ticket.setEstado(estado);

	    ticketRepository.save(ticket);
	}


	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> obtenerTicketsAsignados(int dniEmpleado) {
	    List<Ticket> tickets = ticketRepository
	        .findByEmpleadosAsignados_DniAndEstado_Estado(dniEmpleado, TipoDeEstado.INTERVENIDO);

	    return tickets.stream()
	        .map(ticket -> {
	            TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	            dto.setEstado(ticket.getEstado().getEstado().name());
	            dto.setNombreServicio(ticket.getServicioSolicitado().getNombreServicio()); 
	            return dto;
	        })
	        .toList();
	}


	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> obtenerTicketsFinalizados(int dniEmpleado) {
	    List<Ticket> tickets = ticketRepository
	        .findByEmpleadosAsignados_DniAndEstado_EstadoOrderByFechaYHoraDeCreacionDesc(dniEmpleado, TipoDeEstado.FINALIZADO);

	    return tickets.stream()
	        .map(ticket -> {
	            TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	            dto.setEstado(ticket.getEstado().getEstado().name());
	            return dto;
	        })
	        .toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TicketResponseDTO> obtenerTicketsActivos() {
	    List<Ticket> tickets = ticketRepository.findByEstado_Estado(TipoDeEstado.INICIADO);

	    return tickets.stream()
	        .map(ticket -> {
	            TicketResponseDTO dto = modelMapper.map(ticket, TicketResponseDTO.class);
	            dto.setEstado(ticket.getEstado().getEstado().name());
	            dto.setNombreServicio(ticket.getServicioSolicitado().getNombreServicio());
	            return dto;
	        })
	        .toList();
	}


}

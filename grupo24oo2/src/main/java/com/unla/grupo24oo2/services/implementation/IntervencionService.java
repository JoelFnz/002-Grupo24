package com.unla.grupo24oo2.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.IntervencionDTO;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Intervencion;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IIntervencionRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;
import com.unla.grupo24oo2.services.IIntervencionService;

@Service
public class IntervencionService implements IIntervencionService{

	@Autowired
	private IIntervencionRepository intervencionRepository;
	
	@Autowired
	private IEmpleadoRepository empleadoRepository;
	
	@Autowired
	private ITicketRepository ticketRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public IntervencionDTO crearIntervencion(IntervencionDTO intervencion) {
		Empleado empleado = empleadoRepository.findByNroEmpleado(intervencion.getNroEmpleado())
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		
		Ticket ticket = ticketRepository.findByNroTicket(intervencion.getNroTicket())
				.orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
		
		if(ticket.getEstado().getEstado() == TipoDeEstado.FINALIZADO)
			throw new RuntimeException("Ticket finalizado. No es posible intervenirlo.");
		
		Intervencion intervencionEntity = new Intervencion();
		
		intervencionEntity.setDescripcion(intervencion.getDescripcion());
		intervencionEntity.setEmpleado(empleado);
		intervencionEntity.setTicket(ticket);
		
		intervencionRepository.save(intervencionEntity);
		
		if(ticket.getEstado().getEstado() == TipoDeEstado.INICIADO) {
			ticket.getEstado().setEstado(TipoDeEstado.INTERVENIDO);
			ticketRepository.save(ticket);
		}
		
		return intervencion;
	}

}

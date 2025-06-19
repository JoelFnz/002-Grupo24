package com.unla.grupo24oo2.services.implementation;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.IntervencionDTO;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Intervencion;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;
import com.unla.grupo24oo2.exceptions.NoRegisterFoundException;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IIntervencionRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;
import com.unla.grupo24oo2.services.IIntervencionService;

import jakarta.persistence.EntityNotFoundException;

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
				.orElseThrow(() -> new NoRegisterFoundException("Empleado no encontrado"));
		
		Ticket ticket = ticketRepository.findByNroTicket(intervencion.getNroTicket())
				.orElseThrow(() -> new NoRegisterFoundException("Ticket no encontrado"));
		
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

	@Override
	public Page<IntervencionDTO> obtenerIntervencionesPorNroTicket(String nroTicket, Pageable pageable) {
		Page<Intervencion> intervenciones = intervencionRepository.findByNroTicket(nroTicket, pageable);
		
		return intervenciones.map(intervencion -> {
			IntervencionDTO dto = modelMapper.map(intervencion, IntervencionDTO.class);
			dto.setNombreEmpleado(intervencion.getEmpleado().getNombre());
			return dto;
		});
	}
	
    public String obtenerNroEmpleadoPorDni(int dniEmpleado) {
        Optional<Empleado> empleadoOpt = empleadoRepository.findByDni(dniEmpleado);
        if (empleadoOpt.isPresent()) {
            return empleadoOpt.get().getNroEmpleado();
        } else {
            throw new EntityNotFoundException("Empleado con DNI " + dniEmpleado + " no encontrado");
        }
    }
}

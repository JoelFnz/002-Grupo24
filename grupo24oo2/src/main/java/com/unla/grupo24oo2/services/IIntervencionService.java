package com.unla.grupo24oo2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.grupo24oo2.dtos.IntervencionDTO;

public interface IIntervencionService {
	IntervencionDTO crearIntervencion(IntervencionDTO intervencion);
	
	Page<IntervencionDTO> obtenerIntervencionesPorNroTicket(String nroTicket, Pageable pageable);
	
	String obtenerNroEmpleadoPorDni(int dniEmpleado);
}

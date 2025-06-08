package com.unla.grupo24oo2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Intervencion;
import com.unla.grupo24oo2.entities.Ticket;

public interface IIntervencionRepository extends JpaRepository<Intervencion, Long>{
	List<Intervencion> findByTicketAndEmpleado(Ticket ticket, Empleado empleado);
}

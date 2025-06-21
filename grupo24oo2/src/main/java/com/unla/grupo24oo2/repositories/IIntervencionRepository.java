package com.unla.grupo24oo2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Intervencion;
import com.unla.grupo24oo2.entities.Ticket;

public interface IIntervencionRepository extends JpaRepository<Intervencion, Long>{
	List<Intervencion> findByTicketAndEmpleado(Ticket ticket, Empleado empleado);
	
	@Query("SELECT i FROM Intervencion i WHERE i.ticket.nroTicket = :nroTicket")
	Page<Intervencion> findByNroTicket(@Param("nroTicket") String nroTicket, Pageable pageable);
}

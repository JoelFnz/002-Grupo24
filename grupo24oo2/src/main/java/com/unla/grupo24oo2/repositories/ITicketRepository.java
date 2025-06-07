package com.unla.grupo24oo2.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Ticket;

public interface ITicketRepository  extends JpaRepository<Ticket, Long>{
	public Ticket findByIdTicket(long idTicket);
	
	public Ticket findByNroTicket(String nroTicket);
	
	public Optional<Ticket> findByCliente(Cliente cliente);
	
	public Page<Ticket> findByCliente(Cliente cliente, Pageable pageable);
	
	public Page<Ticket> findByClienteAndFechaYHoraDeCreacion(Cliente cliente, LocalDateTime fechaYHoraDeCreacion, Pageable pageable);
}

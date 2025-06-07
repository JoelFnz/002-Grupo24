package com.unla.grupo24oo2.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Ticket;

public interface ITicketRepository  extends JpaRepository<Ticket, Long>{
	public Ticket findByIdTicket(long idTicket);
	
	public Ticket findByNroTicket(String nroTicket);
	
	public Optional<Ticket> findByCliente(Cliente cliente);
	
	public Page<Ticket> findByCliente(Cliente cliente, Pageable pageable);
	
	public Page<Ticket> findByClienteAndFechaYHoraDeCreacion(Cliente cliente, LocalDateTime fechaYHoraDeCreacion, Pageable pageable);
	
	@Query("SELECT t FROM Ticket t WHERE " +
			"(:#{#filter.fechaCreacion} IS NULL OR t.fechaYHoraDeCreacion >= :#{#filter.fechaCreacion}) " +
			"AND (:#{#filter.fechaCaducidad} IS NULL OR t.fechaYHoraDeCaducidad <= :#{#filter.fechaCaducidad}) " +
			"AND (:#{#filter.nroTicket} IS NULL OR t.nroTicket LIKE %:#{#filter.nroTicket}%) " +
			"AND t.cliente = :cliente")
	Page<Ticket> findByClienteAndFilter(@Param("cliente") Cliente cliente, 
			@Param("filter") TicketFilterDTO filter, 
			Pageable pageable);
}

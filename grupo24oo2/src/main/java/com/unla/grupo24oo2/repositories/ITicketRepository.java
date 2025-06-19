package com.unla.grupo24oo2.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;

public interface ITicketRepository  extends JpaRepository<Ticket, Long>{
	public Optional<Ticket> findByIdTicket(long idTicket);

	public Optional<Ticket> findByNroTicket(String nroTicket);

	public Optional<Ticket> findByCliente(Cliente cliente);

	public Page<Ticket> findByCliente(Cliente cliente, Pageable pageable);

	public Page<Ticket> findByClienteAndFechaYHoraDeCreacion(Cliente cliente, LocalDateTime fechaYHoraDeCreacion, Pageable pageable);

	@Query("SELECT t FROM Ticket t WHERE " +
	        "(:#{#filter.desdeFechaCreacion} IS NULL OR t.fechaYHoraDeCreacion >= :#{#filter.desdeFechaCreacion}) " +
	        "AND (:#{#filter.hastaFechaCreacion} IS NULL OR t.fechaYHoraDeCreacion < :#{#filter.hastaFechaCreacion}) " +  
	        "AND (:#{#filter.nroTicket} IS NULL OR t.nroTicket LIKE %:#{#filter.nroTicket}%) " +
	        "AND t.cliente = :cliente")
	Page<Ticket> findByClienteAndFilter(@Param("cliente") Cliente cliente, 
			@Param("filter") TicketFilterDTO filter, 
			Pageable pageable);

	@Query("SELECT t FROM Ticket t JOIN t.empleadosAsignados e " +
			"WHERE e = :empleado AND t.estado.estado = :estado")
	List<Ticket> findTicketIntervenidosByEmpleado(@Param("empleado") Empleado empleado,
			@Param("estado") TipoDeEstado estado);


	// Nuevo metodo para eliminar tickets de un cliente
	void deleteByCliente(Cliente cliente);


	List<Ticket> findByEmpleadosAsignados_DniAndEstado_Estado(int dni, TipoDeEstado estado);

	List<Ticket> findByEmpleadosAsignados_DniAndEstado_EstadoOrderByFechaYHoraDeCreacionDesc(
			int dni,
			TipoDeEstado estado
			);

	List<Ticket> findByEstado_Estado(TipoDeEstado estado);

}

package com.unla.grupo24oo2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="intervencion")
@Setter @Getter @NoArgsConstructor
public class Intervencion {
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idIntervencion;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name="idTicket")
	private Ticket ticket;
}

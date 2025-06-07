package com.unla.grupo24oo2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="estado")
@Setter @Getter @NoArgsConstructor
public class Estado {
	@Id
	@Setter(AccessLevel.PROTECTED)
	private long idEstado;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoDeEstado estado;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="idEstado", referencedColumnName="idTicket", nullable=false)
	private Ticket ticket;
	
}

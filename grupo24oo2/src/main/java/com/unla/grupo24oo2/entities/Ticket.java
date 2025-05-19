package com.unla.grupo24oo2.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ticket")
@Setter @Getter @NoArgsConstructor
public class Ticket {
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTicket;
	
	@Column(unique=true)
	private String nroTicket;
	
	@CreationTimestamp
	private LocalDateTime fechaYHoraDeCreacion;
	
	private LocalDateTime fechaYHoraDeCaducidad;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(
			name="ticket_has_empleado",
			joinColumns = @JoinColumn(name="idTicket"),
			inverseJoinColumns = @JoinColumn(name="idUsuario")
	)
	private List<Empleado> empleadosAsignados;
	
	@OneToMany(mappedBy="ticket", cascade=CascadeType.ALL)
	private List<Intervencion> intervenciones;
	
	@ManyToOne
	@JoinColumn(name="idServicio")
	private Servicio servicioSolicitado;
	
	@OneToOne(mappedBy="ticket", cascade=CascadeType.ALL)
	private Estado estado;
}

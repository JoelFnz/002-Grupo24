package com.unla.grupo24oo2.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="servicio")
@Setter @Getter @NoArgsConstructor
public class Servicio {
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idServicio;
	
	@Column(unique = true)
	private String nombreServicio;
	
	private String descripcion;
	
	@ManyToMany
	@JoinTable(
		name="servicio_has_empleado",
		joinColumns = @JoinColumn(name="idServicio"),
		inverseJoinColumns = @JoinColumn(name="idUsuario")
	)
	private List<Empleado> empleados;
	
	@OneToMany(mappedBy="servicioSolicitado")
	private List<Ticket> tickets;
}

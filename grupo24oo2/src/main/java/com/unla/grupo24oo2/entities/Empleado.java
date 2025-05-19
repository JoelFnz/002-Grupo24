package com.unla.grupo24oo2.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="empleado")
@Setter @Getter @NoArgsConstructor
public class Empleado extends Usuario{
	@Column(unique=true)
	private String nroEmpleado;
	
	@CreationTimestamp
	private LocalDate fechaDeIngreso;
	
	@ManyToMany(mappedBy="empleadosAsignados", fetch=FetchType.LAZY)
	private List<Ticket> ticketsAsignados;
	
	@OneToMany(mappedBy="empleado", fetch=FetchType.LAZY)
	private List<Intervencion> intervencionesRealizadas;
	
	@ManyToMany(mappedBy="empleados", fetch=FetchType.LAZY)
	private List<Servicio> servicios;
	
	public Empleado(String nroEmpleado, String nombre, String contrasenia, Domicilio domicilio, Contacto contacto) {
		super(nombre, contrasenia, domicilio, contacto);
		this.nroEmpleado = nroEmpleado;
	}
}

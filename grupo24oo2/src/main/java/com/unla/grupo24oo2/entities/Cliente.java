package com.unla.grupo24oo2.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unla.grupo24oo2.entities.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente")
@Getter @Setter @NoArgsConstructor
public class Cliente extends Usuario{
	@Column(unique=true)
	private int dni;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
	@JsonIgnore  // Evita la recursión infinita con Ticket
	private List<Ticket> ticketsAsociados;
	
	public Cliente(int dni, String nombre, String contrasenia, Domicilio domicilio, Contacto contacto) {
		super(nombre, contrasenia, domicilio, contacto, RoleType.CLIENTE);  // <-- asignar el rol aquí
		this.dni = dni;
	}
}

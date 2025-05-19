package com.unla.grupo24oo2.entities;

import java.util.List;

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
	private List<Ticket> ticketsAsociados;
	
	public Cliente(int dni, String nombre, String contrasenia, Domicilio domicilio, Contacto contacto) {
		super(nombre, contrasenia, domicilio, contacto);
		this.dni = dni;
	}
}

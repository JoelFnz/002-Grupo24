package com.unla.grupo24oo2.entities;

import jakarta.persistence.Entity;
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
@Table(name="contacto")
@Getter @Setter @NoArgsConstructor
public class Contacto {
	@Id
	@Setter(AccessLevel.PROTECTED)
	private long idContacto;
	
	private String telefono;
	
	private String email;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="idContacto", referencedColumnName="idUsuario", nullable=false)
	private Usuario usuario;
}

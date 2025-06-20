package com.unla.grupo24oo2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="domicilio")
@Getter @Setter @NoArgsConstructor
public class Domicilio {
	@Id
	@Setter(AccessLevel.PROTECTED)
	private long idDomicilio;
	private String calle;
	private String localidad;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="idDomicilio", referencedColumnName="idUsuario", nullable=false)
	@JsonIgnore // Evita la recursión infinita

	private Usuario usuario;
}

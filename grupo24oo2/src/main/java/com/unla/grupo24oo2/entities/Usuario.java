package com.unla.grupo24oo2.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario")
@Inheritance(strategy=InheritanceType.JOINED) //hijas en tablas separadas, unidas a la madre por el id.
@Getter @Setter @NoArgsConstructor
public abstract class Usuario {
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long idUsuario;
	
	protected String nombre;
	
	protected String contrasenia;
	
	@OneToOne(mappedBy="usuario", cascade=CascadeType.ALL)
	protected Domicilio domicilio;
	
	@OneToOne(mappedBy="usuario", cascade=CascadeType.ALL)
	protected Contacto contacto;
	
	@CreationTimestamp
	protected LocalDateTime createdAt;
	
	@UpdateTimestamp
	protected LocalDateTime updatedAt;
	
	public Usuario(String nombre, String contrasenia, Domicilio domicilio, Contacto contacto) {
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.domicilio = domicilio;
		this.contacto = contacto;
		if(domicilio != null)
			domicilio.setUsuario(this);
		if(contacto != null)
			contacto.setUsuario(this);
	}
}

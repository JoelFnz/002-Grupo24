package com.unla.grupo24oo2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unla.grupo24oo2.entities.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Administrador extends Usuario {

	@JsonIgnore  // Evita la recursión infinita con Ticket
    @Column(unique = true)
    private int dni;

    public Administrador(String nombre, String contrasenia, Domicilio domicilio, Contacto contacto, int dni) {
        super(nombre, contrasenia, domicilio, contacto, RoleType.ADMIN);
        this.dni = dni;
    }
}
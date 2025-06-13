package com.unla.grupo24oo2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRegistroDTO {
    private String nombre;
    private String contrasenia;
    private int dni;
    private String telefono;
    private String email;
    private String calle;
    private String localidad;
    private int numero;
}

package com.unla.grupo24oo2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoRegistroDTO {
    private String nombre;
    private String contrasenia;
    private String nroEmpleado;
    private int dni;
    private String telefono;
    private String email;
    private String calle;
    private String localidad;
    private int numero;
}

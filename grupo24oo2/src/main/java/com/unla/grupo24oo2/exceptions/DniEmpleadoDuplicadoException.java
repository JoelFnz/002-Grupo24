package com.unla.grupo24oo2.exceptions;

public class DniEmpleadoDuplicadoException extends RuntimeException {
    public DniEmpleadoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
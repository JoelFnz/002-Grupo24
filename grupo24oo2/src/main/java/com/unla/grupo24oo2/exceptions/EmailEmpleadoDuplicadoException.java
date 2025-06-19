package com.unla.grupo24oo2.exceptions;

public class EmailEmpleadoDuplicadoException extends RuntimeException {
    public EmailEmpleadoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
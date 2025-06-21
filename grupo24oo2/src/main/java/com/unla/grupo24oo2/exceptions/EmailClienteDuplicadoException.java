package com.unla.grupo24oo2.exceptions;

public class EmailClienteDuplicadoException extends RuntimeException {
    public EmailClienteDuplicadoException(String mensaje) {
        super(mensaje);
    }
}

package com.unla.grupo24oo2.exceptions;

public class NoRegisterFoundException extends RuntimeException{

	public NoRegisterFoundException(String mensaje) {
        super(mensaje);
    }
    
    public NoRegisterFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

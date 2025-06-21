package com.unla.grupo24oo2.exceptions;

public class NoRecordFoundException extends RuntimeException{

	public NoRecordFoundException(String mensaje) {
        super(mensaje);
    }
    
    public NoRecordFoundException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

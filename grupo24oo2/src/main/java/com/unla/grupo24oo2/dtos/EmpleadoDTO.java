package com.unla.grupo24oo2.dtos;

public record EmpleadoDTO(
	    Long idUsuario,
	    String nombre,
	    int dni,
	    String nroEmpleado,
	    String calle,
	    String localidad,
	    String email,
	    String telefono
) {}

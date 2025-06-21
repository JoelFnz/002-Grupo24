package com.unla.grupo24oo2.dtos;

public record IUsuarioRegistroDTO(
	    String nombre,
	    int dni,
	    String contrasenia,
	    String email,
	    String telefono,
	    String calle,
	    String localidad,
	    String rol
	) {}

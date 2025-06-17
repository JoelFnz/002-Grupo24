package com.unla.grupo24oo2.services;

import java.util.List;

import com.unla.grupo24oo2.dtos.ServicioDTO;

public interface IServicioService {
	List<ServicioDTO> traerTodosLosServicios();
	List<ServicioDTO> traerServiciosAsignados(int dniEmpleado);
	void vincularPorNombre(int dniEmpleado, String nombreServicio);
	void crearServicio(ServicioDTO dto);
	boolean estaAsociado(int dniEmpleado, String nombreServicio);

}


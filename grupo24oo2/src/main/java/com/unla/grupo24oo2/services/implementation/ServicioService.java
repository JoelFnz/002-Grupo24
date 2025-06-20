package com.unla.grupo24oo2.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.ServicioDTO;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Servicio;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IServicioRepository;
import com.unla.grupo24oo2.services.IServicioService;

@Service
public class ServicioService implements IServicioService{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	IServicioRepository servicioRepository;
	
	@Autowired
	IEmpleadoRepository empleadoRepository;	
	
	@Override
	public List<ServicioDTO> traerTodosLosServicios() {
		return servicioRepository.findAll()
	            .stream()
	            .map(entity -> modelMapper.map(entity, ServicioDTO.class))
	            .collect(Collectors.toList());
	}
	
	@Override
	public void crearServicio(ServicioDTO dto) {
	    // Verificamos si ya existe un servicio con el mismo nombre (opcional pero recomendable)
	    if (servicioRepository.existsByNombreServicio(dto.getNombreServicio())) {
	        throw new RuntimeException("Ya existe un servicio con ese nombre.");
	    }

	    Servicio servicio = modelMapper.map(dto, Servicio.class);
	    servicioRepository.save(servicio);
	}
	
	@Override
	public void vincularPorNombre(int dniEmpleado, String nombreServicio) {
	    Empleado empleado = empleadoRepository.findByDni(dniEmpleado)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + dniEmpleado));

	    Servicio servicio = servicioRepository.findByNombreServicio(nombreServicio)
	        .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + nombreServicio));

	    // Si el empleado ya está asociado, no hacemos nada
	    if (servicio.getEmpleados().contains(empleado)) {
	        System.out.println("Usted ya esta asociado al servicio.");
	        return;
	    }

	    servicio.getEmpleados().add(empleado);
	    servicioRepository.save(servicio);
	}
	
	@Override
	public List<ServicioDTO> traerServiciosAsignados(int dniEmpleado) {
	    Empleado empleado = empleadoRepository.findByDni(dniEmpleado)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

	    List<Servicio> servicios = empleado.getServicios(); // o getServiciosAsignados()
	    return servicios.stream()
	            .map(s -> modelMapper.map(s, ServicioDTO.class))
	            .toList();
	}
	
	@Override
	public boolean estaAsociado(int dniEmpleado, String nombreServicio) {
	    Empleado empleado = empleadoRepository.findByDni(dniEmpleado)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
	    Servicio servicio = servicioRepository.findByNombreServicio(nombreServicio)
	        .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

	    return servicio.getEmpleados().contains(empleado);
	}

	@Override
	public void desvincularPorNombre(int dniEmpleado, String nombreServicio) {
	    Empleado empleado = empleadoRepository.findByDni(dniEmpleado)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + dniEmpleado));

	    Servicio servicio = servicioRepository.findByNombreServicio(nombreServicio)
	        .orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + nombreServicio));

	    // Si el empleado está asociado, lo removemos
	    if (servicio.getEmpleados().contains(empleado)) {
	        servicio.getEmpleados().remove(empleado);
	        servicioRepository.save(servicio);
	        System.out.println("Empleado desvinculado correctamente del servicio.");
	    } else {
	        System.out.println("El empleado no estaba asociado a ese servicio.");
	    }
	}



}


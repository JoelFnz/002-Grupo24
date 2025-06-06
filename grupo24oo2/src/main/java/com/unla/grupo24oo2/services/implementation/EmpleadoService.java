package com.unla.grupo24oo2.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;
import com.unla.grupo24oo2.entities.Contacto;
import com.unla.grupo24oo2.entities.Domicilio;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.services.IEmpleadoService;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    public Empleado registrarEmpleado(EmpleadoRegistroDTO dto) {
        if (empleadoRepository.findByNroEmpleado(dto.getNroEmpleado()).isPresent()) {
            throw new RuntimeException("Ya existe un empleado con ese número.");
        }
        
        if (empleadoRepository.findByDni(dto.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un empleado con ese DNI.");
        }

        // Crear Domicilio
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setLocalidad(String.valueOf(dto.getNumero()));

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setTelefono(dto.getTelefono());
        contacto.setEmail(dto.getEmail());

        // Crear Empleado y asociar domicilio y contacto
        Empleado nuevo = new Empleado(dto.getNroEmpleado(),dto.getDni(), dto.getNombre(), dto.getContrasenia(), domicilio, contacto);

        domicilio.setUsuario(nuevo);
        contacto.setUsuario(nuevo);

        return empleadoRepository.save(nuevo);
    }
}
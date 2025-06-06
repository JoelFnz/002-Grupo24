package com.unla.grupo24oo2.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Contacto;
import com.unla.grupo24oo2.entities.Domicilio;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.services.IClienteService;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    public Cliente registrarCliente(ClienteRegistroDTO dto) {
        if (clienteRepository.findByDni(dto.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un cliente con ese DNI.");
        }

        // Crear Domicilio
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setLocalidad(String.valueOf(dto.getNumero()));

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setTelefono(dto.getTelefono());
        contacto.setEmail(dto.getEmail());

        // Crear Cliente y asignar domicilio y contacto
        Cliente nuevo = new Cliente(dto.getDni(), dto.getNombre(), dto.getContrasenia(), domicilio, contacto);

        // Establecer relación inversa
        domicilio.setUsuario(nuevo);
        contacto.setUsuario(nuevo);

        return clienteRepository.save(nuevo);
    }
}

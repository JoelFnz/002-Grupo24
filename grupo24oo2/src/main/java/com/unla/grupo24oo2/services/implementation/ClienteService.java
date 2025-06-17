package com.unla.grupo24oo2.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Contacto;
import com.unla.grupo24oo2.entities.Domicilio;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;
import com.unla.grupo24oo2.services.IClienteService;

import jakarta.transaction.Transactional;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;
    
    @Autowired
    private ITicketRepository ticketRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;	//<-- Agregado

    public Cliente registrarCliente(ClienteRegistroDTO dto) {
        if (clienteRepository.findByDni(dto.getDni()).isPresent()) {
            throw new RuntimeException("Ya existe un cliente con ese DNI.");
        }

        // Crear Domicilio
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setLocalidad(dto.getLocalidad());

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setTelefono(dto.getTelefono());
        contacto.setEmail(dto.getEmail());

        // Crear Cliente y asignar domicilio y contacto
        String contraseniaHasheada = passwordEncoder.encode(dto.getContrasenia()); // <-- Agregado
        Cliente nuevo = new Cliente(dto.getDni(), dto.getNombre(), contraseniaHasheada, domicilio, contacto);

        // Establecer relación inversa
        domicilio.setUsuario(nuevo);
        contacto.setUsuario(nuevo);

        return clienteRepository.save(nuevo);
    }
    
    @Override
    public Cliente traerClientePorDni(int dni) {
        return clienteRepository.findByDni(dni).orElse(null);
    }
    
    // Nuevo metodo para actualizar datos del cliente
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente); // Guarda el cliente actualizado en la base de datos
    }
    
    // Nuevo metodo para eliminar cuenta
    @Transactional // Para asegurar que toda la operacion se complete correctamente
    public void eliminar(Cliente cliente) {
    	ticketRepository.deleteByCliente(cliente); // Elimina los tickets antes de borrar el cliente
        clienteRepository.delete(cliente); //  Elimina de la base de datos
    }
    
    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll(); // Obtiene la lista de clientes desde el repositorio
    }

    

}

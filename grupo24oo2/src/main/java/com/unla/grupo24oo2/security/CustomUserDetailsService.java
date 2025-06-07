package com.unla.grupo24oo2.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar primero en empleados
        Optional<Empleado> empleado = empleadoRepository.findByContactoEmail(email);
        if (empleado.isPresent()) {
            return new CustomUserDetails(empleado.get());
        }

        // Luego en clientes
        Optional<Cliente> cliente = clienteRepository.findByContactoEmail(email);
        if (cliente.isPresent()) {
            return new CustomUserDetails(cliente.get());
        }

        // Si no se encuentra en ninguno
        throw new UsernameNotFoundException("No se encontró un usuario con el email: " + email);
    }
}

package com.unla.grupo24oo2.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.entities.Administrador;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Usuario;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IUsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	// Inyección de dependencias para buscar usuarios en la base de datos
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;
    
    @Autowired
    private IUsuarioRepository administradorRepository;
    
    // Este metodo se llama automaticamente cuando Spring Security necesita cargar un usuario por su email (username)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("=== loadUserByUsername ejecutándose con email: " + email);

        // Buscar en empleados
        Optional<Empleado> empleado = empleadoRepository.findByContactoEmail(email);
        if (empleado.isPresent()) {
            System.out.println("Empleado encontrado: " + empleado.get().getContacto().getEmail());
            return new CustomUserDetails(empleado.get(), "EMPLEADO");
        }

        // Buscar en clientes
        Optional<Cliente> cliente = clienteRepository.findByContactoEmail(email);
        if (cliente.isPresent()) {
            System.out.println("Cliente encontrado: " + cliente.get().getContacto().getEmail());
            return new CustomUserDetails(cliente.get(), "CLIENTE");
        }
        
        // Buscar en administradores usando findByEmail()
        Usuario usuario = administradorRepository.findByEmail(email);
        if (usuario != null && usuario instanceof Administrador) { // Validar si es un Administrador
            System.out.println("Administrador encontrado: " + usuario.getContacto().getEmail());
            return new CustomUserDetails(usuario, "ADMINISTRADOR");
        }

        // Si no se encontro ningún usuario
        throw new UsernameNotFoundException("No se encontró un usuario con el email: " + email);
    }
}


package com.unla.grupo24oo2.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unla.grupo24oo2.entities.Cliente;

//Interfaz que extiende JpaRepository para operaciones sobre la entidad Cliente
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	// Metodo personalizado para buscar un cliente por DNI
    Optional<Cliente> findByDni(int dni);
    // El uso de Optional permite manejar el caso en que no se encuentre un cliente con ese DNI
}

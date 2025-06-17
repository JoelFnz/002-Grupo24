package com.unla.grupo24oo2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo24oo2.entities.Administrador;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.contacto.email = :email")
    Usuario findByEmail(@Param("email") String email);
    
    Optional<Administrador> findByDni(int dni); // El uso de Optional permite manejar el caso en que no se encuentre un administrador con ese DNI
}
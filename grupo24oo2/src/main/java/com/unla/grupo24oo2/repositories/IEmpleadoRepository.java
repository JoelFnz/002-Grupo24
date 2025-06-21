package com.unla.grupo24oo2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unla.grupo24oo2.entities.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(int dni);
    Optional<Empleado> findByNroEmpleado(String nroEmpleado);
    Optional<Empleado> findByContactoEmail(String email);
    
    @Query("SELECT e.nroEmpleado FROM Empleado e ORDER BY e.idUsuario DESC LIMIT 1")
    String findUltimoNroEmpleado();
    boolean existsByContactoEmail(String email);

}

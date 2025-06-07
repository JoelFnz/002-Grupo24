package com.unla.grupo24oo2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo24oo2.entities.Empleado;

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByDni(int dni);
    Optional<Empleado> findByNroEmpleado(String nroEmpleado);
}

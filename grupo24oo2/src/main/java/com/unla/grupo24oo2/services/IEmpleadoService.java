package com.unla.grupo24oo2.services;

import java.util.List;

import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;
import com.unla.grupo24oo2.entities.Empleado;

public interface IEmpleadoService {
    Empleado registrarEmpleado(EmpleadoRegistroDTO empleado);
    Empleado traerEmpleadoPorDni(int dni);
    
    // Nuevo método para obtener la lista completa de empleados
    List<Empleado> obtenerTodosLosEmpleados();
    
    // Nuevo metodo para actualizar los datos del Empleados
    Empleado guardar(Empleado empleado);
    
    // Nuevo método para eliminar empleados
    void eliminar(Empleado empleado);

    Empleado traerEmpleadoPorNro(String nroEmpleado);
}

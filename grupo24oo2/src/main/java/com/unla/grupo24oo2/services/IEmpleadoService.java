package com.unla.grupo24oo2.services;

import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;
import com.unla.grupo24oo2.entities.Empleado;

public interface IEmpleadoService {
    Empleado registrarEmpleado(EmpleadoRegistroDTO empleado);
    
    Empleado traerEmpleadoPorDni(int dni);
}

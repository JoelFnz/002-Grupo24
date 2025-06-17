package com.unla.grupo24oo2.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupo24oo2.dtos.EmpleadoDTO;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.services.IEmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/lista")
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();
        
        if (empleados.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay empleados
        }

        List<EmpleadoDTO> dtos = empleados.stream().map(empleado -> 
            new EmpleadoDTO(empleado.getIdUsuario(), empleado.getNombre(), empleado.getDni(),empleado.getNroEmpleado(),
            				empleado.getDomicilio().getCalle(), empleado.getDomicilio().getLocalidad(),
                            empleado.getContacto().getEmail(), empleado.getContacto().getTelefono()))
        .toList();

        return ResponseEntity.ok(dtos); // Devuelve la lista de empleados en formato JSON
    }

    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable int dni) {
        Empleado empleado = empleadoService.traerEmpleadoPorDni(dni);

        if (empleado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Empleado no encontrado.");
        }

        empleadoService.eliminar(empleado);
        return ResponseEntity.ok("Empleado eliminado correctamente.");
    }
}

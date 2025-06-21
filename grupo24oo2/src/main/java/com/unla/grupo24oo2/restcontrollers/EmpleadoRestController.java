package com.unla.grupo24oo2.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupo24oo2.dtos.CambiarContraseniaDTO;
import com.unla.grupo24oo2.dtos.ClienteDTO;
import com.unla.grupo24oo2.dtos.EmpleadoDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.services.IEmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

	@Autowired
	private IEmpleadoService empleadoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/{dni}")
	public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorDni(@PathVariable int dni) {
		Empleado empleado = empleadoService.traerEmpleadoPorDni(dni);

		if (empleado == null) {
			return ResponseEntity.notFound().build(); // Devuelve 404 si no encuentra el empleado
		}

		EmpleadoDTO dto = new EmpleadoDTO(empleado.getIdUsuario(), empleado.getNombre(), empleado.getDni(),
				empleado.getNroEmpleado(), empleado.getDomicilio().getCalle(), empleado.getDomicilio().getLocalidad(),
				empleado.getContacto().getEmail(), empleado.getContacto().getTelefono());

		return ResponseEntity.ok(dto); // Devuelve JSON con los datos del empleado
	}

	@PutMapping("/cambiar_contrasenia/{dni}")
	public ResponseEntity<String> cambiarContrasenia(@PathVariable int dni, @RequestBody CambiarContraseniaDTO dto) {
		Empleado empleado = empleadoService.traerEmpleadoPorDni(dni);

		if (empleado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Empleado no encontrado.");
		}

		// Verificar que la contraseña actual es correcta
		if (!passwordEncoder.matches(dto.contraseniaActual(), empleado.getContrasenia())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: La contraseña actual no es correcta.");
		}

		// Validar que la nueva contraseña y su confirmación coincidan
		if (!dto.nuevaContrasenia().equals(dto.confirmarNuevaContrasenia())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error: La nueva contraseña no coincide con la confirmación.");
		}

		// Guardar la nueva contraseña
		empleado.setContrasenia(passwordEncoder.encode(dto.nuevaContrasenia()));
		empleadoService.guardar(empleado);

		return ResponseEntity.ok("Contraseña actualizada correctamente.");
	}

	@GetMapping("/lista")
	public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados() {
		List<Empleado> empleados = empleadoService.obtenerTodosLosEmpleados();

		if (empleados.isEmpty()) {
			return ResponseEntity.noContent().build(); // Devuelve 204 si no hay empleados
		}

		List<EmpleadoDTO> dtos = empleados.stream()
				.map(empleado -> new EmpleadoDTO(empleado.getIdUsuario(), empleado.getNombre(), empleado.getDni(),
						empleado.getNroEmpleado(), empleado.getDomicilio().getCalle(),
						empleado.getDomicilio().getLocalidad(), empleado.getContacto().getEmail(),
						empleado.getContacto().getTelefono()))
				.toList();

		return ResponseEntity.ok(dtos); // Devuelve la lista de empleados en formato JSON
	}

	// EndPoint para actualizar datos
	@PutMapping("/{dni}")
	public ResponseEntity<String> actualizarEmpleado(@PathVariable int dni, @RequestBody EmpleadoDTO dto) {
		Empleado empleado = empleadoService.traerEmpleadoPorDni(dni);

		if (empleado == null) {
			return ResponseEntity.notFound().build(); // Si no se encuentra el cliente, devuelve 404
		}

		// Actualizamos los datos del cliente
		empleado.setNombre(dto.nombre());
		empleado.getDomicilio().setCalle(dto.calle());
		empleado.getDomicilio().setLocalidad(dto.localidad());
		empleado.getContacto().setEmail(dto.email());
		empleado.getContacto().setTelefono(dto.telefono());

		empleadoService.guardar(empleado); // Guarda los cambios

		return ResponseEntity.ok("Datos actualizados correctamente");
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

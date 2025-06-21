package com.unla.grupo24oo2.restcontrollers;

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
import com.unla.grupo24oo2.dtos.EmpleadoDTO;
import com.unla.grupo24oo2.dtos.IUsuarioRegistroDTO;
import com.unla.grupo24oo2.entities.Administrador;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.services.IUsuarioService;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorRestController {

	 	@Autowired
	    private IUsuarioService usuarioService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @GetMapping("/{dni}")
	    public ResponseEntity<IUsuarioRegistroDTO> obtenerAdministradorPorDni(@PathVariable int dni) {
	        Administrador administrador = usuarioService.traerAdministradorPorDni(dni);

	        if (administrador == null) {
	            return ResponseEntity.notFound().build(); // Devuelve 404 si no encuentra el administrador
	        }

	        IUsuarioRegistroDTO dto = new IUsuarioRegistroDTO(
	        	    administrador.getNombre(),
	        	    administrador.getDni(),
	        	    administrador.getContrasenia(),
	        	    administrador.getContacto().getEmail(),
	        	    administrador.getContacto().getTelefono(),
	        	    administrador.getDomicilio().getCalle(),
	        	    administrador.getDomicilio().getLocalidad(),
	        	    administrador.getRol().name() // Convierte `RoleType` a `String`
	        	);


	        return ResponseEntity.ok(dto); // Devuelve JSON con los datos del administrador
	    }

	    @PutMapping("/cambiar_contrasenia/{dni}")
	    public ResponseEntity<String> cambiarContrasenia(@PathVariable int dni, @RequestBody CambiarContraseniaDTO dto) {
	        Administrador administrador = usuarioService.traerAdministradorPorDni(dni);

	        if (administrador == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Empleado no encontrado.");
	        }

	        // Verificar que la contraseña actual es correcta
	        if (!passwordEncoder.matches(dto.contraseniaActual(), administrador.getContrasenia())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: La contraseña actual no es correcta.");
	        }

	        // Validar que la nueva contraseña y su confirmación coincidan
	        if (!dto.nuevaContrasenia().equals(dto.confirmarNuevaContrasenia())) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: La nueva contraseña no coincide con la confirmación.");
	        }

	        // Guardar la nueva contraseña
	        administrador.setContrasenia(passwordEncoder.encode(dto.nuevaContrasenia()));
	        usuarioService.guardar(administrador);

	        return ResponseEntity.ok("Contraseña actualizada correctamente.");
	    }
	    
	    @DeleteMapping("/eliminar/{dni}")
	    public ResponseEntity<String> eliminarAdministrador(@PathVariable int dni) {
	    	Administrador administrador = usuarioService.traerAdministradorPorDni(dni);

	        if (administrador == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Administrador no encontrado.");
	        }

	        usuarioService.eliminar(administrador);
	        return ResponseEntity.ok("Administrador eliminado correctamente.");
	    }
	    
	    // EndPoint para actualizar datos
	    @PutMapping("/{dni}")
	    public ResponseEntity<String> actualizarAdministrador(@PathVariable int dni, @RequestBody IUsuarioRegistroDTO dto) {
	    	Administrador administrador = usuarioService.traerAdministradorPorDni(dni);

	        if (administrador == null) {
	            return ResponseEntity.notFound().build(); // Si no se encuentra el cliente, devuelve 404
	        }

	        // Actualizamos los datos del cliente
	        administrador.setNombre(dto.nombre());
	        administrador.getDomicilio().setCalle(dto.calle());
	        administrador.getDomicilio().setLocalidad(dto.localidad());
	        administrador.getContacto().setEmail(dto.email());
	        administrador.getContacto().setTelefono(dto.telefono());

	        usuarioService.guardar(administrador); // Guarda los cambios

	        return ResponseEntity.ok("Datos actualizados correctamente");
	    }
}

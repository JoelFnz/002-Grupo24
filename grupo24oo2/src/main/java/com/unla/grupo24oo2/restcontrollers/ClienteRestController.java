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
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.services.IClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteDTO> obtenerClientePorDni(@PathVariable int dni) {
        Cliente cliente = clienteService.traerClientePorDni(dni);

        if (cliente == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no encuentra el cliente
        }

        ClienteDTO dto = new ClienteDTO(cliente.getIdUsuario(), cliente.getNombre(), cliente.getDni(), 
                                        cliente.getDomicilio().getCalle(),cliente.getDomicilio().getLocalidad(), cliente.getContacto().getEmail(), 
                                        cliente.getContacto().getTelefono());

        return ResponseEntity.ok(dto); // Devuelve JSON limpio con solo los datos esenciales
    }
    
    // EndPoint para actualizar datos
    @PutMapping("/{dni}")
    public ResponseEntity<String> actualizarCliente(@PathVariable int dni, @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteService.traerClientePorDni(dni);

        if (cliente == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cliente, devuelve 404
        }

        // Actualizamos los datos del cliente
        cliente.setNombre(dto.nombre());
        cliente.getDomicilio().setCalle(dto.calle());
        cliente.getDomicilio().setLocalidad(dto.localidad());
        cliente.getContacto().setEmail(dto.email());
        cliente.getContacto().setTelefono(dto.telefono());

        clienteService.guardar(cliente); // Guarda los cambios

        return ResponseEntity.ok("Datos actualizados correctamente");
    }
    
    @PutMapping("/cambiar_contrasenia/{dni}")
    public ResponseEntity<String> cambiarContrasenia(@PathVariable int dni, @RequestBody CambiarContraseniaDTO dto) {
        Cliente cliente = clienteService.traerClientePorDni(dni);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Cliente no encontrado.");
        }

        // Verificar que la contraseña actual es correcta
        if (!passwordEncoder.matches(dto.contraseniaActual(), cliente.getContrasenia())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: La contraseña actual no es correcta.");
        }

        // Validar que la nueva contraseña y su confirmación coincidan
        if (!dto.nuevaContrasenia().equals(dto.confirmarNuevaContrasenia())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: La nueva contraseña no coincide con la confirmación.");
        }

        // Guardar la nueva contraseña
        cliente.setContrasenia(passwordEncoder.encode(dto.nuevaContrasenia()));
        clienteService.guardar(cliente);

        return ResponseEntity.ok("Contraseña actualizada correctamente.");
    }
    
    @DeleteMapping("/eliminar/{dni}")
    public ResponseEntity<String> eliminarCuenta(@PathVariable int dni) {
        Cliente cliente = clienteService.traerClientePorDni(dni);

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Cliente no encontrado.");
        }

        clienteService.eliminar(cliente); // Llamar al método de eliminación
        return ResponseEntity.ok("Cuenta eliminada correctamente.");
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay clientes
        }

        List<ClienteDTO> dtos = clientes.stream().map(cliente -> 
            new ClienteDTO(cliente.getIdUsuario(), cliente.getNombre(), cliente.getDni(), 
                           cliente.getDomicilio().getCalle(), cliente.getDomicilio().getLocalidad(), 
                           cliente.getContacto().getEmail(), cliente.getContacto().getTelefono()))
        .toList();

        return ResponseEntity.ok(dtos); // Devuelve la lista de clientes en formato JSON
    }
    
    

}

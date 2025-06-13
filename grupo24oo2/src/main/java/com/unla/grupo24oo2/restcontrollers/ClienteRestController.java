package com.unla.grupo24oo2.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupo24oo2.dtos.ClienteDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.services.IClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

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

}

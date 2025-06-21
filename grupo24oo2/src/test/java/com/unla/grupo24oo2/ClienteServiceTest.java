package com.unla.grupo24oo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.services.implementation.ClienteService;

@SpringBootTest
public class ClienteServiceTest {
	/*
    @Autowired
    private ClienteService clienteService;
    
    @Test
    public void testRegistrarCliente() {
        ClienteRegistroDTO dto = new ClienteRegistroDTO();
        dto.setDni(12345678);
        dto.setNombre("Diego");
        dto.setContrasenia("clave123");
        dto.setCalle("Belgrano");
        dto.setNumero(456);
        dto.setTelefono("1155667788");
        dto.setEmail("diego@test.com");

        Cliente cliente = clienteService.registrarCliente(dto);

        assertNotNull(cliente.getIdUsuario());
        assertEquals("Diego", cliente.getNombre());
    }
    */
}

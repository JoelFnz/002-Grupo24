package com.unla.grupo24oo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.unla.grupo24oo2.dtos.IUsuarioRegistroDTO;
import com.unla.grupo24oo2.entities.Usuario;
import com.unla.grupo24oo2.entities.enums.RoleType;
import com.unla.grupo24oo2.services.IUsuarioService;


@SpringBootTest
public class TestCrearUsuarioAdministrador {
/*
    @Autowired
    private IUsuarioService usuarioService;

    @Test
    public void testRegistrarAdministrador() {
        IUsuarioRegistroDTO dto = new IUsuarioRegistroDTO(
            "Diego Admin", // Nombre
            55555555, // Dni
            "1234", // Contraseña
            "admin@email.com", //Email
            "1199887766", // Telefono
            "Av. Principal", // Calle
            "Lomas de Zamora", // Localidad
            "ADMIN" // Rol
        );

        Usuario admin = usuarioService.registrarAdministrador(dto); //  Asignar rol ADMIN

        assertNotNull(admin.getIdUsuario());
        assertEquals("Admin Master", admin.getNombre());
        assertEquals(RoleType.ADMIN, admin.getRol());
    }
    */
}


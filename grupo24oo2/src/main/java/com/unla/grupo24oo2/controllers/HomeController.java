package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.security.CustomUserDetails;
import com.unla.grupo24oo2.services.implementation.EmpleadoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
    private EmpleadoService empleadoService;

	@GetMapping("/")
	public String home(HttpSession session) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            String email = userDetails.getUsername();
            Integer dni = userDetails.getDni();
            String rol = userDetails.getAuthorities().stream()
            		
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_INVITADO"); // Si no tiene rol, asignar "Invitado"

            session.setAttribute("emailCliente", email);
            session.setAttribute("dniCliente", dni); // <-- Agregado
            session.setAttribute("rolUsuario", rol.replace("ROLE_", "")); // Guarda el rol sin el prefijo "ROLE_"
            
            if ("ROLE_EMPLEADO".equals(rol)) {
                Empleado empleado = empleadoService.traerEmpleadoPorDni(dni);
                if (empleado != null) {
                    session.setAttribute("nroEmpleado", empleado.getNroEmpleado());
                }
            }

        }

	    return ViewRouterHelper.HOME;
	}
}

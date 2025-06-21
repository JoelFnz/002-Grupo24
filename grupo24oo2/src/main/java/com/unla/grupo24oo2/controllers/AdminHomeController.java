package com.unla.grupo24oo2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.security.CustomUserDetails;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping("/home")
    public String mostrarHomeAdmin(HttpSession session){
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
    	
 	    }
    	return (ViewRouterHelper.ADMIN_HOME);
    }
}

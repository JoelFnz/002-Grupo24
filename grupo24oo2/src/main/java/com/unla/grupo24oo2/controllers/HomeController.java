package com.unla.grupo24oo2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.security.CustomUserDetails;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(HttpSession session) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
            String email = userDetails.getUsername();
            Integer dni = userDetails.getDni();
            
            session.setAttribute("emailCliente", email);
            session.setAttribute("dniCliente", dni); // <-- Agregado
        }

	    return ViewRouterHelper.HOME;
	}
}

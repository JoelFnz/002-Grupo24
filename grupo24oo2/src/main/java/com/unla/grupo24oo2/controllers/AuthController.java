package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
	
	// Inyecta el AuthenticationManager de Spring, que se encarga de autenticar usuarios
    @Autowired
    private AuthenticationManager authenticationManager;

    // Este metodo se ejecuta cuando el usuario envia el formulario de login (POST a /login)
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {

        try {
        	// Intenta autenticar al usuario con el email y contraseña ingresados
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            // Login exitoso
            return new ModelAndView("redirect:/inicio"); // Cambia por la vista principal del sistema

        } catch (AuthenticationException e) {
            // Login fallido
            ModelAndView mav = new ModelAndView("login"); // vista del formulario de login
            mav.addObject("error", "Email o contraseña incorrectos");
            return mav;
        }
    }
}

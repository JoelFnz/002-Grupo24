package com.unla.grupo24oo2.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Obtener el usuario autenticado
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        // Obtener el DNI del cliente
        Integer dniCliente = userDetails.getDni();
        
        String email = userDetails.getUsername(); 

        // Guardar email en sesión
        request.getSession().setAttribute("emailCliente", email);
        
        // Redirigir al historial con el DNI correcto
        response.sendRedirect("/ticket/historial?dniCliente=" + dniCliente);
    }
}

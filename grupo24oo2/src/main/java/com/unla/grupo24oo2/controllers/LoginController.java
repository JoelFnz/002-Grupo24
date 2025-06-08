package com.unla.grupo24oo2.controllers;

import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        // Retorna la constante con el nombre de la vista "login"
        return ViewRouterHelper.LOGIN;
    }
}

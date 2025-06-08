package com.unla.grupo24oo2.controllers;

import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.IEmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	private IEmpleadoService empleadoService;
	
    @GetMapping("/login")
    public String showLoginForm() {
        // Retorna la constante con el nombre de la vista "login"
        return ViewRouterHelper.LOGIN;
    }
}

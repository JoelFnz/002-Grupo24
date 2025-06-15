package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.security.CustomUserDetails;
import com.unla.grupo24oo2.services.implementation.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public ModelAndView mostrarFormularioRegistro() {
        ModelAndView mAV = new ModelAndView(ViewRouterHelper.REGISTRO);
        mAV.addObject("cliente", new ClienteRegistroDTO());
        return mAV;
    }

    // Procesar el formulario enviado
    @PostMapping("/registro")
    public ModelAndView procesarFormulario(@ModelAttribute("cliente") ClienteRegistroDTO dto) {
        clienteService.registrarCliente(dto);
        ModelAndView mAV = new ModelAndView(ViewRouterHelper.REGISTRO_EXITOSO);
        mAV.addObject("mensaje", "Registro exitoso");
        return mAV;
    }
    
    // Mostrar mis datos
    @GetMapping("/mis_datos")
    public String verMisDatos() {
        return (ViewRouterHelper.MIS_DATOS);
    }
 
    // Mostrar opciones de seguridad
    @GetMapping("/seguridad")
    public String mostrarSeguridad() {
        return (ViewRouterHelper.SEGURIDAD);
    }
 
 	// Mostrar cambiar contraseña
    @GetMapping("/cambiar_contrasenia")
    public String mostrarCambioContrasenia() {
        return (ViewRouterHelper.CAMBIAR_CONTRASENIA);
    }
    
    // Mostrar eliminar cuenta
    @GetMapping("/eliminar_cuenta")
    public String mostrarEliminarCuenta() {
        return (ViewRouterHelper.ELIMINAR_CUENTA);
    }


}

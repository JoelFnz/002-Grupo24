package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new ClienteRegistroDTO());
        return "registro"; // carga registro.html desde /templates
    }

    @PostMapping("/registro")
    public String procesarFormulario(@ModelAttribute("cliente") ClienteRegistroDTO dto, Model model) {
        clienteService.registrarCliente(dto);
        model.addAttribute("mensaje", "Registro exitoso");
        return "registro_exitoso"; // otra vista HTML
    }
}

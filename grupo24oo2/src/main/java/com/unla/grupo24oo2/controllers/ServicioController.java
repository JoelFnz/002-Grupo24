package com.unla.grupo24oo2.controllers;

import com.unla.grupo24oo2.dtos.ServicioDTO;
import com.unla.grupo24oo2.security.CustomUserDetails;
import com.unla.grupo24oo2.services.IServicioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping("/ofrecer")
    public String mostrarFormularioOfrecer(Model model, @RequestParam(value = "mensaje", required = false) String mensaje) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        int dniEmpleado = userDetails.getDni();

        ServicioDTO dto = new ServicioDTO();
        dto.setDniEmpleado(dniEmpleado);
        model.addAttribute("servicioDTO", dto);
        
        model.addAttribute("nuevoServicio", new ServicioDTO());

        List<ServicioDTO> serviciosAsignados = servicioService.traerServiciosAsignados(dniEmpleado);
        model.addAttribute("serviciosAsignados", serviciosAsignados);

        List<ServicioDTO> servicios = servicioService.traerTodosLosServicios();
        model.addAttribute("listaServicios", servicios != null ? servicios : new ArrayList<>());

        if (mensaje != null) {
            model.addAttribute("mensaje", mensaje);
        }
        

        return "servicio/ofrecer_servicio";
    }

    @PostMapping("/ofrecer")
    public String procesarOferta(@ModelAttribute("servicioDTO") ServicioDTO dto, Model model) {
        boolean yaAsociado = servicioService.estaAsociado(dto.getDniEmpleado(), dto.getNombreServicio());

        if (yaAsociado) {
            String mensaje = "Ya estas asociado a ese servicio.";
            return "redirect:/servicio/ofrecer?mensaje=" + mensaje.replace(" ", "%20");
        }

        servicioService.vincularPorNombre(dto.getDniEmpleado(), dto.getNombreServicio());
        return "redirect:/servicio/ofrecer?mensaje=Servicio%20asignado%20correctamente";
    }
    
    
    @PostMapping("/crear")
    public String crearNuevoServicio(@ModelAttribute("nuevoServicio") ServicioDTO dto, Model model) {
        servicioService.crearServicio(dto);
        return "redirect:/servicio/ofrecer?mensaje=Servicio%20creado%20correctamente";
    }

}


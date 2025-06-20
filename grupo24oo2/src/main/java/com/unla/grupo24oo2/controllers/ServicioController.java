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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping("/ofrecer")
    public ModelAndView mostrarFormularioOfrecer(@RequestParam(value = "mensaje", required = false) String mensaje) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        int dniEmpleado = userDetails.getDni();

        ModelAndView modelAndView = new ModelAndView("servicio/ofrecer_servicio");

        ServicioDTO dto = new ServicioDTO();
        dto.setDniEmpleado(dniEmpleado);
        modelAndView.addObject("servicioDTO", dto);

        modelAndView.addObject("nuevoServicio", new ServicioDTO());

        List<ServicioDTO> serviciosAsignados = servicioService.traerServiciosAsignados(dniEmpleado);
        modelAndView.addObject("serviciosAsignados", serviciosAsignados);

        List<ServicioDTO> servicios = servicioService.traerTodosLosServicios();
        modelAndView.addObject("listaServicios", servicios != null ? servicios : new ArrayList<>());

        if (mensaje != null) {
            modelAndView.addObject("mensaje", mensaje);
        }

        return modelAndView;
    }


    @PostMapping("/ofrecer")
    public ModelAndView procesarOferta(@ModelAttribute("servicioDTO") ServicioDTO dto) {
        boolean yaAsociado = servicioService.estaAsociado(dto.getDniEmpleado(), dto.getNombreServicio());

        String mensaje = yaAsociado
            ? "Ya estas asociado a ese servicio."
            : "Servicio asignado correctamente";

        if (!yaAsociado) {
            servicioService.vincularPorNombre(dto.getDniEmpleado(), dto.getNombreServicio());
        }

        return new ModelAndView("redirect:/servicio/ofrecer?mensaje=" + mensaje.replace(" ", "%20"));
    }

    
    
    @PostMapping("/crear")
    public ModelAndView crearNuevoServicio(@ModelAttribute("nuevoServicio") ServicioDTO dto) {
        servicioService.crearServicio(dto);
        return new ModelAndView("redirect:/servicio/ofrecer?mensaje=Servicio%20creado%20correctamente");
    }

    @PostMapping("/desvincular")
    public ModelAndView desvincularServicio(@RequestParam("dniEmpleado") int dniEmpleado,
                                            @RequestParam("nombreServicio") String nombreServicio) {

        servicioService.desvincularPorNombre(dniEmpleado, nombreServicio);
        String mensaje = "Servicio eliminado de tus asignaciones";

        return new ModelAndView("redirect:/servicio/ofrecer?mensaje=" + mensaje.replace(" ", "%20"));
    }

}


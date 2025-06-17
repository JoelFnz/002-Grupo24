package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.implementation.EmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/registro")
    public ModelAndView mostrarFormularioRegistro() {
        ModelAndView mAV = new ModelAndView(ViewRouterHelper.REGISTRO_EMPLEADO);
        mAV.addObject("empleado", new EmpleadoRegistroDTO());
        return mAV;
    }

    @PostMapping("/registro")
    public ModelAndView procesarFormulario(@ModelAttribute("empleado") EmpleadoRegistroDTO dto) {
        empleadoService.registrarEmpleado(dto);
        ModelAndView mAV = new ModelAndView(ViewRouterHelper.REGISTRO_EXITOSO_EMPLEADO);
        mAV.addObject("mensaje", "Empleado registrado exitosamente");
        return mAV;
    }
    
    @GetMapping("/lista_empleados")
    public String listarEmpleados() {
        return (ViewRouterHelper.LISTA_EMPLEADOS);
    }
    
    // Mostrar eliminar cuenta
    @GetMapping("/eliminar_cuenta")
    public String mostrarEliminarCuenta() {
        return (ViewRouterHelper.ELIMINAR_CUENTA);
    }
    
    // Mostrar cambiar contraseña
    @GetMapping("/cambiar_contrasenia")
    public String mostrarCambioContrasenia() {
        return (ViewRouterHelper.CAMBIAR_CONTRASENIA);
    }
}

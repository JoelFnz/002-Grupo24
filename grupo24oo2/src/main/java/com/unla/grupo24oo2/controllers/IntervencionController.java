package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.IntervencionDTO;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.IIntervencionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/intervencion")
public class IntervencionController {
	
	@Autowired
	private IIntervencionService intervencionService;
	
	@GetMapping
	public ModelAndView mostrarTicketsAsociados(@RequestParam(required = true) String nroEmpleado) {
		ModelAndView mV = new ModelAndView();
		
		return mV;
	}
	
	@GetMapping("/crear")
	public ModelAndView mostrarFormularioIntervencion(
	        @RequestParam(required = true) int dniEmpleado,
	        @RequestParam(required = true) String nroTicket
	) {
	    ModelAndView mV = new ModelAndView(ViewRouterHelper.FORMULARIO_INTERVENCION);

	    String nroEmpleado = intervencionService.obtenerNroEmpleadoPorDni(dniEmpleado);
	    
	    IntervencionDTO intervencion = new IntervencionDTO();
	    intervencion.setNroEmpleado(nroEmpleado);
	    intervencion.setNroTicket(nroTicket);

	    mV.addObject("intervencion", intervencion);
	    return mV;
	}

	
	@PostMapping("/crear")
	public ModelAndView crearIntervencion(
			@Valid @ModelAttribute("intervencion") IntervencionDTO intervencion,
			BindingResult bR
			) {
		ModelAndView mV;
		
		if(bR.hasErrors()) {
			mV = new ModelAndView(ViewRouterHelper.FORMULARIO_INTERVENCION);
			mV.addObject("intervencion", intervencion);
			mV.addObject("mensaje", "un mensaje de error");
		} else {
			intervencionService.crearIntervencion(intervencion);
			mV = new ModelAndView(ViewRouterHelper.CREACION_INTERVENCION);
			mV.addObject("intervencion", intervencion);
		}
		
		return mV;
	}
	
}

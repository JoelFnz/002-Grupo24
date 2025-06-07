package com.unla.grupo24oo2.controllers;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.IServicioService;
import com.unla.grupo24oo2.services.ITicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IServicioService servicioService; 
	
	@GetMapping("/crear/{dni}")
	public ModelAndView mostrarFormularioTicket(@PathVariable("dni") int dniCliente, Model model) {
		ModelAndView mV = new ModelAndView(ViewRouterHelper.FORMULARIO_TICKET);
		
		TicketDTO ticket = new TicketDTO();
		ticket.setDniCliente(dniCliente);
		
		mV.addObject("ticket", ticket);
		mV.addObject("servicios", servicioService.traerTodosLosServicios());
		
		return mV;
	}
	
	@PostMapping("/crear")
	public ModelAndView guardarTicket(@ModelAttribute("ticket") TicketDTO ticket) {
		ModelAndView mV = new ModelAndView(ViewRouterHelper.CREACION_TICKET);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		TicketResponseDTO ticketResponse = ticketService.crearTicket(ticket);
		
		mV.addObject("ticketResponse", ticketResponse);
		mV.addObject("fechaYHoraDeCreacion", ticketResponse.getFechaYHoraDeCreacion().format(formatter));
	    mV.addObject("fechaYHoraDeCaducidad", ticketResponse.getFechaYHoraDeCaducidad().format(formatter));
		return mV;
	}
}

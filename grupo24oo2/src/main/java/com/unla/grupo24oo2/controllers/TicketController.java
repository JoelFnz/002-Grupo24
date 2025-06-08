package com.unla.grupo24oo2.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.IEmpleadoService;
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
	
	@GetMapping("/historial")
	public ModelAndView obtenerHistorial(
	        @RequestParam(required = true) int dniCliente,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime fechaCreacion,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime fechaCaducidad,
	        @RequestParam(required = false) String nroTicket,
	        Pageable pageable){
	    
	    TicketFilterDTO filter = new TicketFilterDTO();
	    filter.setFechaCreacion(fechaCreacion);
	    filter.setFechaCaducidad(fechaCaducidad);
	    filter.setNroTicket(nroTicket);

	    ModelAndView mV = new ModelAndView(ViewRouterHelper.HISTORIAL_TICKET);
	    
	    Page<TicketResponseDTO> tickets = ticketService.obtenerTicketsPorFiltro(filter, dniCliente, pageable);
	    mV.addObject("tickets", tickets);
	    mV.addObject("filter", filter); 
	    mV.addObject("dni", dniCliente);
	    mV.addObject("fechaCreacionFormatted", fechaCreacion != null ? 
	            fechaCreacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null);
	    mV.addObject("fechaCaducidadFormatted", fechaCaducidad != null ? 
	            fechaCaducidad.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null);

	    return mV; 
	}
	
	 @GetMapping("/asociados")
	 public ModelAndView mostrarTicketsAsociados(
			 @RequestParam(required = true) String nroEmpleado,
			 @PageableDefault(size = 5) Pageable pageable) {
		 ModelAndView mV = new ModelAndView(ViewRouterHelper.ASOCIADOS_TICKET);

		 Page<TicketResponseDTO> tickets = ticketService.obtenerTicketsPorNroEmpleado(nroEmpleado, pageable);
		 
		 mV.addObject("nroEmpleado", nroEmpleado);
		 mV.addObject("tickets", tickets);

		 return mV;
	 }
}

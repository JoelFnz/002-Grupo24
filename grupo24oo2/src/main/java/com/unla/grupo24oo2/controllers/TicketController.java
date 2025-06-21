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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unla.grupo24oo2.dtos.TicketDTO;
import com.unla.grupo24oo2.dtos.TicketFilterDTO;
import com.unla.grupo24oo2.dtos.TicketResponseDTO;
import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.entities.enums.TipoDeEstado;
import com.unla.grupo24oo2.helpers.ViewRouterHelper;
import com.unla.grupo24oo2.services.IClienteService;
import com.unla.grupo24oo2.services.IEmpleadoService;
import com.unla.grupo24oo2.services.IIntervencionService;
import com.unla.grupo24oo2.services.IServicioService;
import com.unla.grupo24oo2.services.ITicketService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IServicioService servicioService; 
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IIntervencionService intervencionService;

	
	@GetMapping("/crear/{dni}")
	public ModelAndView mostrarFormularioTicket(@PathVariable("dni") int dniCliente, Model model, HttpSession session) {  // <- Parametro HttpSession agregado para la session
	    ModelAndView mV = new ModelAndView(ViewRouterHelper.FORMULARIO_TICKET);
	    
	    Cliente cliente = clienteService.traerClientePorDni(dniCliente); //<-- Creo cliente para obtener el email y mostrarlo en la vista
	    
	    TicketDTO ticket = new TicketDTO();
	    ticket.setDniCliente(dniCliente);

	    if (cliente != null && cliente.getContacto() != null) {
	        String email = cliente.getContacto().getEmail();
	        session.setAttribute("emailCliente", email); //<-- Guardamos el email en la sesion
	    } else {
	        session.setAttribute("emailCliente", null);
	    }

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
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime desdeFechaCreacion,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime hastaFechaCreacion,
	        @RequestParam(required = false) String nroTicket,
	        Pageable pageable){
		
	    
	    TicketFilterDTO filter = new TicketFilterDTO();
	    filter.setHastaFechaCreacion(hastaFechaCreacion);
	    filter.setDesdeFechaCreacion(desdeFechaCreacion);
	    filter.setNroTicket(nroTicket);

	    ModelAndView mV = new ModelAndView(ViewRouterHelper.HISTORIAL_TICKET);
	    
	    Page<TicketResponseDTO> tickets = ticketService.obtenerTicketsPorFiltro(filter, dniCliente, pageable);
	    mV.addObject("tickets", tickets);
	    mV.addObject("filter", filter); 
	    mV.addObject("dni", dniCliente);
	    mV.addObject("desdeFechaFormatted", desdeFechaCreacion != null ? 
	            desdeFechaCreacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null);
	    mV.addObject("hastaFechaFormatted", hastaFechaCreacion != null ? 
	            hastaFechaCreacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) : null);

	    return mV; 
	}
	
	 
	 @GetMapping("/detalle")
	 public ModelAndView mostrarDetalleTicket(
			 @RequestParam(required = true) int dniCliente,
			 @RequestParam(required = true) String nroTicket,
			 Pageable pageable) {
		 ModelAndView mV = new ModelAndView(ViewRouterHelper.DETALLE_TICKET);
		 
		 TicketResponseDTO ticket = ticketService.obtenerTicketPorNroTicket(nroTicket); 
		 
		 mV.addObject("ticket", ticket);
		 mV.addObject("intervenciones", intervencionService.obtenerIntervencionesPorNroTicket(nroTicket, pageable));
		 
		 return mV;
	 }
	 
	 @PostMapping("/darBaja")
	 public String darDeBajaTicket(@RequestParam String nroTicket,
	                               @RequestParam int dniCliente,
	                               RedirectAttributes redirectAttributes) {
	     try {
	         ticketService.finalizarTicket(nroTicket);
	         redirectAttributes.addFlashAttribute("exito", "El ticket fue dado de baja correctamente.");
	     } catch (RuntimeException e) {
	         redirectAttributes.addFlashAttribute("error", "No se pudo dar de baja el ticket: " + e.getMessage());
	     }

	     return "redirect:/ticket/historial?dniCliente=" + dniCliente;
	 }
	 
	 @GetMapping("/asociados")
	 public ModelAndView mostrarTicketsAsociados(@RequestParam int dniEmpleado) {
	     ModelAndView mv = new ModelAndView(ViewRouterHelper.ASOCIADOS_TICKET);

	     // 1. Tickets asociados al empleado
	     List<TicketResponseDTO> ticketsAsociados = ticketService.obtenerTicketsAsignados(dniEmpleado);

	     // 2. Tickets activos del sistema (estado INICIADO)
	     List<TicketResponseDTO> ticketsActivos = ticketService.obtenerTicketsActivos();

	     // 3. Historial de tickets finalizados del empleado
	     List<TicketResponseDTO> ticketsFinalizados = ticketService.obtenerTicketsFinalizados(dniEmpleado);

	     mv.addObject("dniEmpleado", dniEmpleado);
	     mv.addObject("ticketsAsociados", ticketsAsociados);
	     mv.addObject("ticketsActivos", ticketsActivos);
	     mv.addObject("ticketsFinalizados", ticketsFinalizados);

	     return mv;
	 }

	 @GetMapping("/asignar")
	 public String asignarTicket(@RequestParam String nroTicket, @RequestParam int dniEmpleado) {
	     ticketService.asignarTicketAEmpleado(nroTicket, dniEmpleado);
	     return "redirect:/ticket/asociados?dniEmpleado=" + dniEmpleado;
	 }

	 @GetMapping("/finalizar")
	 public String finalizarTicket(@RequestParam String nroTicket, @RequestParam int dniEmpleado) {
	     ticketService.finalizarTicket(nroTicket);
	     return "redirect:/ticket/asociados?dniEmpleado=" + dniEmpleado;
	 }

	 
}

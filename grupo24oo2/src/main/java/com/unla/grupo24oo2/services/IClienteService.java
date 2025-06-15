package com.unla.grupo24oo2.services;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.entities.Cliente;

public interface IClienteService {
	Cliente registrarCliente(ClienteRegistroDTO cliente);
	
	// Nuevo método
    Cliente traerClientePorDni(int dni);
    
    // Nuevo metodo para actualizar los datos del cliente
    Cliente guardar(Cliente cliente);
    
    //  Nuevo metodo para eliminar la cuenta del cliente
    void eliminar(Cliente cliente);


}

package com.unla.grupo24oo2.dtos;

import java.time.LocalDateTime;

import com.unla.grupo24oo2.entities.TipoDeEstado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketFilterDTO {
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCaducidad;
    //private TipoDeEstado estado;
    private String nroTicket;
}

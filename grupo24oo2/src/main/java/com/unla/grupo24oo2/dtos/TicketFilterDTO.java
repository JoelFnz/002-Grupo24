package com.unla.grupo24oo2.dtos;

import java.time.LocalDateTime;

import com.unla.grupo24oo2.entities.enums.TipoDeEstado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TicketFilterDTO {
    private LocalDateTime desdeFechaCreacion;
    private LocalDateTime hastaFechaCreacion;
    //private TipoDeEstado estado;
    private String nroTicket;
}

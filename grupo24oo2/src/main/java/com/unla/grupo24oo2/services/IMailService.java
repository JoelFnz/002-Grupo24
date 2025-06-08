package com.unla.grupo24oo2.services;

public interface IMailService {
    void enviarEmail(String destinatario, String asunto, String cuerpo);
}
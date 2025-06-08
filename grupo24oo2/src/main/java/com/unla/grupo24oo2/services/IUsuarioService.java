package com.unla.grupo24oo2.services;

import com.unla.grupo24oo2.entities.Usuario;

public interface IUsuarioService {
    Usuario buscarPorEmail(String email);
    String generarTokenRecuperacion(String email);
    boolean validarTokenRecuperacion(String token);
    boolean actualizarContrasenia(String token, String nuevaContrasenia);
}
package com.unla.grupo24oo2.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.unla.grupo24oo2.entities.Usuario;
import com.unla.grupo24oo2.repositories.IUsuarioRepository;
import com.unla.grupo24oo2.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Map<String, String> tokenStorage = new HashMap<>(); // Simula almacenamiento de tokens

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public String generarTokenRecuperacion(String email) {
        String token = UUID.randomUUID().toString(); // Genera un token único
        tokenStorage.put(token, email);
        return token;
    }

    @Override
    public boolean validarTokenRecuperacion(String token) {
        return tokenStorage.containsKey(token);
    }

    @Override
    public boolean actualizarContrasenia(String token, String nuevaContrasenia) {
        String email = tokenStorage.get(token);
        if (email == null) {
            return false;
        }

        Usuario usuario = usuarioRepository.findByEmail(email);

        // Encriptar la nueva contraseña antes de guardarla
        usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
        usuarioRepository.save(usuario);

        tokenStorage.remove(token); // Eliminamos el token después de usarlo
        return true;
    }
}
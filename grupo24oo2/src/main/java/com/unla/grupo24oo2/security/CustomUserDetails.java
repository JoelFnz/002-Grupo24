package com.unla.grupo24oo2.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.unla.grupo24oo2.entities.Cliente;
import com.unla.grupo24oo2.entities.Usuario;

import java.util.Collection;
import java.util.Collections;

//Esta clase implementa la interfaz UserDetails que usa Spring Security
//Representa al usuario autenticado del sistema
public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;
    private final String rol;
    
    // Constructor que recibe el usuario y su rol
    public CustomUserDetails(Usuario usuario, String rol) {
        this.usuario = usuario;
        this.rol = rol;
    }
    
    // Método para obtener el DNI si el usuario es un cliente
    public Integer getDni() {
        if (usuario instanceof Cliente) {
            return ((Cliente) usuario).getDni();
        }
        return null; // Retornar null si no es un cliente
    }



    // Devuelve los permisos (authorities) del usuario, en este caso un solo rol
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol));
    }
    
    // Devuelve la contraseña del usuario (ya encriptada con BCrypt)
    @Override
    public String getPassword() {
        return usuario.getContrasenia();
    }

    // Devuelve el nombre de usuario que se va a usar para el login (en este caso el email)
    @Override
    public String getUsername() {
        return usuario.getContacto().getEmail();
    }

    // Flags para indicar que la cuenta esta activa y valida
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

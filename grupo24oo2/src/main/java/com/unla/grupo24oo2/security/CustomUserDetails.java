package com.unla.grupo24oo2.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.unla.grupo24oo2.entities.Usuario;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

    private final Usuario usuario;

    // Constructor que recibe un objeto Usuario (puede ser Cliente o Empleado)
    public CustomUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    // Retorna la contraseña (ya encriptada) del usuario
    @Override
    public String getPassword() {
        return usuario.getContrasenia();
    }

    // Retorna el identificador con el cual el usuario inicia sesión. (En nuestro caso el email)
    @Override
    public String getUsername() {
        return usuario.getContacto().getEmail();
    }

    // Retorna la lista de roles (autoridades) del usuario
    // Usamos el enum RoleType como una sola autoridad
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().name()));
    }

    // Indica si la cuenta esta expirada. True = la cuenta esta activa
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indica si la cuenta esta bloqueada. True = no esta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indica si las credenciales (contraseña) estan expiradas. True = validas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indica si la cuenta este habilitada. True = habilitada
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

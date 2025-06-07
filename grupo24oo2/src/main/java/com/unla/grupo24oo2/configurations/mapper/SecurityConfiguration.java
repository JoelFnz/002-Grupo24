package com.unla.grupo24oo2.configurations.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unla.grupo24oo2.security.CustomUserDetailsService;


@Configuration
public class SecurityConfiguration {
	
	private final CustomUserDetailsService userDetailsService;

    // Constructor para inyectar el servicio que carga los usuarios desde la base de datos
    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    // Configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // Rutas publicas: no requieren autenticacion
                .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                // Cualquier otra ruta necesita autenticacion
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Ruta personalizada para el formulario de login
                .loginPage("/login")
                // A donde redirige si el login es exitoso
                .defaultSuccessUrl("/home", true) // <-- Despues lo modificamos
                .permitAll() // Permitir el acceso al formulario a todos
            )
            .logout(logout -> logout
                // A donde redirige cuando se hace logout
                .logoutSuccessUrl("/login?logout") // <-- Despues lo modificamos
                .permitAll() // Permitir logout sin restricciones
            );

        return http.build(); // Devuelve la cadena de filtros configurada
    }
    
    // Registra el servicio que usara Spring Security para cargar los usuarios
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
    
    // Bean para encriptar contraseñas usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

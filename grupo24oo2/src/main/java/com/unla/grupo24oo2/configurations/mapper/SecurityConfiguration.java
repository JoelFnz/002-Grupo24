package com.unla.grupo24oo2.configurations.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.unla.grupo24oo2.security.CustomSuccessHandler;
import com.unla.grupo24oo2.security.CustomUserDetailsService;


@Configuration
public class SecurityConfiguration {
	
	private final CustomUserDetailsService userDetailsService;

    // Constructor para inyectar el servicio que carga los usuarios desde la base de datos
    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        System.out.println("SecurityConfiguration creada");
    }
    
    // Define el comportamiento de seguridad para las rutas HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Entrando a filterChain()");  // Marca cuando se llama a este método
        
        http
            .authorizeHttpRequests(authz -> {
                System.out.println("Configurando autorización de rutas");
                authz
                	// Rutas públicas (sin login)
                    .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                    //.anyRequest().authenticated()
                    .anyRequest().permitAll();						// <-- Despues lo cambiamos
            })
            .formLogin(form -> {
            	// Configura login con formulario
                form
                    .loginPage("/login")							// URL de la vista de login
                    .failureUrl("/login?error=true") 				// Esto redirige cuando el login falla
                    .loginProcessingUrl("/perform_login")			// Ruta que Spring intercepta para procesar el login
                    .usernameParameter("email")
                    .passwordParameter("contrasenia")
                    .successHandler(new CustomSuccessHandler())		// Usamos el manejador en lugar de defaultSuccessUrl
                    //.defaultSuccessUrl("/tickets/historial", true)	// Pagina a la que redirige tras login exitoso
                    .permitAll();
            })
            .logout(logout -> {
                System.out.println("Configurando logout");
             // Configura logout
                logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
            })
            .csrf(csrf -> {
                System.out.println("Desactivando CSRF");			// <-- Despues lo cambiamos
                csrf.disable();
            });
        
        System.out.println("Finalizando configuración del SecurityFilterChain");
        return http.build();
    }
    
    // Registra el servicio personalizado que Spring Security va a usar para cargar usuarios
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        System.out.println("Registrando UserDetailsService");
        return userDetailsService;
    }
    
    // Registra el codificador de contraseñas que se va a usar para comparar las contraseñas encriptadas
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Registrando PasswordEncoder");
        return new BCryptPasswordEncoder();
    }
    
    // Se define el AuthenticationManager para manejar la autenticación en la aplicación
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
    	// Crea un proveedor de autenticación basado en DAO, que utiliza un UserDetailsService para cargar los datos del usuario
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // Se especifica que implementación de UserDetailsService se va a usar
        authProvider.setUserDetailsService(userDetailsService);
        // Se configura el codificador de contraseñas para comparar correctamente las contraseñas encriptadas (BCrypt)
        authProvider.setPasswordEncoder(passwordEncoder);
        
        // Se retorna una instancia de ProviderManager que administra el proveedor configurado
        return new ProviderManager(authProvider);
    }

}

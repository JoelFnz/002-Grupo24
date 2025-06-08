package com.unla.grupo24oo2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.entities.Usuario;
import com.unla.grupo24oo2.services.IMailService;
import com.unla.grupo24oo2.services.IUsuarioService;

@Controller
public class RecuperacionController {

    @Autowired
    private IMailService mailService;

    @Autowired
    private IUsuarioService usuarioService; 	// funciona para Cliente y Empleado
    
    //  Metodo GET para mostrar el formulario de recuperación
    @GetMapping("/recuperar-contrasenia")
    public ModelAndView mostrarFormulario() {
        return new ModelAndView("recuperar_contrasenia");
    }

    @GetMapping("/restablecer-contrasenia")
    public ModelAndView mostrarFormularioRestablecimiento(@RequestParam String token) {
        ModelAndView mV = new ModelAndView("restablecer_contrasenia");

        // Validar el token antes de mostrar el formulario
        if (!usuarioService.validarTokenRecuperacion(token)) {
            mV.addObject("mensaje", "El enlace de recuperación no es válido o ha expirado.");
            return mV;
        }

        mV.addObject("token", token);
        return mV;
    }

    
    @PostMapping("/recuperar-contrasenia")
    public ModelAndView procesarRecuperacion(@RequestParam String email) {
        ModelAndView mV = new ModelAndView("recuperar_contrasenia");

        // Buscar usuario por email
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if (usuario == null) {
            mV.addObject("mensaje", "No existe una cuenta con ese email.");
            return mV;
        }

        // Generar token de recuperación
        String tokenRecuperacion = usuarioService.generarTokenRecuperacion(email);

        // Enviar email con el enlace de recuperación
        String asunto = "Recuperación de contraseña";
        String cuerpo = "Hola, usa este enlace para cambiar tu contraseña: "
                        + "http://localhost:8080/restablecer-contrasenia?token=" + tokenRecuperacion;
        mailService.enviarEmail(email, asunto, cuerpo);

        mV.addObject("mensaje", "Se ha enviado un email con instrucciones para recuperar la contraseña.");
        return mV;
    }
    
    @PostMapping("/restablecer-contrasenia")
    public ModelAndView procesarCambioContrasenia(@RequestParam String token, @RequestParam String nuevaContrasenia) {
        ModelAndView mV = new ModelAndView("login");

        // Guardar la nueva contraseña en la base de datos con hashing
        if (usuarioService.actualizarContrasenia(token, nuevaContrasenia)) {
            mV.addObject("mensaje", "Tu contraseña ha sido actualizada correctamente. Ahora puedes iniciar sesión.");
        } else {
            mV.setViewName("restablecer_contrasenia");
            mV.addObject("mensaje", "Error al cambiar la contraseña.");
        }

        return mV;
    }


}
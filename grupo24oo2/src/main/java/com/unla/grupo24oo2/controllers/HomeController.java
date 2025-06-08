package com.unla.grupo24oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.unla.grupo24oo2.helpers.ViewRouterHelper;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return ViewRouterHelper.HOME;
    }
}

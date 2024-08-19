package com.michito.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class LogInController {

    @GetMapping("/login")
    public String iniciar(Model model) {
        
        return "logIn";
    }

}

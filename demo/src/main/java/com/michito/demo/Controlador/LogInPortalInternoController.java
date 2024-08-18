package com.michito.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logInPortalInterno")
public class LogInPortalInternoController {

    @GetMapping("")
    public String iniciar() {
        return "logInPortaInterno";
    }
    
    @PostMapping("/redirect")
    public String redirigir() {
        return "redirect:/Mascotas/all";
    }
}

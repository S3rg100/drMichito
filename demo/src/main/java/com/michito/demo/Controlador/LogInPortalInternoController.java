package com.michito.demo.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInPortalInternoController {

    @GetMapping("/logInPortalInterno")
    public String iniciar() {
        return "logInPortaInterno";
    }
}

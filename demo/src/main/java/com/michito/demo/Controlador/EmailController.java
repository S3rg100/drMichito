package com.michito.demo.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michito.demo.Servicio.ServicioEmail;
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private ServicioEmail emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email enviado con éxito a " + to;
    }
}

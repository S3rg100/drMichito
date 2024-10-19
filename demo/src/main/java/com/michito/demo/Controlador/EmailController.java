package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.EmailRequest;
import com.michito.demo.Servicio.ServicioEmail;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private ServicioEmail emailService;

    @PostMapping("/send-email")
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        String subject = "Gracias por contactarnos, " + emailRequest.getNombre() + " " + emailRequest.getApellido();
    
        // Usamos StringBuilder para construir el body del email
        StringBuilder body = new StringBuilder();
        body.append("<div style=\"font-family: Arial, sans-serif; line-height: 1.5; color: #333;\">");
        body.append("<h2 style=\"color: #4CAF50;\">¡Gracias por contactarnos, ")
            .append(emailRequest.getNombre())
            .append(" ")
            .append(emailRequest.getApellido())
            .append("!</h2>");
        body.append("<p>Recibimos tu mensaje: ")
            .append(emailRequest.getBody())
            .append("</p>");
        
        body.append("<p>Estimado/a ")
            .append(emailRequest.getNombre())
            .append(" ")
            .append(emailRequest.getApellido())
            .append(",</p>");
        body.append("<p>Gracias por contactarnos en <strong>Dr. Michito</strong>, donde nos dedicamos a ofrecer los mejores servicios médicos y de aseo para mascotas.</p>");
        
        body.append("<h3>🐾 Servicios disponibles:</h3>");
        body.append("<ul>")
            .append("<li>Cuidado veterinario general</li>")
            .append("<li>Limpieza y aseo</li>")
            .append("<li>Consultas de especialidades</li>")
            .append("<li>Cirugías y procedimientos</li>")
            .append("<li>Urgencias 24/7</li>")
            .append("</ul>");
        
        body.append("<p>Si deseas más información o tienes alguna consulta, no dudes en contactarnos a través de nuestro teléfono o <a href=\"http://localhost:4200\">nuestra página web</a>.</p>");
        
        body.append("<p>Atentamente,<br>El equipo de <strong>Dr. Michito</strong></p>");
        body.append("<p style=\"font-style: italic; color: #888;\">🐾 \"Cuidamos a tus amigos de cuatro patas como si fueran los nuestros\" 🐾</p>");
        body.append("</div>");

        
        emailService.sendEmail(emailRequest.getEmail(), subject, body.toString());
       
    }
}
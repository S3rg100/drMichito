package com.michito.demo.Controlador;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.michito.demo.Entidades.EmailRequest;
import com.michito.demo.Entidades.EmailRequestWithFile;
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
        body.append("<p>Hemos recibido tu mensaje: ")
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
            .append("<li>Profilaxis</li>")
            .append("<li>Vacunación</li>")
            .append("<li>Urgencias 24/7</li>")
            .append("</ul>");
        
        body.append("<p>Pronto uno de nuestros asesores se comunicará contigo para brindarte asesoramiento personalizado. Puedes contactarnos a través de nuestro teléfono o <a href=\"http://localhost:4200\">nuestra página web</a>.</p>");
        
        body.append("<p>Atentamente,<br>El equipo de <strong>Dr. Michito</strong></p>");
        body.append("<p style=\"font-style: italic; color: #888;\">🐾 \"Cuidamos a tus amigos de cuatro patas como si fueran los nuestros\" 🐾</p>");
        body.append("</div>");

        
        emailService.sendEmail(emailRequest.getEmail(), subject, body.toString());
       
    }


    @PostMapping("/send-email-with-attachment")
    public void sendEmailWithAttachment(@ModelAttribute EmailRequestWithFile requestWithFile) throws MessagingException, IOException {
        
        String subject = requestWithFile.getAsunto();

        // Construimos el contenido HTML del correo
        StringBuilder body = new StringBuilder();
        body.append("<div style=\"font-family: Arial, sans-serif; line-height: 1.5; color: #333;\">");
        body.append("<h2 style=\"color: #4CAF50;\">Detalles del Tratamiento para ").append(requestWithFile.getNombreMascota()).append("</h2>");
        
        body.append("<p>Estimado/a cliente,</p>");
        body.append("<p>Gracias por confiar en <strong>Dr. Michito</strong>. A continuación, encontrarás los detalles del tratamiento realizado a tu mascota.</p>");
        
        body.append("<p><strong>Veterinario a cargo:</strong> ").append(requestWithFile.getNombreVeterinario()).append("</p>");
        body.append("<p><strong>Fecha del tratamiento:</strong> ").append(requestWithFile.getFechaTratamiento()).append("</p>");
        body.append("<p><strong>Detalles:</strong> ").append(requestWithFile.getBody()).append("</p>");

        body.append("<p>Para cualquier consulta adicional, puedes contactarnos en nuestro teléfono o en <a href=\"http://localhost:4200\">nuestra página web</a>.</p>");
        
        body.append("<p>Atentamente,<br>El equipo de <strong>Dr. Michito</strong></p>");
        body.append("<p style=\"font-style: italic; color: #888;\">🐾 \"Cuidamos a tus amigos de cuatro patas como si fueran los nuestros\" 🐾</p>");
        body.append("</div>");

        // Envía el correo con el archivo adjunto
        emailService.sendEmailWithAttachment(
            requestWithFile.getEmailCliente(),
            subject,
            body.toString(),
            requestWithFile.getFile().getBytes(),
            requestWithFile.getFile().getOriginalFilename()
        );
    }
}
package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Servicio.Servicio;

@Controller
@RequestMapping("/logInPortalInterno")
public class LogInPortalInternoController {

    @Autowired
    private Servicio serv;

    @GetMapping("")
    public String iniciar(Model model) {
        model.addAttribute("loginForm", new Login("", ""));
        return "logInPortaInterno";
    }

    @PostMapping("")
    public String procesarLogin(@ModelAttribute("loginForm") Login loginForm, Model model) {
        System.out.println(loginForm);
        Login login = serv.SearchByUsuario(loginForm.getUsuario());
        if (login != null && login.getPassword().equals(loginForm.getPassword())) {
            return "redirect:/Mascotas/all";
        } else {
            // Si los datos son incorrectos, añade un mensaje de error al modelo
            model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtelo de nuevo.");
            model.addAttribute("loginForm", loginForm); // Mantiene los datos del formulario
            return "logInPortaInterno"; 
        }
    }
}

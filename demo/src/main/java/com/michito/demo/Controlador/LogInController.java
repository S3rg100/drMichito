package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Servicio.ServicioCliente;

@Controller
@RequestMapping("/login")
public class LogInController {

    @Autowired
    private ServicioCliente serv;

    @GetMapping("")
    public String iniciar(Model model) {
        model.addAttribute("loginForm", new Cliente());
        return "logIn";
    }

    @PostMapping("")
    public String procesarLogin(@ModelAttribute("loginForm") Cliente loginForm, Model model) {
        String cedula = loginForm.getCedula();
        Cliente cliente = serv.findByCedula(cedula);

        if (cliente != null) {
            return "redirect:/Clientes/VistaMascotas/" + cliente.getId();
        } else {
            model.addAttribute("error", "Cédula ingresada no encontrada. Inténtelo de nuevo.");
            model.addAttribute("loginForm", loginForm);
            return "login";
        }
    }

}

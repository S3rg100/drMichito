package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michito.demo.Servicio.Servicio;

@Controller
@RequestMapping("/Mascotas")
public class MascotaController {
    @Autowired
    Servicio serv;

    @GetMapping("/info/{id}")
    public String mostrarMascota(Model model, @PathVariable("id") int identificador) {
        model.addAttribute("Mascotas", serv.searchById(identificador));
        return "Mascotas";
    }

    @GetMapping("/all")
    public String mostrarMascota(Model model) {
        model.addAttribute("Mascotas", serv.searchAll());
        return "vistaMascotas";
    }

    @GetMapping("/agregar")
    public String Agregar(Model model) {
        return "agregarMascota";
    }
}

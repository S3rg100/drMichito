package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michito.demo.Entidades.Mascota;
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
    public String redirigirAgregar(Model model) {
        Mascota newMascota = new Mascota(0, "", 0, 0, "", "");
        model.addAttribute("mascota", newMascota);
        return "agregarMascota";
    }

    @PostMapping("/agregar")
    public String Agregar(@ModelAttribute ("mascota") Mascota newMascota) {
        
        serv.addMascota(newMascota);
        return "redirect:/Mascotas/all";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int identificador, Model model) {
        Mascota mascota = serv.searchById(identificador);
        model.addAttribute("mascota", mascota);
        return "editarMascota";
    }

    @PostMapping("/editar/{id}")
    public String editarMascota(@PathVariable("id") int identificador, @ModelAttribute("mascota") Mascota mascotaEditada) {
        mascotaEditada.setId(identificador);
        serv.updateMascota(mascotaEditada);
        return "redirect:/Mascotas/all";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") int identificador) {
        serv.eliminarMascota(identificador);
        return "redirect:/Mascotas/all";
    }

    
}
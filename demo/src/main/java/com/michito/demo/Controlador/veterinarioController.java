package com.michito.demo.Controlador;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Servicio.ServicioVeterinario;

@RestController
@Controller
@RequestMapping("/Veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class veterinarioController {
    @Autowired
    ServicioVeterinario veterinarioServicio;




    

    @GetMapping("/info/{id}")
    public Veterinario mostrarVeterinarios(Model model, @PathVariable("id") Long identificador) {

        return veterinarioServicio.searchById(identificador);
    }

    @GetMapping("/all")
    public List<Veterinario> mostrar(Model model) {
        return veterinarioServicio.searchAll();
    }

    @PostMapping("/agregar")
    public void Agregar(@RequestBody Veterinario veterinario) {
        veterinarioServicio.addVeterinario(veterinario);
       
    }

  

    @PutMapping("/editar/{id}")
    public void editarVeterinario(@RequestBody Veterinario veterinarioEditado) {
        veterinarioServicio.updateVeterinario(veterinarioEditado);
    }

    

    @DeleteMapping("/delete/{id}")
    public void eliminarVeterinarioa(@PathVariable("id") Long identificador) {
        veterinarioServicio.eliminarVeterinario(identificador);
       
    }

   

    @GetMapping("/vistaDetalle/{id}")
    public Veterinario verDetallesMascotaVistaCliente(@PathVariable("id") Long id) {
        return veterinarioServicio.searchById(id);
    }

}
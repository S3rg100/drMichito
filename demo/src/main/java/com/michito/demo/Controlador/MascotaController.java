package com.michito.demo.Controlador;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Controller
@RequestMapping("/Mascotas")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {
    @Autowired
    ServicioMascota mascotaServicio;
    @Autowired
    ServicioCliente clienteServicio;

   
  

    @GetMapping("/info/{id}")
    public Mascota mostrarMascota(Model model, @PathVariable("id") Long identificador) {

        return mascotaServicio.searchById(identificador);
    }

    @GetMapping("/all")
    public List<Mascota> mostrar(Model model) {
        return mascotaServicio.searchAll();
    }

    @PostMapping("/agregar")
    public void redirigirAgregar(@RequestBody Mascota mascota) {
        mascotaServicio.addMascota(mascota);
    }

    

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long identificador, Model model) {
        
        Mascota mascota = mascotaServicio.searchById(identificador);
        System.out.println("metodo get"+mascota.getCliente().getNombre());
        model.addAttribute("mascota", mascota);
        return "UpdateMascota";
    }

    @PutMapping("/editar/{id}")
public void editarMascota(@RequestBody Mascota mascotaEditada) {

    mascotaServicio.updateMascota(mascotaEditada);

}

    

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMascota(@PathVariable("id") Long identificador) {
        mascotaServicio.eliminarMascota(identificador);
       
    }

   

    @GetMapping("/vistaDetalle/{id}")
    public Mascota verDetallesMascotaVistaCliente(@PathVariable("id") Long id) {
        return mascotaServicio.searchById(id);
    }


}

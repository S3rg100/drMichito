package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/Clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {
    @Autowired
    ServicioCliente clienteServicio;
    @Autowired
    ServicioMascota mascotaServicio;



    @PostMapping("/agregar")
    public void crearCliente(@RequestBody Cliente cliente) {
        clienteServicio.addCliente(cliente);
    }


    @GetMapping("/all")
    public List<Cliente> mostrar(Model model) {
        return clienteServicio.searchAllClientes();
    }

    @PutMapping("/update/{id}")
    public void actualizarForm(@RequestBody Cliente cliente) {
        clienteServicio.updateCliente(cliente);
    }

    

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable("id") Long identificador) {
        clienteServicio.deleteCliente(identificador);
    }

    //revisar más tarde
    @GetMapping("/Mascotas/{id}")
    public String mascotasDeCliente(Model model, @PathVariable("id") Long identificador) {
        Cliente cliente = clienteServicio.searchByIdCliente(identificador);
        model.addAttribute("Mascotas", cliente.getMascotas());
        return "ReadMascotas";
    }

    @GetMapping("/VistaMascotas/{id}")
    public String vistaMascotasDeCliente(Model model, @PathVariable("id") Long identificador) {
        Cliente cliente = clienteServicio.searchByIdCliente(identificador);
        model.addAttribute("cliente", cliente);
        model.addAttribute("mascotas", cliente.getMascotas());
        return "ReadMascotasVistaCliente";
    }

    @GetMapping("/Mascotas/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long identificador, Model model) {
        Mascota mascota = mascotaServicio.searchById(identificador);
        model.addAttribute("mascota", mascota);
        return "UpdateMascota";
    }

    @PostMapping("/editar/{id}")
    public String editarMascota(@PathVariable("id") Long identificador,
            @ModelAttribute("mascota") Mascota mascotaEditada) {
        mascotaEditada.setId(identificador);
        mascotaServicio.updateMascota(mascotaEditada);
        return "redirect:/Mascotas/all";
    }

}

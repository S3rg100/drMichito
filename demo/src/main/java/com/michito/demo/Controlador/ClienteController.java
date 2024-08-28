package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Servicio.ServicioCliente;


@Controller
@RequestMapping("/Clientes")
public class ClienteController {
    @Autowired
    ServicioCliente clienteServicio;


    @GetMapping("/agregar")
    public String Agregar(Model model) {
        Cliente c = new Cliente("","","",0);
        model.addAttribute("cliente", c);
        return "CreateCliente";
    }
    

    @GetMapping("/info/{id}")
    public String mostrar(Model model, @PathVariable("id") Long identificador) {
        model.addAttribute("Clientes", clienteServicio.searchByIdCliente(identificador));
        return "ReadClientes";
    }
    @GetMapping("/all")
    public String mostrar(Model model) {
        model.addAttribute("Clientes", clienteServicio.searchAllClientes());
        return "ReadClientes";      
    }   
    @GetMapping("/update/{id}")
    public String actualizarForm(Model model, @PathVariable("id") Long identificador) {
        model.addAttribute("cliente", clienteServicio.searchByIdCliente(identificador));
        return "UpdateCliente";
    }
    

    @PostMapping("/update/{id}")
    public String actualizar(@ModelAttribute("cliente") Cliente cliente) {
        clienteServicio.updateCliente(cliente);
        return "redirect:/Clientes/all";
    }
    
    @GetMapping("/delete/{id}")
    public String Eliminar( @PathVariable("id") Long identificador) {
        clienteServicio.deleteCliente(identificador);
        return "redirect:/Clientes/all";
    }
    

   
    @GetMapping("/Mascotas/{id}")
    public String mascotasDeCliente(Model model, @PathVariable("id") Long identificador) {
        Cliente cliente = clienteServicio.searchByIdCliente(identificador);
        model.addAttribute("Mascotas", cliente.getMascotas());
        return "ReadMascotas";
    }
    
  
    

}

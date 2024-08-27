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
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.Servicio;


@Controller
@RequestMapping("/Clientes")
public class ClienteCOntroller {
    @Autowired
    Servicio serv;


    @GetMapping("/agregar")
    public String Agregar(Model model) {
        Cliente c = new Cliente("","","",0);
        model.addAttribute("cliente", c);
        return "addCliente";
    }
    

    @GetMapping("/info/{id}")
    public String mostrar(Model model, @PathVariable("id") Long identificador) {
        model.addAttribute("Clientes", serv.searchByIdCliente(identificador));
        return "Clientes";
    }
    @GetMapping("/all")
    public String mostrar(Model model) {
        model.addAttribute("Clientes", serv.searchAllClientes());
        return "Clientes";      
    }   
    @GetMapping("/update/{id}")
    public String actualizarForm(Model model, @PathVariable("id") Long identificador) {
        model.addAttribute("cliente", serv.searchByIdCliente(identificador));
        return "editarCliente";
    }
    

    @PostMapping("/update/{id}")
    public String actualizar(@ModelAttribute("cliente") Cliente cliente) {
        serv.updateCliente(cliente);
        return "redirect:/Clientes/all";
    }
    
    @GetMapping("/delete/{id}")
    public String Eliminar(Model model, @PathVariable("id") Long identificador) {
         serv.deleteCliente(identificador);
        return "redirect:/Clientes/all";
    }

   
    @GetMapping("/Mascotas/{id}")
    public String mascotasDeCliente(Model model, @PathVariable("id") Long identificador) {
        Cliente c = serv.searchByIdCliente(identificador);
        model.addAttribute("Mascotas", c.getMascotas());
        return "vistaMascotas";
    }
    
  
    

}

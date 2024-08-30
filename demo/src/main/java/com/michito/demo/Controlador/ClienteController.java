package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.michito.demo.Servicio.ServicioMascota;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.ServicioCliente;


@Controller
@RequestMapping("/Clientes")
public class ClienteController {
    @Autowired
    ServicioCliente clienteServicio;
    @Autowired
    ServicioMascota mascotaServicio;

    @GetMapping("/agregar")
    public String Agregar(Model model) {
        Cliente c = new Cliente("","","",0);
        model.addAttribute("cliente", c);
        return "CreateCliente";
    }

    @PostMapping("/agregar")
    public String crearCliente(@ModelAttribute("cliente") Cliente nuevoCliente) {
        clienteServicio.addCliente(nuevoCliente);
        return "redirect:/Clientes/all";
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
public String actualizar(@ModelAttribute("cliente") Cliente clienteActualizado, @PathVariable("id") Long id) {
    // Cargar el cliente existente desde la base de datos
    Cliente clienteExistente = clienteServicio.searchByIdCliente(id);

    if (clienteExistente != null) {
        // Actualizar solo los campos del cliente, sin tocar la lista de mascotas
        clienteExistente.setCedula(clienteActualizado.getCedula());
        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        clienteExistente.setCelular(clienteActualizado.getCelular());

        // Guardar los cambios en el cliente
        clienteServicio.updateCliente(clienteExistente);
    }

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

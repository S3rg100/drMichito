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
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;

@Controller
@RequestMapping("/Mascotas")
public class MascotaController {
    @Autowired
    ServicioMascota mascotaServicio;
    @Autowired
    ServicioCliente clienteServicio;

   
  

    @GetMapping("/info/{id}")
    public String mostrarMascota(Model model, @PathVariable("id") Long identificador) {

        model.addAttribute("Mascotas", mascotaServicio.searchById(identificador));
        return "ReadMascotas";
    }

    @GetMapping("/all")
    public String mostrar(Model model) {
        model.addAttribute("Mascotas", mascotaServicio.searchAll());// nombre "Mascota"
        return "ReadMascotas";
    }

    @GetMapping("/agregar")
    public String redirigirAgregar(Model model) {
        Mascota newMascota = new Mascota("", 0, 0, "");
        model.addAttribute("mascota", newMascota);
        return "CreateMascota";
    }

    @PostMapping("/agregar")
    public String agregarMascota(Mascota mascota, Model model) {
        String cedulaCliente = mascota.getCedulaCliente();
        Cliente cliente = clienteServicio.findByCedula(cedulaCliente);

        if (cliente != null) {
            mascota.setCliente(cliente);
            mascotaServicio.addMascota(mascota);
            return "redirect:/Mascotas/all";
        } else {
            model.addAttribute("error", "Cliente no encontrado");
            return "CreateMascota";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long identificador, Model model) {
        
        Mascota mascota = mascotaServicio.searchById(identificador);
        System.out.println("metodo get"+mascota.getCliente().getNombre());
        model.addAttribute("mascota", mascota);
        return "UpdateMascota";
    }

    @PostMapping("/editar/{id}")
public String editarMascota(@PathVariable("id") Long id,@ModelAttribute("mascota") Mascota mascotaEditada) {
    
    if (mascotaEditada.getCliente() != null && mascotaEditada.getCliente().getId() != null) {
        Cliente cliente = clienteServicio.searchByIdCliente(mascotaEditada.getCliente().getId());
        mascotaEditada.setCliente(cliente);
    } 
    mascotaServicio.updateMascota(mascotaEditada);

    return "redirect:/Mascotas/all";
}

    

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long identificador) {
        mascotaServicio.eliminarMascota(identificador);
        return "redirect:/Mascotas/all";
    }

    @GetMapping("/detalle/{id}")
    public String verDetallesMascota(@PathVariable("id") Long id, Model model) {
        Mascota mascota = mascotaServicio.searchById(id);  
        if (mascota != null) {
            model.addAttribute("Mascota", mascota);
            return "ReadMascota";
        } else {
            model.addAttribute("errorMessage", "Mascota no encontrada");
            return "error";  
        }
    }

    @GetMapping("/vistaDetalle/{id}")
    public String verDetallesMascotaVistaCliente(@PathVariable("id") Long id, Model model) {
        Mascota mascota = mascotaServicio.searchById(id);  
        if (mascota != null) {
            model.addAttribute("Mascota", mascota);
            return "ReadMascotaVistaCliente";
        } else {
            model.addAttribute("errorMessage", "Mascota no encontrada");
            return "error";  
        }
    }


}

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
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;
import com.michito.demo.Servicio.Servicio;

@Controller
@RequestMapping("/Mascotas")
public class MascotaController {
    @Autowired
    Servicio serv;

    @Autowired
    private MascotasRepositorio mascotasRepositorio;

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @GetMapping("/info/{id}")
    public String mostrarMascota(Model model, @PathVariable("id") Long identificador) {

        model.addAttribute("Mascotas", serv.searchById(identificador));
        return "vistaMascotas";
    }

    @GetMapping("/all")
    public String mostrar(Model model) {
        model.addAttribute("Mascotas", serv.searchAll());// nombre "Mascota"
        return "vistaMascotas";
    }

    @GetMapping("/agregar")
    public String redirigirAgregar(Model model) {
        Mascota newMascota = new Mascota("", 0, 0, "");
        model.addAttribute("mascota", newMascota);
        return "agregarMascota";
    }

    @PostMapping("/agregar")
    public String agregarMascota(Mascota mascota, Model model) {
        String cedulaCliente = mascota.getCedulaCliente();
        Cliente cliente = clientesRepositorio.findByCedula(cedulaCliente);

        if (cliente != null) {
            mascota.setCliente(cliente);
            mascotasRepositorio.save(mascota);
            return "redirect:/Mascotas/all";
        } else {
            model.addAttribute("error", "Cliente no encontrado");
            return "agregarMascota";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long identificador, Model model) {
        Mascota mascota = serv.searchById(identificador);
        model.addAttribute("mascota", mascota);
        return "editarMascota";
    }

    @PostMapping("/editar/{id}")
    public String editarMascota(@PathVariable("id") int identificador,
            @ModelAttribute("mascota") Mascota mascotaEditada) {
        mascotaEditada.setId(identificador);
        serv.updateMascota(mascotaEditada);
        return "redirect:/Mascotas/all";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable("id") Long identificador) {
        serv.eliminarMascota(identificador);
        return "redirect:/Mascotas/all";
    }

    @GetMapping("/detalle/{id}")
public String verDetallesMascota(@PathVariable("id") Long id, Model model) {
    Mascota mascota = serv.searchById(id);  // Usa el método correcto del servicio
    if (mascota != null) {
        model.addAttribute("Mascota", mascota);
        return "detalleMascota";
    } else {
        model.addAttribute("errorMessage", "Mascota no encontrada");
        return "error";  // Redirige a una página de error o muestra un mensaje adecuado
    }
}


}

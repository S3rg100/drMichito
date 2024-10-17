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
import org.springframework.web.bind.annotation.RequestParam;
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
    public void editarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        Veterinario veterinarioExistente = veterinarioServicio.searchById(id);
        
        if (veterinarioExistente != null) {
            // Mantener el login existente
            veterinario.setLogin(veterinarioExistente.getLogin());
            
            // Actualizar otros campos del veterinario
            veterinarioExistente.setNombre(veterinario.getNombre());
            veterinarioExistente.setEspecialidad(veterinario.getEspecialidad());
            veterinarioExistente.setEstado(veterinario.isEstado());
            
            // Guardar el veterinario actualizado
            veterinarioServicio.addVeterinario(veterinarioExistente);
            
        } 
    }

    

    @DeleteMapping("/delete/{id}")
    public void eliminarVeterinarioa(@PathVariable("id") Long identificador) {
        veterinarioServicio.eliminarVeterinario(identificador);
       
    }

    @GetMapping("/buscar")
public List<Veterinario> buscarVeterinariosPorNombre(@RequestParam String nombre) {
    return veterinarioServicio.buscarPorNombre(nombre);
}


   

    @GetMapping("/vistaDetalle/{id}")
    public Veterinario verDetallesMascotaVistaCliente(@PathVariable("id") Long id) {
        return veterinarioServicio.searchById(id);
    }

}

package com.michito.demo.Controlador;
import java.util.List;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.michito.demo.Entidades.Login;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Entidades.VeterinarioDTO;
import com.michito.demo.Servicio.ServicioLogin;
import com.michito.demo.Servicio.ServicioVeterinario;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@Controller
@RequestMapping("/Veterinarios")
@CrossOrigin(origins = "http://localhost:4200")
public class veterinarioController {
    @Autowired
    ServicioVeterinario veterinarioServicio;
    @Autowired
    ServicioLogin loginServicio;

    @GetMapping("/info/{id}")
    public Veterinario mostrarVeterinarios(Model model, @PathVariable("id") Long identificador) {

        return veterinarioServicio.searchById(identificador);
    }

    @GetMapping("/all")
    public List<Veterinario> mostrar(Model model) {
        return veterinarioServicio.searchAll();
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarVeterinario(@RequestBody VeterinarioDTO dto) {
        try {
            Veterinario veterinario = new Veterinario();
            veterinario.setId(dto.getId());
            veterinario.setCedula(dto.getCedula());
            veterinario.setNombre(dto.getNombre());
            veterinario.setCorreo(dto.getCorreo());
            veterinario.setCelular(dto.getCelular());
            veterinario.setEspecialidad(dto.getEspecialidad());
            veterinario.setEstado(dto.isEstado());

            Login login = new Login();
            login.setUsuario(dto.getUsuario());
            login.setPasswords(dto.getPasswords());
            login.setRoles(dto.getRoles());
            loginServicio.save(login);
            veterinario.setLogin(login);

            veterinarioServicio.addVeterinario(veterinario);
            return ResponseEntity.ok("Veterinario guardado correctamente");
        } catch (Exception e) {
            // Imprimir el error en el log para diagnóstico
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el veterinario: " + e.getMessage());
        }
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

    @GetMapping("/detalles")
    public ResponseEntity<Veterinario> buscarVeterinario (){
        Veterinario veterinario = veterinarioServicio.searchByUsernameLogin(
            
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (veterinario == null) {
            return new ResponseEntity<Veterinario>(veterinario, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Veterinario>(veterinario, HttpStatus.OK);
    }

}

package com.michito.demo.Controlador;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.MascotaDTO;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;

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
    public MascotaDTO obtenerMascotaPorId(@PathVariable Long id) {
        Mascota mascota = mascotaServicio.searchById(id);
        
        // Si la mascota tiene un cliente, obtenemos la cédula del cliente
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setPeso(mascota.getPeso());
        dto.setEdad(mascota.getEdad());
        dto.setFoto(mascota.getFoto());
        if (mascota.getCliente() != null) {
            dto.setCedulaCliente(mascota.getCliente().getCedula());  // Aquí vinculamos la cédula del cliente
        } else {
            dto.setCedulaCliente("");  // Si no hay cliente vinculado, dejamos el campo vacío
        }

        return dto;
    }


   
    @GetMapping("/all")
    public List<MascotaDTO> mostrar(Model model) {
        // Recuperar todas las mascotas desde el servicio
        List<Mascota> mascotas = mascotaServicio.searchAll();
        
        // Crear una lista para almacenar los DTO de las mascotas
        List<MascotaDTO> mascotasDTO = new ArrayList<>();
        
        // Convertir cada Mascota a MascotaDTO
        for (Mascota m : mascotas) {
            MascotaDTO dto = new MascotaDTO();
            dto.setId(m.getId());
            dto.setNombre(m.getNombre());
            dto.setPeso(m.getPeso());
            dto.setEdad(m.getEdad());
            dto.setFoto(m.getFoto());
            
            if (m.getCliente() != null) {
                dto.setCedulaCliente(m.getCliente().getCedula());
            } else {
                dto.setCedulaCliente(""); 
            }
            
            mascotasDTO.add(dto);
        }
        
        return mascotasDTO;
    }
    

    @PostMapping("/agregar")
    public void redirigirAgregar(@RequestBody Mascota mascota) {
        Cliente cliente = clienteServicio.findByCedula(mascota.getCedulaCliente());
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        } else {
            mascota.setCliente(cliente);
            mascotaServicio.addMascota(mascota);
        }
       
    }

    
 
    @PutMapping("/editar/{id}")
    public void editarMascota(@RequestBody Mascota mascotaEditada) {
        Cliente clienteMascota = clienteServicio.searchByMascotaId(mascotaEditada.getId());
        if (clienteMascota == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        } else {
            mascotaEditada.setCedulaCliente(clienteMascota.getCedula());
            mascotaEditada.setCliente(clienteMascota);
            mascotaServicio.updateMascota(mascotaEditada);
        }
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

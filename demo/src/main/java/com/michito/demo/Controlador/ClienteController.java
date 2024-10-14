package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;

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

    @GetMapping("/{cedula}")
    public Cliente obtenerCliente(@PathVariable("cedula") String cedula) {
        Cliente cliente = clienteServicio.findByCedula(cedula);
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        return cliente;
    }

    @GetMapping("/info/{id}")
    public Cliente obtenerClientePorId(@PathVariable("id") Long identificador) {
        Cliente cliente = clienteServicio.searchByIdCliente(identificador);
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        return cliente;
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorCliente(@PathVariable Long clienteId) {
        List<Mascota> mascotas = mascotaServicio.obtenerMascotasPorCliente(clienteId);
        
        // Recorrer las mascotas y setear la cédula del cliente
        for (Mascota mascota : mascotas) {
            // Asegúrate de que el cliente esté cargado para cada mascota
            if (mascota.getCliente() != null) {
                mascota.setCedulaCliente(mascota.getCliente().getCedula());  // Asignar la cédula del cliente
            }
        }
        
        return ResponseEntity.ok(mascotas);
    }




    @PutMapping("/update/{id}")
    public void actualizarForm(@RequestBody Cliente cliente) {
        clienteServicio.updateCliente(cliente);
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable("id") Long identificador) {
        clienteServicio.deleteCliente(identificador);
    }

    // revisar más tarde
    @GetMapping("/Mascotas/{id}")
    public List<Mascota> mascotasDeCliente(@PathVariable("id") Long identificador) {
        return mascotaServicio.searchByIdCliente(identificador);
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

    @GetMapping("/buscar")
    public List<Cliente> buscarClientesConteniendoPorCedula(@RequestParam("cedula") String cedula) {
        return clienteServicio.buscarClientesPorCedula(cedula);
    }

}

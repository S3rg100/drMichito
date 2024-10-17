package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Servicio.ServicioTratamiento;


@CrossOrigin(origins = "http://localhost:4200")  // Reemplaza con la URL de tu frontend
@RestController
@RequestMapping("/Tratamientos")
public class TratamientoController {

    @Autowired
    private ServicioTratamiento tratamientoService;

    // Endpoint para obtener todos los medicamentos
    @GetMapping("/all")
    public ResponseEntity<List<Tratamieneto>> listarMedicamentos() {
        List<Tratamieneto> tratamientos = tratamientoService.findAll();
        return ResponseEntity.ok(tratamientos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarTratamiento(@PathVariable Long id) {
        tratamientoService.eliminarTratamiento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/agregar")
    public ResponseEntity<Tratamieneto> agregarTratamiento(@RequestBody Tratamieneto tratamiento) {
        Tratamieneto nuevoTratamiento = tratamientoService.addTratamiento(tratamiento);
        return ResponseEntity.ok(nuevoTratamiento);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Tratamieneto> editarTratamiento(@PathVariable Long id, @RequestBody Tratamieneto tratamiento) {
        Tratamieneto tratamientoActualizado = tratamientoService.updateTratamiento(id, tratamiento);
        return ResponseEntity.ok(tratamientoActualizado);
    }

    @GetMapping("/tratamiento/{id}")
    public Tratamieneto obtenerTratamiento(@PathVariable Long id) {
        Tratamieneto tratamiento = tratamientoService.obtenerPorId(id);
        System.out.println("Medicamentos del tratamiento: " + tratamiento.getMedicamentos()); // Verificar si los medicamentos son traídos
        return tratamiento;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Tratamieneto> obtenerTratamientoPorId(@PathVariable Long id) {
        Tratamieneto tratamiento = tratamientoService.findById(id);
        if (tratamiento != null) {
            return ResponseEntity.ok(tratamiento);
        } else {
            return ResponseEntity.status(404).build();  // Si no encuentra el tratamiento, retorna 404
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Tratamieneto>> obtenerTratamientosPorCliente(@PathVariable Long clienteId) {
        List<Tratamieneto> tratamientos = tratamientoService.obtenerTratamientosPorCliente(clienteId);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }

    // En el controlador del backend
    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<Tratamieneto>> obtenerTratamientosPorVeterinario(@PathVariable Long veterinarioId) {
        List<Tratamieneto> tratamientos = tratamientoService.obtenerTratamientosPorVeterinario(veterinarioId);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Tratamieneto>> obtenerTratamientosPorMascota(@PathVariable Long mascotaId) {
        List<Tratamieneto> tratamientos = tratamientoService.obtenerTratamientosPorMascota(mascotaId);
        if (tratamientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tratamientos);
    }


        
}

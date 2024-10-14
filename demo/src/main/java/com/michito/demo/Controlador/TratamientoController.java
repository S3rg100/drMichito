package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}

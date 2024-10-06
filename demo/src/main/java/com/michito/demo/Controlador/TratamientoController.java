package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Repositorio.TratamientoRepositorio;

@RestController
@RequestMapping("/Tratamientos")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientoController {

    @Autowired
    private TratamientoRepositorio tratamienetoRepositorio;

    // Endpoint para obtener todos los medicamentos
    @GetMapping("/all")
    public ResponseEntity<List<Tratamieneto>> listarMedicamentos() {
        List<Tratamieneto> tratamientos = tratamienetoRepositorio.findAll();
        return ResponseEntity.ok(tratamientos);
    }
}

package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Servicio.ServicioMedicamento;

@RestController
@RequestMapping("/Medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    private ServicioMedicamento servicioMedicamento;

    // Endpoint para obtener todos los medicamentos
    @GetMapping("/all")
    public ResponseEntity<List<Medicamento>> listarMedicamentos() {
        List<Medicamento> medicamentos = servicioMedicamento.obtenerMedicamentos();
        return ResponseEntity.ok(medicamentos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Long id) {
        servicioMedicamento.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();  // Devolver 204 si se eliminó correctamente
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Medicamento> actualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamentoActualizado) {
        Medicamento medicamento = servicioMedicamento.actualizarMedicamento(id, medicamentoActualizado);
        return ResponseEntity.ok(medicamento);  // Devolver el medicamento actualizado
    }


}
package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Servicio.ServicioKPIs;

@RestController
@RequestMapping("/kpis")
@CrossOrigin(origins = "http://localhost:4200")
public class KPIsController {

    @Autowired
    private ServicioKPIs kpiService;


    @GetMapping("/total-mascotas")
    public Long getTotalMascotas() {
        return kpiService.getTotalMascotas();
    }

    @GetMapping("/tratamientos-ultimo-mes")
    public Long getTratamientosUltimoMes() {
        return kpiService.getTratamientosUltimoMes();
    }

    @GetMapping("/mascotas-activas")
    public Long getMascotasActivas() {
        return kpiService.getMascotasConTratamientos();
    }

    @GetMapping("/veterinarios-activos")
    public Long getVeterinariosActivos() {
        return kpiService.getVeterinariosActivos();
    }

    @GetMapping("/veterinarios-inactivos")
    public Long getVeterinariosInactivos() {
        return kpiService.getVeterinariosInactivos();
    }

    @GetMapping("/tratamientos-por-medicamento")
    public List<Object[]> getTratamientosPorMedicamento() {
        return kpiService.getTratamientosPorMedicamento();
    }
}

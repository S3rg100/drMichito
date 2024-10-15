package com.michito.demo.Servicio;

import java.util.List;

public interface ServicioKPIs {

    public Long getTotalMascotas();
    public Long getTratamientosUltimoMes();
    public Long getMascotasConTratamientos();
    public Long getVeterinariosActivos();
    public Long getVeterinariosInactivos();
    public List<Object[]> getTratamientosPorMedicamento();
}
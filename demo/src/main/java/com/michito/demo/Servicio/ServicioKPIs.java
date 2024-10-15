package com.michito.demo.Servicio;

import java.util.List;

import com.michito.demo.Entidades.Medicamento;

public interface ServicioKPIs {

    public Long getTotalMascotas();
    public Long getTratamientosUltimoMes();
    public Long getMascotasConTratamientos();
    public Long getVeterinariosActivos();
    public Long getVeterinariosInactivos();
    public List<Object[]> getTratamientosPorMedicamento();
    public double getGananciaTotales();
    public double getVentasTotales();
    public List<Medicamento> obtenerTop3MedicamentosMasVendidos();
}

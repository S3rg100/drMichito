package com.michito.demo.Servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Repositorio.MascotasRepositorio;
import com.michito.demo.Repositorio.MedicamentoRepositorio;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Repositorio.VeterinarioRepositorio;

@Service
public class ServicioKPIsImplementacion implements ServicioKPIs {

    @Autowired
    private TratamientoRepositorio tratamientoRepositorio;

    @Autowired
    private VeterinarioRepositorio veterinarioRepositorio;

    @Autowired
    private MascotasRepositorio mascotaRepositorio;

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Override
    public Long getTotalMascotas() {
        return mascotaRepositorio.countTotalMascotas();
    }

    @Override
    public Long getTratamientosUltimoMes() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(2);
        return tratamientoRepositorio.countTratamientosUltimoMes(startDate, endDate);
    }

    @Override
    public Long getMascotasConTratamientos() {
        return tratamientoRepositorio.countMascotasConTratamiento();
    }

    @Override
    public Long getVeterinariosActivos() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(12);
        return tratamientoRepositorio.countVeterinariosActivos(startDate, endDate);
    }

    @Override
    public Long getVeterinariosInactivos() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(12);
        return veterinarioRepositorio.countVeterinariosInactivos(startDate, endDate);
    }

    @Override
    public List<Object[]> getTratamientosPorMedicamento() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);
        return tratamientoRepositorio.countTratamientosPorMedicamento(startDate, endDate);
    }

    @Override
    public double getGananciaTotales() {
        return tratamientoRepositorio.totalGanancias();
    }

    @Override
    public double getVentasTotales() {
        return tratamientoRepositorio.totalVentas();
    }

    @Override
    public List<Medicamento> obtenerTop3MedicamentosMasVendidos() {
        Pageable top3 = PageRequest.of(0, 3); // Limitar a los 3 primeros resultados
        return medicamentoRepositorio.findTop3MedicamentosMasVendidos(top3);
    }
}

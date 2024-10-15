package com.michito.demo.Repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Tratamieneto;

public interface TratamientoRepositorio extends JpaRepository<Tratamieneto, Long> {

    List<Tratamieneto> findByMascotaIn(List<Mascota> mascotas);

    List<Tratamieneto> findByMascotaId(Long id);

    List<Tratamieneto> findByVeterinarioId(Long id);

    List<Tratamieneto> findByMedicamentosId(Long id);

    // Cantidad total de tratamientos administrados en el último mes
    @Query("SELECT COUNT(t) FROM Tratamieneto t WHERE t.fecha BETWEEN :startDate AND :endDate")
    Long countTratamientosUltimoMes(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Cantidad de mascotas diferentes que tienen tratamientos
    @Query("SELECT COUNT(DISTINCT t.mascota) FROM Tratamieneto t")
    Long countMascotasConTratamiento();

    // Cantidad de veterinarios activos
    @Query("SELECT COUNT(DISTINCT t.veterinario) FROM Tratamieneto t WHERE t.fecha BETWEEN :startDate AND :endDate")
    Long countVeterinariosActivos(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Cantidad de tratamientos donde se suministró cada medicamento
    @Query("SELECT m.nombre, COUNT(t) FROM Tratamieneto t JOIN t.medicamentos m WHERE t.fecha BETWEEN :startDate AND :endDate GROUP BY m.nombre")
    List<Object[]> countTratamientosPorMedicamento(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Ganancias totales
    @Query("SELECT SUM(m.precioVenta - m.precioCompra) FROM Tratamieneto t JOIN t.medicamentos m")
    double totalGanancias();

    // Ganancias totales
    @Query("SELECT SUM(m.precioVenta) FROM Tratamieneto t JOIN t.medicamentos m")
    double totalVentas();
}

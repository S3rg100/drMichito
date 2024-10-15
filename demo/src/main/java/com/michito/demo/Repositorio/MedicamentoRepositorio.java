package com.michito.demo.Repositorio;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Entidades.Tratamieneto;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento,Long> {
    Optional<Medicamento> findByNombre(String nombre);
    @Query("SELECT t FROM Tratamieneto t JOIN t.medicamentos m WHERE m.id = :medicamentoId")
    List<Tratamieneto> findByMedicamentosId(@Param("medicamentoId") Long medicamentoId);
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);
    List<Medicamento> findByTratamientosId(Long tratamientoId);
}

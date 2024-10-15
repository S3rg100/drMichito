package com.michito.demo.Repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.michito.demo.Entidades.Veterinario;

public interface VeterinarioRepositorio extends JpaRepository<Veterinario,Long>{
    
    //Cantidad de veterinarios inactivos
    @Query("SELECT COUNT(v) FROM Veterinario v WHERE v NOT IN (SELECT DISTINCT t.veterinario FROM Tratamieneto t WHERE t.fecha BETWEEN :startDate AND :endDate)")
    Long countVeterinariosInactivos(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    
    List<Veterinario> findByNombreContainingIgnoreCase(String nombre);
}

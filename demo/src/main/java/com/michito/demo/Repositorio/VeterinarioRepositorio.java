package com.michito.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.michito.demo.Entidades.Veterinario;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepositorio extends JpaRepository<Veterinario,Long>{
    
    //Cantidad de veterinarios inactivos
    @Query("SELECT COUNT(v) FROM Veterinario v WHERE v.estado = false")
    Long countVeterinariosInactivos();

    @Query("SELECT COUNT(v) FROM Veterinario v WHERE v.estado = true")
    Long countVeterinariosActivos();
    
    List<Veterinario> findByNombreContainingIgnoreCase(String nombre);
}

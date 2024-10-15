package com.michito.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.michito.demo.Entidades.Veterinario;

public interface VeterinarioRepositorio extends JpaRepository<Veterinario,Long>{
    List<Veterinario> findByNombreContainingIgnoreCase(String nombre);
}

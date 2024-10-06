package com.michito.demo.Repositorio;



import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.michito.demo.Entidades.Mascota;

public interface MascotasRepositorio extends JpaRepository<Mascota,Long> {

   public List<Mascota> findByClienteId(Long id);
    
}

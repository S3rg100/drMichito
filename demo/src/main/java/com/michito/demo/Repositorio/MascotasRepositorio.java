package com.michito.demo.Repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.michito.demo.Entidades.Mascota;

public interface MascotasRepositorio extends JpaRepository<Mascota,Long> {

   public List<Mascota> findByClienteId(Long id);
   
   @Query("SELECT COUNT(m) FROM Mascota m")
   Long countTotalMascotas();

   @Query("SELECT COUNT(m) FROM Mascota m WHERE m.estado = true")
   Long countMascotasActivas();
   
   List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    
}

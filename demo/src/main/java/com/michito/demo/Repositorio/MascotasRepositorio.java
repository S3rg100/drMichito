package com.michito.demo.Repositorio;



import org.springframework.data.jpa.repository.JpaRepository;
import com.michito.demo.Entidades.Mascota;

public interface MascotasRepositorio extends JpaRepository<Mascota,Long> {

   
    
}

package com.michito.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Tratamieneto;

public interface TratamientoRepositorio extends JpaRepository<Tratamieneto,Long>{

     List<Tratamieneto> findByMascotaIn(List<Mascota> mascotas);
    List<Tratamieneto> findByMedicamentosId(Long id);
    
}

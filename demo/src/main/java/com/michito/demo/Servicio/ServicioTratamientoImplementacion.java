package com.michito.demo.Servicio;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Entidades.Tratamieneto;
import jakarta.transaction.Transactional;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicioTratamientoImplementacion implements ServicioTratamiento{
      @Autowired
      TratamientoRepositorio tratamientoRepositorio;

      @Transactional
      public void eliminarTratamiento(Long id) {
        Tratamieneto tratamiento = tratamientoRepositorio.findById(id).get();
      
          // Eliminar relaciones
          tratamiento.getMedicamentos().clear();
          tratamiento.setMascota(null);
          tratamiento.setVeterinario(null);
      
          // Eliminar el tratamiento
          tratamientoRepositorio.delete(tratamiento);
      }

    @Override
    public List<Tratamieneto> findAll() {
        return tratamientoRepositorio.findAll();
    }
      

}

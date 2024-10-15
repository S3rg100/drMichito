package com.michito.demo.Servicio;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Entidades.Tratamieneto;
import jakarta.transaction.Transactional;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


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

    @Override
    public Tratamieneto addTratamiento(Tratamieneto tratamiento) {
      return tratamientoRepositorio.save(tratamiento);  
  }
      
  public Tratamieneto updateTratamiento(Long id, Tratamieneto tratamiento) {
    
    return tratamientoRepositorio.save(tratamiento);  
}

  @Override
  public Tratamieneto findById(Long id) {
    return tratamientoRepositorio.findById(id).orElse(null);
  }

  


}

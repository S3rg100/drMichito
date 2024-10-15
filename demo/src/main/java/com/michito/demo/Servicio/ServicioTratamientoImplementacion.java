package com.michito.demo.Servicio;
import com.michito.demo.Repositorio.MascotasRepositorio;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Entidades.Mascota;
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
      @Autowired
      MascotasRepositorio mascotasRepositorio;

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

  @Override
   public List<Tratamieneto> obtenerTratamientosPorCliente(Long clienteId) {
        // Primero obtenemos las mascotas del cliente
        List<Mascota> mascotas = mascotasRepositorio.findByClienteId(clienteId);

        // Después, buscamos los tratamientos asociados a esas mascotas
        return tratamientoRepositorio.findByMascotaIn(mascotas);
    }

  @Override
  public Tratamieneto obtenerPorId(Long id) {
    Optional<Tratamieneto> tratamiento = tratamientoRepositorio.findById(id);
    if (tratamiento.isPresent()) {
        System.out.println("Medicamentos del tratamiento: " + tratamiento.get().getMedicamentos());
        return tratamiento.get();
    } else {
        throw new RuntimeException("Tratamiento no encontrado");
    }
}

  @Override
  public Tratamieneto obtenerTratamiento(Long id) {
    Tratamieneto tratamiento = tratamientoRepositorio.findById(id)
        .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));

    // Acceder a la lista de medicamentos para forzar la carga si es LAZY
    tratamiento.getMedicamentos().size();

    return tratamiento;
}

  
  


}

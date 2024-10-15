package com.michito.demo.Servicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Repositorio.VeterinarioRepositorio;
@Service
public class ServicioVeterinarioImplementacion implements ServicioVeterinario {
    @Autowired
    VeterinarioRepositorio veterinarioRespositorio ;
    @Autowired
    TratamientoRepositorio tratamientoRepositorio;
    @Override
    public Veterinario searchById(Long id) {
       return veterinarioRespositorio.findById(id).get();
    }

    @Override
    public void addVeterinario(Veterinario newMascota) {
        veterinarioRespositorio.save(newMascota);
    }

    @Override
    public List<Veterinario> searchAll() {
        return veterinarioRespositorio.findAll();
    }
    @Override
    public List<Veterinario> buscarPorNombre(String nombre) {
        return veterinarioRespositorio.findByNombreContainingIgnoreCase(nombre);
    }
    

    @Override
    public void updateVeterinario(Veterinario VeterinarioEditado) {
        veterinarioRespositorio.save(VeterinarioEditado);
    }

    @Override
    public void eliminarVeterinario(Long id) {
        Veterinario veterinario = veterinarioRespositorio.findById(id).get();

        List<Tratamieneto> tratamientos = tratamientoRepositorio.findByVeterinarioId(id);
        for (Tratamieneto tratamiento : tratamientos) {
            tratamiento.setVeterinario(null); // Desasociar el veterinario de cada tratamiento
            tratamientoRepositorio.save(tratamiento); // Guardar los cambios
        }
        veterinarioRespositorio.deleteById(id);
    }
    
}

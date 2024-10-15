package com.michito.demo.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.michito.demo.Repositorio.VeterinarioRepositorio;

import com.michito.demo.Entidades.Veterinario;
@Service
public class ServicioVeterinarioImplementacion implements ServicioVeterinario {
    @Autowired
    VeterinarioRepositorio veterinarioRespositorio ;
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
        veterinarioRespositorio.deleteById(id);
    }
    
}

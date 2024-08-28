package com.michito.demo.Servicio;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.MascotasRepositorio;

@Service
public class ServicioMascotaImplementacion implements ServicioMascota{
    @Autowired
	MascotasRepositorio mascotaRepositorio;

    
    @Override
    public Mascota searchById(Long id) {
        return mascotaRepositorio.findById(id).get();
    }

    @Override
    public Collection<Mascota> searchAll() {
        return mascotaRepositorio.findAll();
    }

    @Override
    public void addMascota(Mascota newMascota) {
        mascotaRepositorio.save(newMascota);
    }




   
    @Override
    public void updateMascota(Mascota mascotaEditada) {
        mascotaRepositorio.save(mascotaEditada);
    }

    @Override
    public void eliminarMascota(Long id) {
        mascotaRepositorio.deleteById(id);
    }

   
}

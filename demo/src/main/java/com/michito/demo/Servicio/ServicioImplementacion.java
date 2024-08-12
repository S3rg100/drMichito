package com.michito.demo.Servicio;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repoditorio.MascotasRepositorio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServicioImplementacion implements Servicio{
    @Autowired
	MascotasRepositorio repo;

    
    @Override
    public Mascota searchById(int id) {
        return repo.findById(id);
    }

    @Override
    public Collection<Mascota> searchAll() {
        return repo.findAll();
    }
}

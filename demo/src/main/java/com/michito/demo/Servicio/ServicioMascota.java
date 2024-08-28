package com.michito.demo.Servicio;

import java.util.Collection;

import com.michito.demo.Entidades.Mascota;

public interface ServicioMascota {
    public Mascota searchById(Long id);
    public void addMascota(Mascota newMascota);
    public Collection<Mascota> searchAll();
    public void updateMascota(Mascota mascotaEditada);
    public void eliminarMascota(Long id);
} 
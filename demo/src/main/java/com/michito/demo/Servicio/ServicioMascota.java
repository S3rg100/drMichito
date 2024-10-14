package com.michito.demo.Servicio;

import java.util.*;

import com.michito.demo.Entidades.Mascota;

public interface ServicioMascota {
    public Mascota searchById(Long id);
    public void addMascota(Mascota newMascota);
    public List<Mascota> searchAll();
    public List<Mascota> searchByIdCliente(long id);
    public void updateMascota(Mascota mascotaEditada);
    public void eliminarMascota(Long id);
    public List<Mascota> obtenerMascotasPorCliente(Long clienteId);
} 
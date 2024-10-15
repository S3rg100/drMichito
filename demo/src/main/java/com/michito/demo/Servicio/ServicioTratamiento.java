package com.michito.demo.Servicio;
import java.util.*;

import com.michito.demo.Entidades.Tratamieneto;

public interface ServicioTratamiento {
    public void eliminarTratamiento(Long tratamientoId);
    public List<Tratamieneto> findAll();
    public Tratamieneto addTratamiento(Tratamieneto tratamiento);
    public Tratamieneto updateTratamiento(Long id, Tratamieneto tratamiento);
    public Tratamieneto findById(Long id);
    public List<Tratamieneto> obtenerTratamientosPorCliente(Long clienteId);
    public Tratamieneto obtenerPorId(Long id);
    public Tratamieneto obtenerTratamiento(Long id);
    
}

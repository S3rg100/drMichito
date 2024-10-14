package com.michito.demo.Servicio;
import java.util.*;

import com.michito.demo.Entidades.Tratamieneto;

public interface ServicioTratamiento {
    public void eliminarTratamiento(Long tratamientoId);
    public List<Tratamieneto> findAll();
}

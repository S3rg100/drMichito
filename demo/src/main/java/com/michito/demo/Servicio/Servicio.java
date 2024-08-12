package com.michito.demo.Servicio;
import java.util.*;
import com.michito.demo.Entidades.Mascota;

public interface Servicio {
    public Mascota searchById(int id);
    public Collection<Mascota> searchAll();
}

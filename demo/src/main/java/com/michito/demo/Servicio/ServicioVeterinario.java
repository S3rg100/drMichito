package com.michito.demo.Servicio;
import java.util.*;

import com.michito.demo.Entidades.Veterinario;
public interface ServicioVeterinario {
    public Veterinario searchById(Long id);
    public void addVeterinario(Veterinario newMascota);
    public List<Veterinario> searchAll();
    public void updateVeterinario(Veterinario VeterinarioEditado);
    public void eliminarVeterinario(Long id);
}

package com.michito.demo.Servicio;
import java.util.Collection;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;

public interface Servicio {
    public Mascota searchById(int id);
    public void addMascota(Mascota newMascota);
    public Collection<Mascota> searchAll();
    public Cliente searchByIdCliente(int id);
    public Collection<Cliente> searchAllClientes();
    public void deleteCliente(int id);
    public void updateCliente(Cliente cliente);
    public void addCliente(Cliente cliente);
    public Collection<Mascota> searchMascotasDeCliente(int id);
}

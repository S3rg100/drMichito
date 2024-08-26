package com.michito.demo.Servicio;
import java.util.Collection;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Login;
import com.michito.demo.Entidades.Mascota;

public interface Servicio {
    public Mascota searchById(Long id);
    public void addMascota(Mascota newMascota);
    public Collection<Mascota> searchAll();
    public Cliente searchByIdCliente(Long id);
    public Collection<Cliente> searchAllClientes();
    public void deleteCliente(Long id);
    public void updateCliente(Cliente cliente);
    public void addCliente(Cliente cliente);
    public void updateMascota(Mascota mascotaEditada);
    public void eliminarMascota(Long id);
    public Cliente findByCedula(String cedula);
    public Collection<Login> searchAllLogins();
    public Login SearchByUsuario(String usuario);
} 

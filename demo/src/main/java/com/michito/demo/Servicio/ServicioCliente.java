package com.michito.demo.Servicio;
import java.util.Collection;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;

public interface ServicioCliente {
    
    public Cliente searchByIdCliente(Long id);
    public Collection<Cliente> searchAllClientes();
    public void deleteCliente(Long id);
    public void updateCliente(Cliente cliente);
    public void addCliente(Cliente cliente);
    public Cliente findByCedula(String cedula);
    public Mascota searchById(Long id);
} 

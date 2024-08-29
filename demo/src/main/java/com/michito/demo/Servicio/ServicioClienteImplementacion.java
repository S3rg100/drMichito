package com.michito.demo.Servicio;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;

@Service

public class ServicioClienteImplementacion implements ServicioCliente{
    
    @Autowired
    ClientesRepositorio clienteRepositorio;
    @Autowired
    MascotasRepositorio mascotaRepositorio;

    
  

    @Override
    public Cliente searchByIdCliente(Long id){
        return clienteRepositorio.findById(id).get();
    }

    @Override
    public Collection<Cliente> searchAllClientes() {
        return clienteRepositorio.findAll();
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepositorio.deleteById(id);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void addCliente(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

 

    @Override
    public Cliente findByCedula(String cedula){
        return clienteRepositorio.findByCedula(cedula);
    }

    @Override
    public Mascota searchById(Long id) {
        return mascotaRepositorio.findById(id).get();
    }
   
}
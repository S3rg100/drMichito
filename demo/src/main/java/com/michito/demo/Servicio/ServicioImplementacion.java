package com.michito.demo.Servicio;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.MascotasRepositorio;

@Service

public class ServicioImplementacion implements Servicio{
    @Autowired
	MascotasRepositorio repo;

    
    @Override
    public Mascota searchById(int id) {
        return repo.findById(id);
    }

    @Override
    public Collection<Mascota> searchAll() {
        return repo.findAll();
    }

    @Override
    public void addMascota(Mascota newMascota) {
        repo.addMascota(newMascota);
    }

    @Override
    public Cliente searchByIdCliente(int id){
        return repo.findByIdCliente(id);
    }

    @Override
    public Collection<Cliente> searchAllClientes() {
        return repo.findAllClientes();
    }

    @Override
    public void deleteCliente(int id) {
        repo.deleteCliente(id);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        repo.updateCliente(cliente);
    }

    @Override
    public void addCliente(Cliente cliente) {
        repo.addCliente(cliente);
    }

    @Override
    public Collection<Mascota> searchMascotasDeCliente(int id) {
        return repo.findByClienteId(id);
    }

    @Override
    public void updateMascota(Mascota mascotaEditada) {
        repo.updateMascota(mascotaEditada);
    }

    @Override
    public void eliminarMascota(int id) {
        repo.eliminarMascota(id);;
    }

    
}

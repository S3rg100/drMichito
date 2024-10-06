package com.michito.demo.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;

@Service

public class ServicioClienteImplementacion implements ServicioCliente {

    @Autowired
    ClientesRepositorio clienteRepositorio;
    @Autowired
    MascotasRepositorio mascotaRepositorio;

    @Override
    public Cliente searchByIdCliente(Long id) {
        return clienteRepositorio.findById(id).get();
    }

    @Override
    public List<Cliente> searchAllClientes() {
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
    public Cliente findByCedula(String cedula) {
        return clienteRepositorio.findByCedula(cedula);
    }

    @Override
    public Mascota searchById(Long id) {
        return mascotaRepositorio.findById(id).get();
    }

    @Override
    public Page<Cliente> findAllPaginated(int page, int size){
        return clienteRepositorio.findAll(PageRequest.of(page, size));
    }

    
    @Override
    public List<Cliente> buscarClientesPorCedula(String cedula) {
        return clienteRepositorio.findByCedulaContaining(cedula);
    }

}
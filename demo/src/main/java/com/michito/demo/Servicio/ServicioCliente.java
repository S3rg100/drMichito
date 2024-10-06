package com.michito.demo.Servicio;
import java.util.List;

import org.springframework.data.domain.Page;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;

public interface ServicioCliente {
    
    public Cliente searchByIdCliente(Long id);
    public List<Cliente> searchAllClientes();
    public void deleteCliente(Long id);
    public void updateCliente(Cliente cliente);
    public void addCliente(Cliente cliente);
    public Cliente findByCedula(String cedula);
    public Mascota searchById(Long id);
    /*Paginacion */
    public Page<Cliente> findAllPaginated(int page, int size);

    public List<Cliente> buscarClientesPorCedula(String cedula);
} 

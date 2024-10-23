package com.michito.demo.Repositorio;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michito.demo.Entidades.Cliente;

@Repository
public interface ClientesRepositorio extends JpaRepository<Cliente, Long> {
    Cliente findByCedula(String cedula);
    Page<Cliente> findAll(Pageable pageable);
    List<Cliente> findByCedulaContaining(String cedula);
    
}

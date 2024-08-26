package com.michito.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michito.demo.Entidades.Tratamieneto;

public interface TratamientoRepositorio extends JpaRepository<Tratamieneto,Long>{
    
}

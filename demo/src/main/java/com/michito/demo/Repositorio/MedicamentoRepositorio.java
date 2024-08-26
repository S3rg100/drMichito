package com.michito.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michito.demo.Entidades.Medicamento;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento,Long> {
    
}

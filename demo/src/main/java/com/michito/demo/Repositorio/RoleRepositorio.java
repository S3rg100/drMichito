package com.michito.demo.Repositorio;

import org.springframework.stereotype.Repository;

import com.michito.demo.Entidades.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepositorio extends JpaRepository<Role,Long>{
    
}

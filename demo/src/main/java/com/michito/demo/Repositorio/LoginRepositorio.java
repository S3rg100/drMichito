package com.michito.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michito.demo.Entidades.Login;

public interface LoginRepositorio  extends JpaRepository<Login,String>{
    public Login findByUsuario(String usuario);
}

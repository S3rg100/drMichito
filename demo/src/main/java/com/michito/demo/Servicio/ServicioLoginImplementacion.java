package com.michito.demo.Servicio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Repositorio.LoginRepositorio;

@Service

public class ServicioLoginImplementacion implements ServicioLogin{
   
    @Autowired
    LoginRepositorio loginRepositorio;

   


    @Override
    public Collection<Login> searchAllLogins() {
        return loginRepositorio.findAll();
    }

    @Override
    public Login SearchByUsuario(String usuario) {
        return loginRepositorio.findByUsuario(usuario);
    }

   
}

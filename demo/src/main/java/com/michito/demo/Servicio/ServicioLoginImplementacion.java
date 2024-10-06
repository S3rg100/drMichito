package com.michito.demo.Servicio;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Repositorio.LoginRepositorio;

@Service

public class ServicioLoginImplementacion implements ServicioLogin{
   
   @Autowired
    LoginRepositorio loginRepositorio;

   


    @Override
    public List<Login> searchAllLogins() {
        return loginRepositorio.findAll();
    }

    @Override
    public Login SearchByUsuario(String usuario) {
        return loginRepositorio.findByUsuario(usuario);
    }

   
}

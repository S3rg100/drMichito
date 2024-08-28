package com.michito.demo.Servicio;
import java.util.Collection;

import com.michito.demo.Entidades.Login;

public interface ServicioLogin {

    public Collection<Login> searchAllLogins();
    public Login SearchByUsuario(String usuario);
} 

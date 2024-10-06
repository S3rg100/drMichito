package com.michito.demo.Servicio;
import java.util.List;

import com.michito.demo.Entidades.Login;

public interface ServicioLogin {

    public List<Login> searchAllLogins();
    public Login SearchByUsuario(String usuario);
    
} 

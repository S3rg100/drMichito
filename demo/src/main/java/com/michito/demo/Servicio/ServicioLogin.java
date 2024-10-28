package com.michito.demo.Servicio;
import java.util.List;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Entidades.VeterinarioDTO;

public interface ServicioLogin {

    public List<Login> searchAllLogins();
    public Login SearchByUsuario(String usuario);
    public void save(Login login);
    
    
} 

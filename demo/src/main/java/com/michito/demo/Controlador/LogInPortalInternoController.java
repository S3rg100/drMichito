package com.michito.demo.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Repositorio.LoginRepositorio;
import com.michito.demo.Servicio.Servicio;

@Controller
@RequestMapping("/logInPortalInterno")
public class LogInPortalInternoController {

    @Autowired
    private Servicio serv;
    


    @GetMapping("")
    public String iniciar(Model model) {
        model.addAttribute("loginForm", new Login("",""));
        return "logInPortaInterno";
    }
    
    @PostMapping("")
public String procesarLogin(@ModelAttribute("loginForm") Login loginForm) {
    System.out.println(loginForm);
    Login login = serv.SearchByUsuario(loginForm.getUsuario());
    if (login != null && login.getPassword().equals(loginForm.getPassword())) {
        //return "redirect:/Mascotas/all";
        
    }

    return "redirect:/logInPortalInterno";
}

    
}

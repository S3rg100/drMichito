package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Login;
import com.michito.demo.Servicio.ServicioLogin;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LogInController {

/*
    @Autowired
    private ServicioCliente clienteServicio;
    @Autowired
    private ServicioLogin loginServicio;

    @GetMapping("")
    public String iniciar(Model model) {
        model.addAttribute("loginForm", new Cliente());
        return "logIn";
    }

    @PostMapping("")
    public String procesarLogin(@ModelAttribute("loginForm") Cliente loginForm, Model model) {
        String cedula = loginForm.getCedula();
        Cliente cliente = clienteServicio.findByCedula(cedula);

        if (cliente != null) {
            return "redirect:/Clientes/VistaMascotas/" + cliente.getId();
        } else {
            model.addAttribute("error", "Cédula ingresada no encontrada. Inténtelo de nuevo.");
            model.addAttribute("loginForm", loginForm);
            return "logIn";
        }
    }

    @GetMapping("/portalInterno")
    public String iniciarPortalInterno(Model model) {
        model.addAttribute("loginForm", new Login("", ""));
        return "logInPortaInterno";
    }

    @PostMapping("/portalInterno")
    public String procesarLoginPortalInterno(@ModelAttribute("loginForm") Login loginForm, Model model) {
        System.out.println(loginForm);
        Login login = loginServicio.SearchByUsuario(loginForm.getUsuario());
        if (login != null && login.getPassword().equals(loginForm.getPassword())) {
            return "redirect:/Mascotas/all";
        } else {
            // Si los datos son incorrectos, añade un mensaje de error al modelo
            model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtelo de nuevo.");
            model.addAttribute("loginForm", loginForm); // Mantiene los datos del formulario
            return "logInPortaInterno"; 
        }
    }
    @GetMapping("/portalInterno/{username}")
    public Login getMethodName(@RequestParam String username) {
        return loginServicio.SearchByUsuario(username);
    }
    */
     @Autowired
    private ServicioLogin loginServicio;

    @GetMapping("")
    public List<Login> findAll() {
        return loginServicio.searchAllLogins();
    }
    
    @GetMapping("/portalInterno/{username}")
    public Login findByUsuario(@PathVariable String username) {
        return loginServicio.SearchByUsuario(username);
    }

    @GetMapping("/{cedula}")
    public Login findByCedulaCliente(@PathVariable String cedula) {
        return loginServicio.SearchByUsuario(cedula);
    }

}

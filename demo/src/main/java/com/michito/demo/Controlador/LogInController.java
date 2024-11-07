package com.michito.demo.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Login;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioLogin;
import com.michito.demo.security.JWTGenerator;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LogInController {

    @Autowired
    private ServicioLogin loginServicio;

    @Autowired
    private ServicioCliente servicioCliente;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    @GetMapping("")
    public List<Login> findAll() {
        return loginServicio.searchAllLogins();
    }

    @PostMapping("/portalInterno")
    public ResponseEntity<String> findByUsuario(@RequestBody Login login) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getPasswords())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /*
    @GetMapping("/{cedula}")
    public Login findByCedulaCliente(@PathVariable String cedula) {
        return loginServicio.SearchByUsuario(cedula);
    }
   */
  @GetMapping("/{cedula}")
    public ResponseEntity<?> findByCedulaCliente(@PathVariable String cedula) {
        Cliente cliente = servicioCliente.findByCedula(cedula);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Autenticación manual con rol CLIENTE, sin contraseña
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            cliente.getCedula(),  // La cédula actúa como el "username" aquí
            null,  // No se usa contraseña
            List.of(new SimpleGrantedAuthority("CLIENTE"))  // Asignación de rol CLIENTE
        );

        // Establece el contexto de seguridad con la autenticación del cliente
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Genera el token JWT basado en la autenticación configurada
        String token = jwtGenerator.generateToken(authentication);

        return ResponseEntity.ok(token);
    }


  
        
}

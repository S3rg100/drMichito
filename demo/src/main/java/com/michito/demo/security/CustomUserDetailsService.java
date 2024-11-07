package com.michito.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Login;
import com.michito.demo.Entidades.Role;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.LoginRepositorio;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepositorio loginRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepositorio.findByUsuario(username);
        
        if(login==null)
            throw new UsernameNotFoundException(username + " not found");

        UserDetails userDetails = User.builder()
            .username(login.getUsuario())
            .password(login.getPasswords()) // Asegúrate de que esté encriptada
            .authorities(mapToGrantedAuthorities(login.getRoles()))
            .build();

        return userDetails;
    }

    public UserDetails loadUserByCedula(String cedula) throws UsernameNotFoundException {
        // Aquí necesitas buscar al cliente por cédula, asumiendo que hay un método para esto
        Cliente cliente = clientesRepositorio.findByCedula(cedula);

        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente con cédula " + cedula + " no encontrado");
        }

        // Crea los detalles del usuario con un rol CLIENTE sin contraseña
        return User.builder()
            .username(cedula) // Utilizamos la cédula como username en este caso
            .password("")     // Sin contraseña ya que los clientes no la necesitan
            .authorities(List.of(new SimpleGrantedAuthority("CLIENTE"))) // Rol de CLIENTE
            .build();
    }

    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getNombre()))
            .collect(Collectors.toList());
    }
}

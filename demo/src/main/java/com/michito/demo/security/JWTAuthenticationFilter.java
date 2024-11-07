package com.michito.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Repositorio.ClientesRepositorio;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;;

public class JWTAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    JWTGenerator jwtGenerator;
    @Autowired
    CustomUserDetailsService customUserDetails;
    @Autowired
    ClientesRepositorio clientesRepositorio;
    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
    throws ServletException, IOException {
       String token = getJWT(request);
        if(token!= null && jwtGenerator.validateToken(token)){
            String username = jwtGenerator.extractUsermane(token);
            UserDetails userDetails = customUserDetails.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null,userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
        
    }
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        String token = getJWT(request);
        
        if (token != null && jwtGenerator.validateToken(token)) {
            String identifier = jwtGenerator.extractUsermane(token);  // Puede ser username o cédula

            UserDetails userDetails;
            try {
                // Intentamos cargar al usuario como un usuario normal
                userDetails = customUserDetails.loadUserByUsername(identifier);
            } catch (UsernameNotFoundException e) {
                // Si no es un usuario normal, intentamos cargarlo como cliente por cédula
                Cliente cliente = clientesRepositorio.findByCedula(identifier);
                if (cliente == null) {
                    filterChain.doFilter(request, response);  // Cliente no encontrado, continuar sin autenticar
                    return;
                }
                
                // Crear UserDetails para cliente con rol CLIENTE
                userDetails = org.springframework.security.core.userdetails.User.builder()
                        .username(cliente.getCedula())
                        .password("")  // No se requiere contraseña para clientes
                        .authorities(List.of(new SimpleGrantedAuthority("CLIENTE")))
                        .build();
            }

            // Crear el token de autenticación
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            // Establecer el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
    public String getJWT(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            return authHeader.replace("Bearer", "");
        }
        return null;
    }


    
}

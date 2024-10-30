package com.michito.demo.Entidades;

import lombok.Data;

@Data
public class EmailRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String body;  // Este será el mensaje del cliente

    
}

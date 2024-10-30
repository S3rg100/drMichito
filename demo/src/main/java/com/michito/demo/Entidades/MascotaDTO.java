package com.michito.demo.Entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotaDTO {
    private Long id;
    private String nombre;
    private int peso;
    private int edad;
    private String foto;
    private String cedulaCliente;  // Este campo lo obtenemos del cliente
    private boolean estado;
}

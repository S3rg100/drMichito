package com.michito.demo.Entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeterinarioDTO {
    private Long id;
    private String cedula;
    private String nombre;
    private String correo;
    private Long celular;
    private String especialidad;
    private boolean estado;

    // Datos de Login directamente en VeterinarioDTO
    private String usuario;
    private String passwords;
    private String tipo;

    

    // Constructor completo
    public VeterinarioDTO(Long id, String cedula, String nombre, String correo, Long celular, 
                          String especialidad, boolean estado, String usuario, String passwords, String tipo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.especialidad = especialidad;
        this.estado = estado;
        this.usuario = usuario;
        this.passwords = passwords;
        this.tipo = tipo;
    }

    

    

    @Override
    public String toString() {
        return "VeterinarioDTO{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", celular=" + celular +
                ", especialidad='" + especialidad + '\'' +
                ", estado=" + estado +
                ", usuario='" + usuario + '\'' +
                ", passwords='" + passwords + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

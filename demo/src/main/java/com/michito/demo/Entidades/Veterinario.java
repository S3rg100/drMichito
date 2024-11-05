package com.michito.demo.Entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Veterinario {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String cedula;
    private String nombre;
    private String correo;
    private Long celular;
    private String especialidad;
    private boolean estado;
    

@JsonIgnore
   @OneToMany(mappedBy = "veterinario")
    private List<Tratamieneto> tratamientos = new ArrayList<>();
    

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Login login;




    
    
    public Veterinario(String cedula,String nombre,String correo,Long celular,String especialidad,boolean estado){ 
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.especialidad = especialidad;
        this.estado=estado;
    } 
    
    
    
    
    @Override
public String toString() {
    return "Veterinario{" +
            "id=" + id +
            ", cedula='" + cedula + '\'' +
            ", nombre='" + nombre + '\'' +
            ", correo='" + correo + '\'' +
            ", celular=" + celular +
            ", especialidad='" + especialidad + '\'' +
            ", estado=" + estado +
            "login:"+login;
}

}
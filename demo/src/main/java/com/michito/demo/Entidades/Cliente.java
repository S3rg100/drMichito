package com.michito.demo.Entidades;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String cedula;
    private String nombre;
    private String correo;
    private long celular;
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Mascota> mascotas = new ArrayList<>();
    

    public Cliente(String cedula,String nombre,String correo, long celular){
        this.cedula=cedula;
        this.nombre=nombre;
        this.correo=correo;
        this.celular=celular;
    }



    
    
}

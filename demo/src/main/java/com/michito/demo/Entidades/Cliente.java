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
@Entity
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
    public List<Mascota> getMascotas() {
        return mascotas;
    }
    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
  
    public Cliente(){}
    public Cliente(String cedula,String nombre,String correo, long celular){
        this.cedula=cedula;
        this.nombre=nombre;
        this.correo=correo;
        this.celular=celular;
    }



    
    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setCedula(String cedula){
        this.cedula=cedula;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public void setCelular(long celular){
        this.celular=celular;
    }
    public String getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public long getCelular() {
        return celular;
    }
}

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


@Entity
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
    
    
    
   public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

@JsonIgnore
   @OneToMany(mappedBy = "veterinario")
    private List<Tratamieneto> tratamientos = new ArrayList<>();
    public List<Tratamieneto> getTratamientos() {
        return tratamientos;
    }
    public void setTratamientos(List<Tratamieneto> tratamientos) {
        this.tratamientos = tratamientos;
    }

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Login login;




    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    
    public Veterinario(String cedula,String nombre,String correo,Long celular,String especialidad,boolean estado){ 
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.especialidad = especialidad;
        this.estado=estado;
    } 
    public Veterinario(){} 
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }
    public Long getId() {
        return id;
    }
    
    
}
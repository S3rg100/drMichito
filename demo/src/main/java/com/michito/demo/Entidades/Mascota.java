package com.michito.demo.Entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Transient;

@Entity
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;
    
    private String nombre;
    private int peso;
    private int edad;
    private String foto;

    @Transient // No se almacena en la base de datos
    private String cedulaCliente;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "mascota")
    private List<Tratamieneto> tratamientos = new ArrayList<>();
    
    // Método PreRemove para romper la relación con los tratamientos antes de eliminar la mascota
    @PreRemove
    public void preRemove() {
        for (Tratamieneto tratamiento : this.tratamientos) {
            tratamiento.setMascota(null);  // Rompe la relación con los tratamientos
        }
    }
    
    public List<Tratamieneto> getTratamientos() {
        return tratamientos;
    }
    
    public void setTratamientos(List<Tratamieneto> tratamientos) {
        this.tratamientos = tratamientos;
    }
   
    public Mascota(){}
    
    public Mascota(String nombre, int edad, int peso, String foto) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.foto = foto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getPeso() {
        return peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
}

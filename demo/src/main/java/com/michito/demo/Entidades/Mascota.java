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
import lombok.Data;

@Entity
@Data
public class Mascota {
    @Id
    @GeneratedValue
    private Long id;
    private boolean estado;
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
    
    public Mascota(){
        this.estado=true;   
    }
    
    public Mascota(String nombre, int edad, int peso, String foto,boolean estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.foto = foto;
        this.estado=estado;
    }

    
    
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void getEstado(boolean estado) {
        this.estado = estado;
    }

}

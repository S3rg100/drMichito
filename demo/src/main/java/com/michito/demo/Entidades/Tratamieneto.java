package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;


@Entity
public class Tratamieneto {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private String descripcion;
    
    
    
   
    @JsonIgnore
    @ManyToMany
    @JoinTable( name = "tratamieneto_medicamento",
        joinColumns = @JoinColumn(name = "tratamieneto_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
    @JsonIgnore
    @ManyToOne
    private Veterinario  veterinario;

    public Veterinario getVeterinario() {
        return veterinario;
    }
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    @JsonIgnore
    @ManyToOne
    private Mascota mascota;
    public Mascota getMascota() {
        return mascota;
    }
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    
    
    
    public Tratamieneto(LocalDate fecha,String descripcion){
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
    public Tratamieneto(){}
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    

}

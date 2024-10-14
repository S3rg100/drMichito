package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;



@Entity
public class Tratamieneto {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private String descripcion;
    
    
   
    // Relaciones con medicamento
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "tratamieneto_medicamento",
        joinColumns = @JoinColumn(name = "tratamieneto_id"),
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    // Relaciones con veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = true)  // permite que sea null si el tratamiento es eliminado
    private Veterinario veterinario;

    // Relaciones con mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = true)  // permite que sea null si el tratamiento es eliminado
    private Mascota mascota;
    


    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
    public Veterinario getVeterinario() {
        return veterinario;
    }
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
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

package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tratamieneto {  // Dejo el nombre tal cual
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private String descripcion;

    // Relaciones con medicamento
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
        name = "tratamiento_medicamento", 
        joinColumns = @JoinColumn(name = "tratamiento_id"), 
        inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    

    // Relaciones con veterinario
    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = true)
    private Veterinario veterinario;

    // Relaciones con mascota
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = true)
    private Mascota mascota;

    // Constructores
    public Tratamieneto(LocalDate fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
}

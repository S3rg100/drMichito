package com.michito.demo.Entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue
    private Long id; 
    private String nombre;
    private double precioVenta;
    private double precioCompra;
    private int unidadesDisponibles;
    private int unidadesVendidas;

    @JsonIgnore
    @ManyToMany(mappedBy = "medicamentos")
    private List<Tratamieneto> tratamientos = new ArrayList<>();
    

    public Medicamento(String nombre, double precioVenta, double precioCompra, int unidadesDisponibles, int unidadesVendidas) {
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.unidadesDisponibles = unidadesDisponibles;
        this.unidadesVendidas = unidadesVendidas;
    }

}

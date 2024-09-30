package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Medicamento {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String tipo;
    private String descipcion;
    private LocalDate fechaVencimiento;
    private int stock;
    @JsonIgnore
    @ManyToMany(mappedBy = "medicamentos")
    private List<Tratamieneto> tratamientos = new ArrayList<>();
    public List<Tratamieneto> getTratamienetos() {
        return tratamientos;
    }
    public void setTratamienetos(List<Tratamieneto> tratamienetos) {
        tratamientos = tratamienetos;
    }
    

    
    public Medicamento(String nombre,String tipo, String descipcion,LocalDate fechaVencimiento,int stock){
        this.nombre = nombre;
        this.tipo = tipo;
        this.descipcion = descipcion;
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;

    }
    public Medicamento(){}
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getDescipcion() {
        return descipcion;
    }
    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }
    
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}

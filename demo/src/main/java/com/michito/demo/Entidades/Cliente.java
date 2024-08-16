package com.michito.demo.Entidades;

import java.util.*;

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private String correo;
    private long celular;
    private Map<Integer,Mascota>mascotas = new HashMap<>();

    public Cliente(int id,String cedula,String nombre,String correo, long celular){
        this.id=id;
        this.cedula=cedula;
        this.nombre=nombre;
        this.correo=correo;
        this.celular=celular;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public void agregarMascota(int id,Mascota mascota){
        mascotas.put(id,mascota);
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
    public void setMascotas(Map<Integer,Mascota>mascotas){
        this.mascotas=mascotas;
    }
    public Map<Integer,Mascota> getMascotas() {
        return mascotas;
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

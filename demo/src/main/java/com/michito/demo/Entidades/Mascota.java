package com.michito.demo.Entidades;

public class Mascota {
    private int id;
    private String nombre;
    private int edad;
    private int peso;
    private String enfermedad;
    private String foto;
    public Mascota(){}
    public Mascota(int id,String nombre,int edad,int peso,String enfermedad,String foto){
        this.id=id;
        this.nombre=nombre;
        this.edad=edad;
        this.peso=peso;
        this.enfermedad=enfermedad;
        this.foto=foto;
    }
    public int getId(){
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
    public String getEnfermedad() {
        return enfermedad;
    }
    public String getFoto() {
        return foto;
    }
    public void setId(int id){
        this.id=id;
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
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
}

package com.michito.demo.Entidades;

public class MascotaDTO {
    private Long id;
    private String nombre;
    private int peso;
    private int edad;
    private String foto;
    private String cedulaCliente;  // Este campo lo obtenemos del cliente

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
}

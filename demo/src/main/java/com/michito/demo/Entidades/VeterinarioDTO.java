package com.michito.demo.Entidades;


public class VeterinarioDTO {
    private Long id;
    private String cedula;
    private String nombre;
    private String correo;
    private Long celular;
    private String especialidad;
    private boolean estado;

    // Datos de Login directamente en VeterinarioDTO
    private String usuario;
    private String passwords;
    private String tipo;

    // Constructor vacío
    public VeterinarioDTO() {}

    // Constructor completo
    public VeterinarioDTO(Long id, String cedula, String nombre, String correo, Long celular, 
                          String especialidad, boolean estado, String usuario, String passwords, String tipo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.especialidad = especialidad;
        this.estado = estado;
        this.usuario = usuario;
        this.passwords = passwords;
        this.tipo = tipo;
    }

    // Getters y Setters para Veterinario
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Getters y Setters para Login
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "VeterinarioDTO{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", celular=" + celular +
                ", especialidad='" + especialidad + '\'' +
                ", estado=" + estado +
                ", usuario='" + usuario + '\'' +
                ", passwords='" + passwords + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

package com.michito.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Login {
    @Id
    private String usuario;
    @JsonIgnore
    @OneToOne(mappedBy = "login")
    private Veterinario veterinario;
    @Transient // No se almacena en la base de datos
    private Long idVeterinario;
     public Long getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Long idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    private String tipo;

    private String passwords;

    public Login() {}

    public Login(String usuario, String passwords,String tipo) {
        this.usuario = usuario;
        this.passwords = passwords;
        this.tipo=tipo;
    }

    public String getPassword() {
        return passwords;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPassword(String passwords) {
        this.passwords = passwords;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) { 
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Login{" +
                "usuario='" + usuario + '\'' +
                ", passwords='" + passwords + '\'' +
                '}';
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}


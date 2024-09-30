package com.michito.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Login {
    @Id
    private String usuario;
    @JsonIgnore
    @OneToOne(mappedBy = "login")
    private Veterinario veterinario;

    private String passwords;

    public Login() {}

    public Login(String usuario, String passwords) {
        this.usuario = usuario;
        this.passwords = passwords;
    }

    public String getPassword() {
        return passwords;
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


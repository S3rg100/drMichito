package com.michito.demo.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Login {
    @Id
    private String usuario;
    @JsonIgnore
    @OneToOne(mappedBy = "login")
    private Veterinario veterinario;
    @Transient // No se almacena en la base de datos
    private Long idVeterinario;
    

    private String tipo;

    private String passwords;


    public Login(String usuario, String passwords,String tipo) {
        this.usuario = usuario;
        this.passwords = passwords;
        this.tipo=tipo;
    }

    @Override
    public String toString() {
        return "Login{" +
                "usuario='" + usuario + '\'' +
                ", tipo='" + tipo + '\'' +
                ", passwords='" + passwords + '\'' +
                ", idVeterinario=" + idVeterinario +
                '}';
    }

}


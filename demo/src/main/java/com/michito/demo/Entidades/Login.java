package com.michito.demo.Entidades;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

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
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

   

    private String passwords;


    public Login(String usuario, String passwords,List<Role> roles) {
        this.usuario = usuario;
        this.passwords = passwords;
        this.roles=roles;
    }

    


}


package com.michito.demo.Entidades;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmailRequestWithFile {
    private String asunto;
    private String nombreVeterinario;
    private String nombreMascota;
    private String emailCliente;
    private String fechaTratamiento;
    private String body;
    private MultipartFile file;
}

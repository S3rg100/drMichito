package com.michito.demo.Servicio;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;


import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Repositorio.VeterinarioRepositorio;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ServicioTratamientoTestMock {
    @InjectMocks 
    ServicioTratamientoImplementacion servicioTratamiento;
    @InjectMocks
    ServicioVeterinarioImplementacion servicioVeterinario;
    @InjectMocks
    ServicioMascotaImplementacion servicioMascota;

    @Mock
    TratamientoRepositorio tratamientoRepositorio;
    @Mock
    VeterinarioRepositorio veterinarioRepositorio;
    //13
    @Test
    public void ServicioTratamiento_addTratamiento_Tratamiento(){
        //arrange
        Tratamieneto tratamieneto1= new Tratamieneto(LocalDate.of(2024,5,15),"Nuevo");
        when(tratamientoRepositorio.save(tratamieneto1)).thenReturn(tratamieneto1);
        //act
        Tratamieneto nuevoTratamiento = servicioTratamiento.addTratamiento(tratamieneto1);
        //assert
        Assertions.assertThat(nuevoTratamiento).isNotNull();
    }
    //14
    @Test
    public void ServicioTratamiento_obtenerTratamientosPorVeterinario_TratamientoList() {
        // arrange
        Tratamieneto tratamiento1 = new Tratamieneto(LocalDate.of(2024, 5, 15), "Nuevo");
        Tratamieneto tratamiento2 = new Tratamieneto(LocalDate.of(2024, 8, 15), "Nuevo 2");
        Veterinario veterinario = new Veterinario("1111", "Pablo", "corr@gm.com", 456L, "cirujano", true);
        
        when(tratamientoRepositorio.save(tratamiento1)).thenReturn(tratamiento1);
        when(tratamientoRepositorio.save(tratamiento2)).thenReturn(tratamiento2);
        when(veterinarioRepositorio.save(veterinario)).thenReturn(veterinario);
        
        servicioVeterinario.addVeterinario(veterinario);
        tratamiento1.setVeterinario(veterinario);
        tratamiento2.setVeterinario(veterinario);
        servicioTratamiento.addTratamiento(tratamiento1);
        servicioTratamiento.addTratamiento(tratamiento2);
        when(tratamientoRepositorio.findByVeterinarioId(veterinario.getId())).thenReturn(Arrays.asList(tratamiento1, tratamiento2));
        
        List<Tratamieneto> tratamientos = servicioTratamiento.obtenerTratamientosPorVeterinario(veterinario.getId());
        
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isGreaterThan(0);
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }
    
    //15 Prueba para el método updateById
    @Test
    void testUpdateById() {
        Tratamieneto tratamientoExistente = new Tratamieneto(LocalDate.of(2024, 5, 12), "Nuevo tratamiento");

        when(tratamientoRepositorio.save(tratamientoExistente)).thenReturn(tratamientoExistente);
        servicioTratamiento.addTratamiento(tratamientoExistente);
        tratamientoExistente.setDescripcion("CAMBIA");
        when(tratamientoRepositorio.save(tratamientoExistente)).thenReturn(tratamientoExistente);
        Tratamieneto modificado = servicioTratamiento.updateTratamiento(tratamientoExistente.getId(), tratamientoExistente);
        Assertions.assertThat(modificado).isNotNull();  
        Assertions.assertThat(modificado.getDescripcion()).isEqualTo("CAMBIA");
    }


    
}

package com.michito.demo.Servicio;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

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
    public void ServicioTratamiento_obtenerTratamientosPorVeterinario_TratamientoList(){
        //arrange
        Tratamieneto tratamieneto1= new Tratamieneto(LocalDate.of(2024,5,15),"Nuevo");
        Tratamieneto tratamieneto2= new Tratamieneto(LocalDate.of(2024,8,15),"nuevo 2");
        Veterinario veterinario= new Veterinario("1111","pablo","corr@gm.com",456L,"cirujano",true);
        servicioVeterinario.addVeterinario(veterinario);
        tratamieneto1.setVeterinario(veterinario);
        tratamieneto2.setVeterinario(veterinario);
        servicioTratamiento.addTratamiento(tratamieneto1);
        servicioTratamiento.addTratamiento(tratamieneto2);
        //act
        List<Tratamieneto>tratamientos = servicioTratamiento.obtenerTratamientosPorVeterinario(veterinario.getId());
        //assert
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isGreaterThan(0);
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }
    //15
    @Test
    public void ServicioTratamiento_obtenerTratamientosPorMascota_TratamientoList(){
        Mascota mascota = new Mascota("mike",4,5,"URL",true);
        Tratamieneto tratamieneto1= new Tratamieneto(LocalDate.of(2024,5,15),"Nuevo");
        Tratamieneto tratamieneto2= new Tratamieneto(LocalDate.of(2024,8,15),"nuevo 2");
        servicioMascota.addMascota(mascota);
        tratamieneto1.setMascota(mascota);
        tratamieneto2.setMascota(mascota);
        servicioTratamiento.addTratamiento(tratamieneto1);
        servicioTratamiento.addTratamiento(tratamieneto2);
        List<Tratamieneto>tratamientos = servicioTratamiento.obtenerTratamientosPorMascota(mascota.getId());
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isGreaterThan(0);
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    
}

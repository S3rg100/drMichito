package com.michito.demo.Servicio;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;


import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Entidades.Veterinario;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class ServicioTratamientoTestNative {
    @Autowired 
    ServicioTratamiento servicioTratamiento;
    @Autowired
    ServicioVeterinario servicioVeterinario;
    @Autowired
    ServicioMascota servicioMascota;


    @Test
    public void ServicioTratamiento_addTratamiento_Tratamiento(){
        //arrange
        Tratamieneto tratamieneto1= new Tratamieneto(LocalDate.of(2024,5,15),"Nuevo");
        Tratamieneto tratamieneto2= new Tratamieneto(LocalDate.of(2024,8,15),"nuevo 2");
        //act
        Tratamieneto nuevoTratamiento = servicioTratamiento.addTratamiento(tratamieneto1);
        nuevoTratamiento = servicioTratamiento.addTratamiento(tratamieneto2);
        //assert
        Assertions.assertThat(nuevoTratamiento).isNotNull();
    }

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

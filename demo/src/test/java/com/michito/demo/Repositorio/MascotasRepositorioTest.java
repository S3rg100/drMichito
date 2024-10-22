package com.michito.demo.Repositorio;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.config.TestConfig;

import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class) 
public class MascotasRepositorioTest {

    @Autowired
    MascotasRepositorio mascotasRepositorio;

    @BeforeEach
    void setUp(){
        mascotasRepositorio.deleteAll();
        mascotasRepositorio.save(new Mascota("nombre",5,4,"URL",true));
    }

    //Create
    @Test
    public void MascotasRepositorio_save_Mascota() {
        Mascota mascota = new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascotaGuardada = mascotasRepositorio.save(mascota);
        Assertions.assertThat(mascotaGuardada).isNotNull();
    }
    //Read
    @Test
    public void MascotasRepositorio_FindAll_NotEmptyList(){
        Mascota mascota1=new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascota2=new Mascota("Nuevo2", 3, 6, "URL Foto", false);
        mascotasRepositorio.save(mascota1);
        mascotasRepositorio.save(mascota2);
        List<Mascota>mascotas = mascotasRepositorio.findAll();
        Assertions.assertThat(mascotas).isNotNull();
        Assertions.assertThat(mascotas.size()).isEqualTo(3);//before Each ya cargó un dato
        Assertions.assertThat(mascotas.size()).isGreaterThan(0);
    }
    //Delete
    @Test
    public void MascotasRepositorio_deleteById_EmptyMascota(){
        long index = 2L;
        mascotasRepositorio.deleteById(index);
        Assertions.assertThat(mascotasRepositorio.findById(index)).isEmpty();
    }
    //ERROR
    @Test
    public void MascotasRepositorio_FindById_FindWrongIndex(){
        long index = -1L;
        Optional<Mascota> mascota=mascotasRepositorio.findById(index);
        Assertions.assertThat(mascota).isEmpty();
    }
}

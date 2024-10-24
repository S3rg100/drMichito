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
import java.util.List;

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

    //1 Create
    @Test
    public void MascotasRepositorio_save_Mascota() {
        Mascota mascota = new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascotaGuardada = mascotasRepositorio.save(mascota);
        Assertions.assertThat(mascotaGuardada).isNotNull();
    }
    //2 Read
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
    //3 Update
    @Test
    public void MascotasRepositorio_updateById_Mascota(){
        String name = "MODIFICADO";
        Mascota mascota2=new Mascota("Nuevo2", 3, 6, "URL Foto", false);
        mascotasRepositorio.save(mascota2);
        Mascota mascota=mascotasRepositorio.findByNombre("Nuevo2");
        mascota.setNombre(name);
        Mascota mascotaModificada = mascotasRepositorio.save(mascota);
        Assertions.assertThat(mascotaModificada).isNotNull();
        Assertions.assertThat(mascotaModificada.getNombre()).isEqualTo(name);
    }
    //4 Delete
    @Test
    public void MascotasRepositorio_deleteById_EmptyMascota(){
        long index = 2L;
        mascotasRepositorio.deleteById(index);
        Assertions.assertThat(mascotasRepositorio.findById(index)).isEmpty();
    }
    //5 ERROR
    @Test
    public void MascotasRepositorio_FindById_FindWrongIndex(){
        long index = -1L;
        Optional<Mascota> mascota=mascotasRepositorio.findById(index);
        Assertions.assertThat(mascota).isEmpty();
    }
    //6 Consultas propias 1
    @Test
    public void MascotasRepositorio_countMascotasActivas_Long(){
        Mascota mascota1=new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascota2=new Mascota("Nuevo2", 3, 6, "URL Foto", false);
        mascotasRepositorio.save(mascota1);
        mascotasRepositorio.save(mascota2);
        Long count = mascotasRepositorio.countMascotasActivas();
        Assertions.assertThat(count).isEqualTo(2);
        Assertions.assertThat(count).isGreaterThan(0);
    }
    //7 Consultas propias 2
    @Test
    public void MascotasRepositorio_countTotalMascotas_Long(){
        Mascota mascota1=new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascota2=new Mascota("Nuevo2", 3, 6, "URL Foto", false);
        mascotasRepositorio.save(mascota1);
        mascotasRepositorio.save(mascota2);
        Long count = mascotasRepositorio.countTotalMascotas();
        List<Mascota>mascotas=mascotasRepositorio.findAll();
        Assertions.assertThat(count).isEqualTo(3); //Se crea una en Before Each
        Assertions.assertThat(count).isGreaterThan(0);
        Assertions.assertThat(count).isEqualTo(mascotas.size());
    }
}
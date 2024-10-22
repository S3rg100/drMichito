package com.michito.demo.Repositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.config.TestConfig;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class) 
public class VeterinarioRepositorioTest {
    @Autowired
    VeterinarioRepositorio veterinarioRepositorio;
    @Autowired
    TratamientoRepositorio tratamientoRepositorio;
    @BeforeEach
    void setUp(){
        tratamientoRepositorio.deleteAll();
        veterinarioRepositorio.deleteAll();
        veterinarioRepositorio.save(new Veterinario("111","Pepe","cor@gm.com",111L,"cirujano",true));
        veterinarioRepositorio.save(new Veterinario("222","Pepe","cor@gm.com",111L,"cirujano",true));
        veterinarioRepositorio.save(new Veterinario("333","Pepe","cor@gm.com",111L,"cirujano",false));
        veterinarioRepositorio.save(new Veterinario("444","Pepe","cor@gm.com",111L,"cirujano",false));
    }
    //11 Consultas propias 3
    @Test
    public void VeterinarioRepositorio_countVeterinariosInactivos_Long(){
        Long count = veterinarioRepositorio.countVeterinariosInactivos();
        Assertions.assertThat(count).isGreaterThan(0);
        Assertions.assertThat(count).isEqualTo(2);
    }
    //12 Consultas propias 4
    @Test
    public void VeterinarioRepositorio_countVeterinariosActivos_Long(){
        Long count = veterinarioRepositorio.countVeterinariosActivos();
        Assertions.assertThat(count).isGreaterThan(0);
        Assertions.assertThat(count).isEqualTo(2);
    }
    
}

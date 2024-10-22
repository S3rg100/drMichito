package com.michito.demo.Repositorio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.config.TestConfig;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class) 
public class MascotasRepositorioTest {

    @Autowired
    MascotasRepositorio mascotasRepositorio;

    @Test
    public void MascotasRepositorio_save_Mascota() {
        Mascota mascota = new Mascota("Nuevo", 4, 5, "FOTOOO", true);
        Mascota mascotaGuardada = mascotasRepositorio.save(mascota);
        Assertions.assertThat(mascotaGuardada).isNotNull();
    }
}

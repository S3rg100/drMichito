package com.michito.demo.Repositorio;

import java.time.LocalDate;
import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Entidades.Veterinario;
import com.michito.demo.config.TestConfig;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class) 
public class TratamientoRepositorioTest {

    @Autowired
    MascotasRepositorio mascotasRepositorio;

    @Autowired
    TratamientoRepositorio tratamientoRepositorio;

    @Autowired
    MedicamentoRepositorio medicamentoRepositorio;

    @Autowired
    VeterinarioRepositorio veterinarioRepositorio;

    @BeforeEach
    void setUp(){
        mascotasRepositorio.deleteAll();
        tratamientoRepositorio.deleteAll();
        medicamentoRepositorio.deleteAll();
        veterinarioRepositorio.deleteAll();

        // Inicializar veterinario
        Veterinario veterinario = new Veterinario("1211121222", "Pedro", "pedro@gmail.com", 1234L, "cirujano", true);
        veterinarioRepositorio.save(veterinario);

        // Inicializar mascota
        Mascota mascota = new Mascota("Toby", 2, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s", false);
        mascotasRepositorio.save(mascota);

        // Inicializar medicamentos
        Medicamento medicamento1 = new Medicamento("Antibiótico", 50.0, 30.0, 10, 2);
        Medicamento medicamento2 = new Medicamento("Analgésico", 100.0, 60.0, 20, 5);
        medicamentoRepositorio.save(medicamento1);
        medicamentoRepositorio.save(medicamento2);

        Tratamieneto tratamiento1 = new Tratamieneto(LocalDate.of(2024, 10, 15), "Tratamiento de prueba 1");
        tratamiento1.setVeterinario(veterinario);
        tratamiento1.setMascota(mascota);
        tratamiento1.setMedicamentos(Arrays.asList(medicamento1, medicamento2));
        tratamientoRepositorio.save(tratamiento1);

        Tratamieneto tratamiento2 = new Tratamieneto(LocalDate.of(2024, 10, 16), "Tratamiento de prueba 2");
        tratamiento1.setVeterinario(veterinario);
        tratamiento1.setMascota(mascota);
        tratamiento1.setMedicamentos(Arrays.asList(medicamento1, medicamento2));
        tratamientoRepositorio.save(tratamiento2);

    }


    @Test
    public void testCountTratamientosUltimoMes() {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();

        Long count = tratamientoRepositorio.countTratamientosUltimoMes(startDate, endDate);
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    public void testTotalGanancias() {
        double totalGanancias = tratamientoRepositorio.totalGanancias();
        Assertions.assertThat(totalGanancias).isEqualTo(60.0);  // (50 - 30) + (100 - 60) = 20 + 40 = 60
    }

    @Test
    public void testTotalVentas() {
        double totalVentas = tratamientoRepositorio.totalVentas();
        Assertions.assertThat(totalVentas).isEqualTo(150.0);  // 50 + 100 = 150
    }
}

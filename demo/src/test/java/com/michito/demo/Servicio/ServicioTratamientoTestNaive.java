package com.michito.demo.Servicio;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.michito.demo.Entidades.Tratamieneto;

@SpringBootTest
@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ServicioTratamientoTestNaive {

    @Autowired
    private ServicioTratamiento servicioTratamiento;

    @Autowired
    private ServicioVeterinario servicioVeterinario;

    //16 Buscar Todos
    @Test
    public void ServicioTratamiento_findAll_test() {
        List<Tratamieneto> tratamientos = servicioTratamiento.findAll();
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(10);
    }

    //17 Encontrar por ID
    @Test
    public void ServicioTratamiento_findById_test() {
        Long tratamientoId = 1L;
        Tratamieneto tratamiento = servicioTratamiento.findById(tratamientoId);
        Assertions.assertThat(tratamiento).isNotNull();
        Assertions.assertThat(tratamiento.getId()).isEqualTo(tratamientoId);
    }

    //18 Obetener tratamientos por cliente
    @Test
    public void ServicioTratamiento_obtenerTratamientosPorCliente_test() {
        Long clienteId = 1L;
        List<Tratamieneto> tratamientos = servicioTratamiento.obtenerTratamientosPorCliente(clienteId);
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    //19 Agregar Tratamiento
    @Test
    public void ServicioTratamiento_addTratamiento_test() {
        Tratamieneto tratamiento1 = new Tratamieneto(LocalDate.of(2024, 9, 15), "Tratamiento de prueba");
        Tratamieneto resultado = servicioTratamiento.addTratamiento(tratamiento1);
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getDescripcion()).isEqualTo("Tratamiento de prueba");
    }

    //20 Actualizar Tratamiento
    @Test
    public void ServicioTratamiento_updateTratamiento_test() {
        Long tratamientoId = 1L;
        Tratamieneto tratamientoActualizado = servicioTratamiento.findById(tratamientoId);
        tratamientoActualizado.setFecha(LocalDate.of(2025, 6, 12));
        Tratamieneto resultado = servicioTratamiento.updateTratamiento(tratamientoId, tratamientoActualizado);
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getFecha()).isEqualTo(LocalDate.of(2025, 6, 12));
    }

    //21 Eliminar Tratamiento
    @Test
    public void ServicioTratamiento_eliminarTratamiento_test() {
        Long tratamientoId = 1L;
        Assertions.assertThatCode(() -> servicioTratamiento.eliminarTratamiento(tratamientoId))
                .doesNotThrowAnyException();

        Tratamieneto tratamiento = servicioTratamiento.findById(tratamientoId);
        Assertions.assertThat(tratamiento).isNull();
    }

    //22 Obtener tratamientos por veterinario
    @Test
    public void ServicioTratamiento_obtenerTratamientosPorVeterinario_test() {
        Long veterinarioId = 1L;
        List<Tratamieneto> tratamientos = servicioTratamiento.obtenerTratamientosPorVeterinario(veterinarioId);
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(2);
    }

    //23 Obtener tratamientos por mascota
    @Test
    public void ServicioTratamiento_obtenerTratamientosPorMascota_test() {
        Long mascotaId = 6L;
        List<Tratamieneto> tratamientos = servicioTratamiento.obtenerTratamientosPorMascota(mascotaId);
        Assertions.assertThat(tratamientos).isNotNull();
        Assertions.assertThat(tratamientos.size()).isEqualTo(1);
    }
}

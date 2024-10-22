package com.michito.demo.Repositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;

import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.config.TestConfig;

import java.time.LocalDate;
import java.util.*;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@Import(TestConfig.class) 
public class TratamientoRepositorioTest {
    @Autowired
    TratamientoRepositorio tratamientoRepositorio;
    @Autowired
    MedicamentoRepositorio medicamentoRepositorio;

    @BeforeEach
    void setUp(){
        medicamentoRepositorio.deleteAll();
        tratamientoRepositorio.deleteAll();
        Tratamieneto tratamieneto= new Tratamieneto(LocalDate.of(2024,8,15),"cirugía");
        Medicamento medicamento1=new Medicamento("A",(float) 3000.0,10,10,10);
        Medicamento medicamento2=new Medicamento("B",(float) 4000.0,10,10,10);
        medicamentoRepositorio.save(medicamento1);
        medicamentoRepositorio.save(medicamento2);
        tratamieneto.agregarMedicamentos(medicamento1);
        tratamieneto.agregarMedicamentos(medicamento2);
        tratamientoRepositorio.save(tratamieneto);
    }
    //Consultas propias 5
    @Test
    public void TratamientoRepositorio_totalVentas_double(){
        List<Double> precios = tratamientoRepositorio.obtenerPreciosMedicamentos();
        double totalCalculado = precios.stream().mapToDouble(Double::doubleValue).sum();
        Assertions.assertThat(totalCalculado).isGreaterThan(0);
        Assertions.assertThat(totalCalculado).isEqualTo(7000);

       
    }

}

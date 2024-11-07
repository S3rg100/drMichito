package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.LoginRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;
import com.michito.demo.Repositorio.MedicamentoRepositorio;
import com.michito.demo.Repositorio.TratamientoRepositorio;
import com.michito.demo.Repositorio.VeterinarioRepositorio;

import jakarta.transaction.Transactional;

@Controller
@Transactional
@Profile("default")
public class DataBaseIni implements ApplicationRunner {
    @Autowired
    MascotasRepositorio MascotaRepositorio;
    @Autowired
    ClientesRepositorio ClienteRepositorio;
    @Autowired
    LoginRepositorio LoginRepositorio;
    @Autowired
    VeterinarioRepositorio VeterinarioRepositorio;
    @Autowired
    MedicamentoRepositorio MedicamentoRepositorio;
    @Autowired
    TratamientoRepositorio TratamientoRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role("ADMIN");
        Role veterinarioRole = new Role("VETERINARIO");
        List<Role>adminAndVet = new ArrayList<>();
        List<Role>veterinario = new ArrayList<>();
        List<Role>admin = new ArrayList<>();
        adminAndVet.add(veterinarioRole);
        adminAndVet.add(adminRole);
        veterinario.add(veterinarioRole);
        admin.add(adminRole);

        Login logadnmin = new Login("admin",passwordEncoder.encode("admin"),adminAndVet);
        LoginRepositorio.save(logadnmin);
        Veterinario adminVet = new Veterinario("1212", "admin", "admin@gmail.com", 1L, "admin",true);
        adminVet.setLogin(logadnmin);
        VeterinarioRepositorio.save(adminVet);


        Login loginVet = new Login("admin2",passwordEncoder.encode("admin2"),admin);
        LoginRepositorio.save(loginVet);
        Veterinario adminVetand = new Veterinario("1010", "admin", "admin@gmail.com", 1L, "admin",true);
        adminVetand.setLogin(loginVet);
        VeterinarioRepositorio.save(adminVetand);


        


        Login login2 = new Login("vet", passwordEncoder.encode("vet"), veterinario);
        login2 = LoginRepositorio.save(login2); // Guardar el login primero y obtener el objeto persistido

        // Crear el veterinario y asignar el login
        Veterinario veterinarioprim = new Veterinario("999", "Prueba", "veterinario@gmail.com", 1234L, "cirujano",true);
        veterinarioprim.setLogin(login2); // Asignar login2 al veterinario
        login2.setVeterinario(veterinarioprim); // Asignar veterinarioprim al login2

        // Guardar el veterinario
        VeterinarioRepositorio.save(veterinarioprim);

        // Luego guardar el login con la relación bidireccional
        LoginRepositorio.save(login2); 
     
        // VeterinarioRepositorio.save(new Veterinario("123","Pedro","pedro@gmail.com",1234L,"cirujano",true));
        // VeterinarioRepositorio.save(new Veterinario("001","Juan","juan@example.com",1001L,"cirujano",true));
        // VeterinarioRepositorio.save(new Veterinario("002","Ana","ana@example.com",1002L,"cardiologo",true));
        // VeterinarioRepositorio.save(new Veterinario("003","Luis","luis@example.com",1003L,"dentista",true));
        // VeterinarioRepositorio.save(new Veterinario("004","María","maria@example.com",1004L,"dermatologo",true));
        // VeterinarioRepositorio.save(new Veterinario("005","Carlos","carlos@example.com",1005L,"oftalmologo",true));
        // VeterinarioRepositorio.save(new Veterinario("006","Laura","laura@example.com",1006L,"neurocirujano",true));
        // VeterinarioRepositorio.save(new Veterinario("007","Pedro","pedro@example.com",1007L,"gastroenterologo",true));
        // VeterinarioRepositorio.save(new Veterinario("008","Isabel","isabel@example.com",1008L,"traumatologo",true));
        // VeterinarioRepositorio.save(new Veterinario("009","David","david@example.com",1009L,"internista",true));
        // VeterinarioRepositorio.save(new Veterinario("010","Lucía","lucia@example.com",1010L,"oncologo",true));
        // VeterinarioRepositorio.save(new Veterinario("011","Javier","javier@example.com",1011L,"cirujano",true));
        // VeterinarioRepositorio.save(new Veterinario("012","Elena","elena@example.com",1012L,"cardiologo",true));
        // VeterinarioRepositorio.save(new Veterinario("013","Ricardo","ricardo@example.com",1013L,"dentista",true));
        // VeterinarioRepositorio.save(new Veterinario("014","Sofia","sofia@example.com",1014L,"dermatologo",true));
        // VeterinarioRepositorio.save(new Veterinario("015","Miguel","miguel@example.com",1015L,"oftalmologo",true));
        // VeterinarioRepositorio.save(new Veterinario("016","Raquel","raquel@example.com",1016L,"neurocirujano",true));
        // VeterinarioRepositorio.save(new Veterinario("017","Andrés","andres@example.com",1017L,"gastroenterologo",true));
        // VeterinarioRepositorio.save(new Veterinario("018","Carmen","carmen@example.com",1018L,"traumatologo",false));
        // VeterinarioRepositorio.save(new Veterinario("019","Fernando","fernando@example.com",1019L,"internista",true));
        // VeterinarioRepositorio.save(new Veterinario("020","Valeria","valeria@example.com",1020L,"oncologo",true));


        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("123")
                    .nombre("Pedro")
                    .correo("pedro@gmail.com")
                    .celular(1234L)
                    .especialidad("cirujano")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("001")
                    .nombre("Juan")
                    .correo("juan@example.com")
                    .celular(1001L)
                    .especialidad("cirujano")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("002")
                    .nombre("Ana")
                    .correo("ana@example.com")
                    .celular(1002L)
                    .especialidad("cardiologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("003")
                    .nombre("Luis")
                    .correo("luis@example.com")
                    .celular(1003L)
                    .especialidad("dentista")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("004")
                    .nombre("María")
                    .correo("maria@example.com")
                    .celular(1004L)
                    .especialidad("dermatologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("005")
                    .nombre("Carlos")
                    .correo("carlos@example.com")
                    .celular(1005L)
                    .especialidad("oftalmologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("006")
                    .nombre("Laura")
                    .correo("laura@example.com")
                    .celular(1006L)
                    .especialidad("neurocirujano")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("007")
                    .nombre("Pedro")
                    .correo("pedro@example.com")
                    .celular(1007L)
                    .especialidad("gastroenterologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("008")
                    .nombre("Isabel")
                    .correo("isabel@example.com")
                    .celular(1008L)
                    .especialidad("traumatologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("009")
                    .nombre("David")
                    .correo("david@example.com")
                    .celular(1009L)
                    .especialidad("internista")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("010")
                    .nombre("Lucía")
                    .correo("lucia@example.com")
                    .celular(1010L)
                    .especialidad("oncologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("011")
                    .nombre("Javier")
                    .correo("javier@example.com")
                    .celular(1011L)
                    .especialidad("cirujano")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("012")
                    .nombre("Elena")
                    .correo("elena@example.com")
                    .celular(1012L)
                    .especialidad("cardiologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("013")
                    .nombre("Ricardo")
                    .correo("ricardo@example.com")
                    .celular(1013L)
                    .especialidad("dentista")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("014")
                    .nombre("Sofia")
                    .correo("sofia@example.com")
                    .celular(1014L)
                    .especialidad("dermatologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("015")
                    .nombre("Miguel")
                    .correo("miguel@example.com")
                    .celular(1015L)
                    .especialidad("oftalmologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("016")
                    .nombre("Raquel")
                    .correo("raquel@example.com")
                    .celular(1016L)
                    .especialidad("neurocirujano")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("017")
                    .nombre("Andrés")
                    .correo("andres@example.com")
                    .celular(1017L)
                    .especialidad("gastroenterologo")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("018")
                    .nombre("Carmen")
                    .correo("carmen@example.com")
                    .celular(1018L)
                    .especialidad("traumatologo")
                    .estado(false)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("019")
                    .nombre("Fernando")
                    .correo("fernando@example.com")
                    .celular(1019L)
                    .especialidad("internista")
                    .estado(true)
                    .build()
        );
    
        VeterinarioRepositorio.save(
                Veterinario.builder()
                    .cedula("020")
                    .nombre("Valeria")
                    .correo("valeria@example.com")
                    .celular(1020L)
                    .especialidad("oncologo")
                    .estado(true)
                    .build()
        );

        //Mascota mascota = new Mascota().builder().nombre("Toby").edad(2).peso(12).foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s").estado(false).build();
        MascotaRepositorio.save(
                Mascota.builder()
                    .nombre("Toby")
                    .edad(2)
                    .peso(12)
                    .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                    .estado(false)
                    .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rex")
                .edad(3)
                .peso(17)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(false)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Luna")
                .edad(1)
                .peso(8)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Charlie")
                .edad(3)
                .peso(16)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Buddy")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lucy")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Maggie")
                .edad(2)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Sophie")
                .edad(4)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Max")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bella")
                .edad(3)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Milo")
                .edad(1)
                .peso(6)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Chloe")
                .edad(2)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rocky")
                .edad(4)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Daisy")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Molly")
                .edad(3)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bailey")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Oscar")
                .edad(1)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Coco")
                .edad(4)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Buster")
                .edad(2)
                .peso(16)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Riley")
                .edad(3)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Harley")
                .edad(4)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Penny")
                .edad(1)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Zoe")
                .edad(2)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ginger")
                .edad(3)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Henry")
                .edad(1)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Maddie")
                .edad(2)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ellie")
                .edad(4)
                .peso(16)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rosie")
                .edad(3)
                .peso(17)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Jake")
                .edad(1)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lilly")
                .edad(4)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lola")
                .edad(2)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Teddy")
                .edad(3)
                .peso(16)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Roxy")
                .edad(1)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Simba")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bruno")
                .edad(3)
                .peso(9)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Pepper")
                .edad(1)
                .peso(8)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rusty")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Murphy")
                .edad(4)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ruby")
                .edad(3)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bandit")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lucky")
                .edad(4)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Shadow")
                .edad(2)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Tucker")
                .edad(3)
                .peso(10)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Dexter")
                .edad(1)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Misty")
                .edad(4)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Gizmo")
                .edad(3)
                .peso(16)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Jasper")
                .edad(2)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Duke")
                .edad(1)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Scout")
                .edad(4)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Mocha")
                .edad(3)
                .peso(12)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Cookie")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Max")
                .edad(1)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Sadie")
                .edad(4)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bear")
                .edad(3)
                .peso(17)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );


        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rocky")
                .edad(2)
                .peso(10)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Charlie")
                .edad(1)
                .peso(16)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Daisy")
                .edad(4)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Chloe")
                .edad(3)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bella")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Luna")
                .edad(1)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Duke")
                .edad(4)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Milo")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Sophie")
                .edad(3)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ziggy")
                .edad(1)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Trixie")
                .edad(4)
                .peso(11)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Gizmo")
                .edad(2)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Nala")
                .edad(3)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rex")
                .edad(4)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lulu")
                .edad(1)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Hank")
                .edad(2)
                .peso(16)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Finn")
                .edad(3)
                .peso(17)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Marley")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Gracie")
                .edad(1)
                .peso(14)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rocco")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Hazel")
                .edad(3)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Diesel")
                .edad(4)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lily")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Tank")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Juno")
                .edad(3)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Moose")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Willow")
                .edad(1)
                .peso(16)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bowie")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Annie")
                .edad(3)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Winston")
                .edad(4)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ginger")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Tyson")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Lulu")
                .edad(3)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Jasper")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Piper")
                .edad(1)
                .peso(16)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Oreo")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bubba")
                .edad(3)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Millie")
                .edad(4)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Dolly")
                .edad(1)
                .peso(9)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Chase")
                .edad(2)
                .peso(14)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Bailey")
                .edad(3)
                .peso(15)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Harley")
                .edad(4)
                .peso(12)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Rusty")
                .edad(1)
                .peso(16)
                .foto("https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Sasha")
                .edad(2)
                .peso(13)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Blue")
                .edad(3)
                .peso(11)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s")
                .estado(true)
                .build()
        );
        MascotaRepositorio.save(
        Mascota.builder()
                .nombre("Ana")
                .edad(58)
                .peso(10)
                .foto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s")
                .estado(true)
                .build()
        );

        // Inserciones de clientes
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("321499")
                .nombre("Jose")
                .correo("jose@gmail.com")
                .celular(12364)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("321456")
                .nombre("Jose")
                .correo("jose@gmail.com")
                .celular(12364)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("79885")
                .nombre("Pedro")
                .correo("pedro@gmail.com")
                .celular(79465)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("44545")
                .nombre("Paola")
                .correo("paola@gmail.com")
                .celular(88987)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("48785")
                .nombre("Jaime")
                .correo("jaime@gmail.com")
                .celular(741002)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("77874")
                .nombre("Cristian")
                .correo("cristian@gmail.com")
                .celular(889800)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("88852")
                .nombre("Lorena")
                .correo("lorena@gmail.com")
                .celular(874521)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("74565")
                .nombre("Ana")
                .correo("ana@gmail.com")
                .celular(12358)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45786")
                .nombre("Carlos")
                .correo("carlos@gmail.com")
                .celular(56478)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("95875")
                .nombre("Sofia")
                .correo("sofia@gmail.com")
                .celular(98456)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("24758")
                .nombre("Miguel")
                .correo("miguel@gmail.com")
                .celular(78453)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("34858")
                .nombre("Laura")
                .correo("laura@gmail.com")
                .celular(23468)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("14568")
                .nombre("Diana")
                .correo("diana@gmail.com")
                .celular(14587)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("85678")
                .nombre("Andres")
                .correo("andres@gmail.com")
                .celular(78588)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78456")
                .nombre("María")
                .correo("maria@gmail.com")
                .celular(36574)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45679")
                .nombre("David")
                .correo("david@gmail.com")
                .celular(87965)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("17845")
                .nombre("Valeria")
                .correo("valeria@gmail.com")
                .celular(45123)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("68945")
                .nombre("Jorge")
                .correo("jorge@gmail.com")
                .celular(25489)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45874")
                .nombre("Patricia")
                .correo("patricia@gmail.com")
                .celular(35678)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("98745")
                .nombre("Javier")
                .correo("javier@gmail.com")
                .celular(98745)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("36895")
                .nombre("Camila")
                .correo("camila@gmail.com")
                .celular(56231)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("47589")
                .nombre("Ricardo")
                .correo("ricardo@gmail.com")
                .celular(12347)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78546")
                .nombre("Nicolas")
                .correo("nicolas@gmail.com")
                .celular(78546)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("12345")
                .nombre("Elena")
                .correo("elena@gmail.com")
                .celular(45678)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("87564")
                .nombre("Hector")
                .correo("hector@gmail.com")
                .celular(65478)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("21547")
                .nombre("Daniela")
                .correo("daniela@gmail.com")
                .celular(21456)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("85471")
                .nombre("Pablo")
                .correo("pablo@gmail.com")
                .celular(78512)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("54789")
                .nombre("Adriana")
                .correo("adriana@gmail.com")
                .celular(45678)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("36987")
                .nombre("Esteban")
                .correo("esteban@gmail.com")
                .celular(36987)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45896")
                .nombre("Luisa")
                .correo("luisa@gmail.com")
                .celular(74585)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("17895")
                .nombre("Sebastian")
                .correo("sebastian@gmail.com")
                .celular(12368)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78452")
                .nombre("Gabriela")
                .correo("gabriela@gmail.com")
                .celular(87477)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78956")
                .nombre("Alejandro")
                .correo("alejandro@gmail.com")
                .celular(96325)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45876")
                .nombre("Fernanda")
                .correo("fernanda@gmail.com")
                .celular(12568)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("98756")
                .nombre("Oscar")
                .correo("oscar@gmail.com")
                .celular(96523)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("14785")
                .nombre("Natalia")
                .correo("natalia@gmail.com")
                .celular(78596)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("58963")
                .nombre("Felipe")
                .correo("felipe@gmail.com")
                .celular(56987)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("47895")
                .nombre("Monica")
                .correo("monica@gmail.com")
                .celular(87412)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78965")
                .nombre("Liliana")
                .correo("liliana@gmail.com")
                .celular(78965)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("45789")
                .nombre("Rodrigo")
                .correo("rodrigo@gmail.com")
                .celular(47895)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78596")
                .nombre("Lucia")
                .correo("lucia@gmail.com")
                .celular(78596)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("36589")
                .nombre("Eduardo")
                .correo("eduardo@gmail.com")
                .celular(32669)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("25896")
                .nombre("Tatiana")
                .correo("tatiana@gmail.com")
                .celular(25896)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("36985")
                .nombre("Victoria")
                .correo("victoria@gmail.com")
                .celular(36985)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("78912")
                .nombre("Mario")
                .correo("mario@gmail.com")
                .celular(78912)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("87452")
                .nombre("Alicia")
                .correo("alicia@gmail.com")
                .celular(87452)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("32587")
                .nombre("Martin")
                .correo("martin@gmail.com")
                .celular(32587)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("58063")
                .nombre("Claudia")
                .correo("claudia@gmail.com")
                .celular(58463)
                .build()
        );
        ClienteRepositorio.save(
        Cliente.builder()
                .cedula("32589")
                .nombre("Julian")
                .correo("julian@gmail.com")
                .celular(32589)
                .build()
        );

        // Medicamentos
        
        Medicamento acolan = MedicamentoRepositorio.findByNombre("ACOLAN").orElse(null);
        Medicamento altidox = MedicamentoRepositorio.findByNombre("ALTIDOX").orElse(null);
        Medicamento apravet = MedicamentoRepositorio.findByNombre("APRAVET").orElse(null);

        // Obtener veterinarios y mascotas desde el repositorio
        Veterinario veterinario1 = VeterinarioRepositorio.findById(1L).orElse(null);
        Veterinario veterinario2 = VeterinarioRepositorio.findById(2L).orElse(null);
        Veterinario veterinario3 = VeterinarioRepositorio.findById(3L).orElse(null);
        Veterinario veterinario4 = VeterinarioRepositorio.findById(4L).orElse(null);
        Veterinario veterinario5 = VeterinarioRepositorio.findById(5L).orElse(null);
        Veterinario veterinario6 = VeterinarioRepositorio.findById(6L).orElse(null);
        Veterinario veterinario7 = VeterinarioRepositorio.findById(7L).orElse(null);
        Veterinario veterinario8 = VeterinarioRepositorio.findById(8L).orElse(null);
        Veterinario veterinario9 = VeterinarioRepositorio.findById(9L).orElse(null);
        Veterinario veterinario10 = VeterinarioRepositorio.findById(10L).orElse(null);
        Veterinario veterinario11 = VeterinarioRepositorio.findById(11L).orElse(null);
        Veterinario veterinario12 = VeterinarioRepositorio.findById(12L).orElse(null);
        Veterinario veterinario13 = VeterinarioRepositorio.findById(13L).orElse(null);
        Veterinario veterinario14 = VeterinarioRepositorio.findById(14L).orElse(null);
        Veterinario veterinario15 = VeterinarioRepositorio.findById(15L).orElse(null);
        Veterinario veterinario16 = VeterinarioRepositorio.findById(16L).orElse(null);
        Veterinario veterinario17 = VeterinarioRepositorio.findById(17L).orElse(null);
        Veterinario veterinario18 = VeterinarioRepositorio.findById(18L).orElse(null);
        Veterinario veterinario19 = VeterinarioRepositorio.findById(19L).orElse(null);
        Veterinario veterinario20 = VeterinarioRepositorio.findById(20L).orElse(null);

        Mascota mascota1 = MascotaRepositorio.findById(1L).orElse(null);
        Mascota mascota2 = MascotaRepositorio.findById(2L).orElse(null);
        Mascota mascota3 = MascotaRepositorio.findById(3L).orElse(null);
        Mascota mascota4 = MascotaRepositorio.findById(4L).orElse(null);
        Mascota mascota5 = MascotaRepositorio.findById(5L).orElse(null);
        Mascota mascota6 = MascotaRepositorio.findById(6L).orElse(null);

        //// Tratamientos
        // Tratamiento 1

        Tratamieneto tratamiento1 = new Tratamieneto(LocalDate.of(2024, 9, 15), "Tratamiento post-operatorio");
        tratamiento1.setVeterinario(veterinario1);
        tratamiento1.setMascota(mascota1);
        tratamiento1.setMedicamentos(Arrays.asList(acolan, altidox));
        TratamientoRepositorio.save(tratamiento1);

        // Tratamiento 2
        Tratamieneto tratamiento2 = new Tratamieneto(LocalDate.of(2024, 9, 20),"Tratamiento para infección de la piel");
        tratamiento2.setVeterinario(veterinario2);
        tratamiento2.setMascota(mascota2);
        tratamiento2.setMedicamentos(Arrays.asList(altidox));
        TratamientoRepositorio.save(tratamiento2);

        // Tratamiento 3
        Tratamieneto tratamiento3 = new Tratamieneto(LocalDate.of(2024, 3, 10), "Tratamiento para fiebre leve");
        tratamiento3.setVeterinario(veterinario3);
        tratamiento3.setMascota(mascota3);
        tratamiento3.setMedicamentos(Arrays.asList(apravet));
        TratamientoRepositorio.save(tratamiento3);

        // Tratamiento 4
        Tratamieneto tratamiento4 = new Tratamieneto(LocalDate.of(2024, 4, 5),
                "Tratamiento para dolor de articulaciones");
        tratamiento4.setVeterinario(veterinario4);
        tratamiento4.setMascota(mascota4);
        tratamiento4.setMedicamentos(Arrays.asList(acolan));
        TratamientoRepositorio.save(tratamiento4);

        // Tratamiento 5
        Tratamieneto tratamiento5 = new Tratamieneto(LocalDate.of(2024, 5, 25), "Tratamiento post-cirugía dental");
        tratamiento5.setVeterinario(veterinario5);
        tratamiento5.setMascota(mascota5);
        tratamiento5.setMedicamentos(Arrays.asList(altidox, apravet));
        TratamientoRepositorio.save(tratamiento5);

        // Tratamiento 6
        Tratamieneto tratamiento6 = new Tratamieneto(LocalDate.of(2024, 6, 10),
                "Tratamiento para infección respiratoria");
        tratamiento6.setVeterinario(veterinario1);
        tratamiento6.setMascota(mascota6);
        tratamiento6.setMedicamentos(Arrays.asList(altidox));
        TratamientoRepositorio.save(tratamiento6);

        // Tratamiento 7
        Tratamieneto tratamiento7 = new Tratamieneto(LocalDate.of(2024, 7, 15), "Tratamiento para inflamación general");
        tratamiento7.setVeterinario(veterinario2);
        tratamiento7.setMascota(mascota1);
        tratamiento7.setMedicamentos(Arrays.asList(acolan));
        TratamientoRepositorio.save(tratamiento7);

        // Tratamiento 8
        Tratamieneto tratamiento8 = new Tratamieneto(LocalDate.of(2024, 8, 30), "Tratamiento para dolor muscular");
        tratamiento8.setVeterinario(veterinario3);
        tratamiento8.setMascota(mascota2);
        tratamiento8.setMedicamentos(Arrays.asList(apravet, acolan));
        TratamientoRepositorio.save(tratamiento8);

        // Tratamiento 9
        Tratamieneto tratamiento9 = new Tratamieneto(LocalDate.of(2024, 9, 5),
                "Tratamiento para infección post-quirúrgica");
        tratamiento9.setVeterinario(veterinario4);
        tratamiento9.setMascota(mascota3);
        tratamiento9.setMedicamentos(Arrays.asList(altidox));
        TratamientoRepositorio.save(tratamiento9);

        // Tratamiento 10
        Tratamieneto tratamiento10 = new Tratamieneto(LocalDate.of(2024, 10, 12), "Tratamiento para fiebre aguda");
        tratamiento10.setVeterinario(veterinario5);
        tratamiento10.setMascota(mascota4);
        tratamiento10.setMedicamentos(Arrays.asList(apravet));
        TratamientoRepositorio.save(tratamiento10);


        // Obtener todos los clientes
        List<Cliente> clientes = ClienteRepositorio.findAll();
        int totalClientes = clientes.size();
        int clienteIndex = 0;

        // Asignar las mascotas a los clientes de manera secuencial
        for (Long mascotaId = 1L; mascotaId <= 99L; mascotaId++) { // Suponiendo que hay 8 mascotas
            Mascota mascota = MascotaRepositorio.findById(mascotaId).get();
            Cliente cliente = clientes.get(clienteIndex);
            mascota.setCliente(cliente);
            MascotaRepositorio.save(mascota);

            // Avanzar al siguiente cliente, y volver al primero si ya se recorrieron todos
            clienteIndex = (clienteIndex + 1) % totalClientes;
        }
    }
}
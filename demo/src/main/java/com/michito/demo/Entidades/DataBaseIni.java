package com.michito.demo.Entidades;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LoginRepositorio.save(new Login("admin","admin","admin"));
        LoginRepositorio.save(new Login("admin2","admin","admin"));


        Login login2 = new Login("vet", "vet", "veterinario");
        login2 = LoginRepositorio.save(login2); // Guardar el login primero y obtener el objeto persistido

        // Crear el veterinario y asignar el login
        Veterinario veterinarioprim = new Veterinario("999", "Prueba", "veterinario@gmail.com", 1234L, "cirujano");
        veterinarioprim.setLogin(login2); // Asignar login2 al veterinario
        login2.setVeterinario(veterinarioprim); // Asignar veterinarioprim al login2

        // Guardar el veterinario
        VeterinarioRepositorio.save(veterinarioprim);

        // Luego guardar el login con la relación bidireccional
        LoginRepositorio.save(login2); 
     
        VeterinarioRepositorio.save(new Veterinario("123","Pedro","pedro@gmail.com",1234L,"cirujano"));
        VeterinarioRepositorio.save(new Veterinario("001","Juan","juan@example.com",1001L,"cirujano"));
        VeterinarioRepositorio.save(new Veterinario("002","Ana","ana@example.com",1002L,"cardiologo"));
        VeterinarioRepositorio.save(new Veterinario("003","Luis","luis@example.com",1003L,"dentista"));
        VeterinarioRepositorio.save(new Veterinario("004","María","maria@example.com",1004L,"dermatologo"));
        VeterinarioRepositorio.save(new Veterinario("005","Carlos","carlos@example.com",1005L,"oftalmologo"));
        VeterinarioRepositorio.save(new Veterinario("006","Laura","laura@example.com",1006L,"neurocirujano"));
        VeterinarioRepositorio.save(new Veterinario("007","Pedro","pedro@example.com",1007L,"gastroenterologo"));
        VeterinarioRepositorio.save(new Veterinario("008","Isabel","isabel@example.com",1008L,"traumatologo"));
        VeterinarioRepositorio.save(new Veterinario("009","David","david@example.com",1009L,"internista"));
        VeterinarioRepositorio.save(new Veterinario("010","Lucía","lucia@example.com",1010L,"oncologo"));
        VeterinarioRepositorio.save(new Veterinario("011","Javier","javier@example.com",1011L,"cirujano"));
        VeterinarioRepositorio.save(new Veterinario("012","Elena","elena@example.com",1012L,"cardiologo"));
        VeterinarioRepositorio.save(new Veterinario("013","Ricardo","ricardo@example.com",1013L,"dentista"));
        VeterinarioRepositorio.save(new Veterinario("014","Sofia","sofia@example.com",1014L,"dermatologo"));
        VeterinarioRepositorio.save(new Veterinario("015","Miguel","miguel@example.com",1015L,"oftalmologo"));
        VeterinarioRepositorio.save(new Veterinario("016","Raquel","raquel@example.com",1016L,"neurocirujano"));
        VeterinarioRepositorio.save(new Veterinario("017","Andrés","andres@example.com",1017L,"gastroenterologo"));
        VeterinarioRepositorio.save(new Veterinario("018","Carmen","carmen@example.com",1018L,"traumatologo"));
        VeterinarioRepositorio.save(new Veterinario("019","Fernando","fernando@example.com",1019L,"internista"));
        VeterinarioRepositorio.save(new Veterinario("020","Valeria","valeria@example.com",1020L,"oncologo"));

        MascotaRepositorio.save(new Mascota("Toby", 2, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Rex", 3, 17,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Luna", 1, 8,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Charlie", 3, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Buddy", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Lucy", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Maggie", 2, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Sophie", 4, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Max", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Bella", 3, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Milo", 1, 6,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Chloe", 2, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Rocky", 4, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Daisy", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Molly", 3, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Bailey", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Oscar", 1, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Coco", 4, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Buster", 2, 16,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Riley", 3, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Harley", 4, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Penny", 1, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Zoe", 2, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Ginger", 3, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Henry", 1, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Maddie", 2, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Ellie", 4, 16,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Rosie", 3, 17,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Jake", 1, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Lilly", 4, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Lola", 2, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Teddy", 3, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Roxy", 1, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Simba", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Bruno", 3, 9,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Pepper", 1, 8,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Rusty", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Murphy", 4, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Ruby", 3, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Bandit", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Lucky", 4, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Shadow", 2, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Tucker", 3, 10,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Dexter", 1, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Misty", 4, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Gizmo", 3, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Jasper", 2, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Duke", 1, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Scout", 4, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Mocha", 3, 12,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Cookie", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Max", 1, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Sadie", 4, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Bear", 3, 17,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Rocky", 2, 10,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Charlie", 1, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Daisy", 4, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Chloe", 3, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Bella", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Luna", 1, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Duke", 4, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Milo", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Sophie", 3, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Ziggy", 1, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Trixie", 4, 11,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Gizmo", 2, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Nala", 3, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Rex", 4, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Lulu", 1, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Hank", 2, 16,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Finn", 3, 17,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Marley", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Gracie", 1, 14,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Rocco", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Hazel", 3, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Diesel", 4, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Lily", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Tank", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Juno", 3, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Moose", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Willow", 1, 16,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Bowie", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Annie", 3, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Winston", 4, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Ginger", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Tyson", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Lulu", 3, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Jasper", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Piper", 1, 16,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Oreo", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Bubba", 3, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Millie", 4, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MascotaRepositorio.save(new Mascota("Dolly", 1, 9,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MascotaRepositorio.save(new Mascota("Chase", 2, 14,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));
        MascotaRepositorio.save(new Mascota("Bailey", 3, 15,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MascotaRepositorio.save(new Mascota("Harley", 4, 12,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MascotaRepositorio.save(new Mascota("Rusty", 1, 16,
                "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));
        MascotaRepositorio.save(new Mascota("Sasha", 2, 13,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MascotaRepositorio.save(new Mascota("Blue", 3, 11,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MascotaRepositorio.save(new Mascota("Ana", 58, 10,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));

        // Inserciones de clientes
        ClienteRepositorio.save(new Cliente("321456", "Jose", "jose@gmail.com", 12364));
        ClienteRepositorio.save(new Cliente("79885", "Pedro", "pedro@gmail.com", 79465));
        ClienteRepositorio.save(new Cliente("44545", "Paola", "paola@gmail.com", 88987));
        ClienteRepositorio.save(new Cliente("48785", "Jaime", "jaime@gmail.com", 741002));
        ClienteRepositorio.save(new Cliente("77874", "Cristian", "cristian@gmail.com", 889800));
        ClienteRepositorio.save(new Cliente("88852", "Lorena", "lorena@gmail.com", 874521));
        ClienteRepositorio.save(new Cliente("74565", "Ana", "ana@gmail.com", 12358));
        ClienteRepositorio.save(new Cliente("45786", "Carlos", "carlos@gmail.com", 56478));
        ClienteRepositorio.save(new Cliente("95875", "Sofia", "sofia@gmail.com", 98456));
        ClienteRepositorio.save(new Cliente("24758", "Miguel", "miguel@gmail.com", 78453));
        ClienteRepositorio.save(new Cliente("34858", "Laura", "laura@gmail.com", 23468));
        ClienteRepositorio.save(new Cliente("14568", "Diana", "diana@gmail.com", 14587));
        ClienteRepositorio.save(new Cliente("85678", "Andres", "andres@gmail.com", 78546));
        ClienteRepositorio.save(new Cliente("78456", "María", "maria@gmail.com", 36574));
        ClienteRepositorio.save(new Cliente("45679", "David", "david@gmail.com", 87965));
        ClienteRepositorio.save(new Cliente("17845", "Valeria", "valeria@gmail.com", 45123));
        ClienteRepositorio.save(new Cliente("68945", "Jorge", "jorge@gmail.com", 25489));
        ClienteRepositorio.save(new Cliente("45874", "Patricia", "patricia@gmail.com", 35678));
        ClienteRepositorio.save(new Cliente("98745", "Javier", "javier@gmail.com", 98745));
        ClienteRepositorio.save(new Cliente("36895", "Camila", "camila@gmail.com", 56231));
        ClienteRepositorio.save(new Cliente("47589", "Ricardo", "ricardo@gmail.com", 12347));
        ClienteRepositorio.save(new Cliente("78546", "Nicolas", "nicolas@gmail.com", 78546));
        ClienteRepositorio.save(new Cliente("12345", "Elena", "elena@gmail.com", 45678));
        ClienteRepositorio.save(new Cliente("87564", "Hector", "hector@gmail.com", 65478));
        ClienteRepositorio.save(new Cliente("21547", "Daniela", "daniela@gmail.com", 21456));
        ClienteRepositorio.save(new Cliente("85471", "Pablo", "pablo@gmail.com", 78512));
        ClienteRepositorio.save(new Cliente("54789", "Adriana", "adriana@gmail.com", 45678));
        ClienteRepositorio.save(new Cliente("36987", "Esteban", "esteban@gmail.com", 36987));
        ClienteRepositorio.save(new Cliente("45896", "Luisa", "luisa@gmail.com", 74585));
        ClienteRepositorio.save(new Cliente("17895", "Sebastian", "sebastian@gmail.com", 12368));
        ClienteRepositorio.save(new Cliente("78452", "Gabriela", "gabriela@gmail.com", 87452));
        ClienteRepositorio.save(new Cliente("78956", "Alejandro", "alejandro@gmail.com", 96325));
        ClienteRepositorio.save(new Cliente("45876", "Fernanda", "fernanda@gmail.com", 12568));
        ClienteRepositorio.save(new Cliente("98756", "Oscar", "oscar@gmail.com", 96523));
        ClienteRepositorio.save(new Cliente("14785", "Natalia", "natalia@gmail.com", 78596));
        ClienteRepositorio.save(new Cliente("58963", "Felipe", "felipe@gmail.com", 56987));
        ClienteRepositorio.save(new Cliente("47895", "Monica", "monica@gmail.com", 87412));
        ClienteRepositorio.save(new Cliente("78965", "Liliana", "liliana@gmail.com", 78965));
        ClienteRepositorio.save(new Cliente("45789", "Rodrigo", "rodrigo@gmail.com", 47895));
        ClienteRepositorio.save(new Cliente("78596", "Lucia", "lucia@gmail.com", 78596));
        ClienteRepositorio.save(new Cliente("36589", "Eduardo", "eduardo@gmail.com", 32589));
        ClienteRepositorio.save(new Cliente("25896", "Tatiana", "tatiana@gmail.com", 25896));
        ClienteRepositorio.save(new Cliente("36985", "Victoria", "victoria@gmail.com", 36985));
        ClienteRepositorio.save(new Cliente("78912", "Mario", "mario@gmail.com", 78912));
        ClienteRepositorio.save(new Cliente("87452", "Alicia", "alicia@gmail.com", 87452));
        ClienteRepositorio.save(new Cliente("32587", "Martin", "martin@gmail.com", 32587));
        ClienteRepositorio.save(new Cliente("58063", "Claudia", "claudia@gmail.com", 58463));
        ClienteRepositorio.save(new Cliente("32589", "Julian", "julian@gmail.com", 32589));

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
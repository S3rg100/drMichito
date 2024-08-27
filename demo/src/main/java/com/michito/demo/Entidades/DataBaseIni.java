package com.michito.demo.Entidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.LoginRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class DataBaseIni implements ApplicationRunner {
    @Autowired
    MascotasRepositorio MRepo;
    @Autowired
    ClientesRepositorio CRepo;
    @Autowired
    LoginRepositorio LRepo;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LRepo.save(new Login("admin","admin"));

        MRepo.save(new Mascota( "Toby", 2, 12, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s"));
        MRepo.save(new Mascota( "Rex", 3, 17, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Vu5Oh9Jgw85wSNikDQeMVPjHxJ18ibXOsA&s"));
        MRepo.save(new Mascota( "Luna", 1, 8, "https://images.ctfassets.net/denf86kkcx7r/4IPlg4Qazd4sFRuCUHIJ1T/f6c71da7eec727babcd554d843a528b8/gatocomuneuropeo-97?fm=webp&w=913"));  
        MRepo.save(new Mascota( "Charlie", 3, 16, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s"));
        MRepo.save(new Mascota( "Buddy", 4, 12, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA_u2l5BvubSYezZrgWYUxbykMc_AJc-Z6TA&s"));
        MRepo.save(new Mascota( "Lucy", 1, 9, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNSoXsa-tnlnvv48zeajd1RuuPysSViowWUQ&s"));
        MRepo.save(new Mascota( "Maggie", 2, 11, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfzafApUxyGfGk2ntFguN2_rkm4UyMhgzutw&s"));
        MRepo.save(new Mascota( "Sophie", 4, 13, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj-p9ds2KazJEwcFryuZ9RRRVTe7M1shdXWg&s"));

        CRepo.save(new Cliente( "321456", "Jose", "jose@gmail.com", 12364));
        CRepo.save(new Cliente( "79885", "Pedro", "Pedro@gmail.com", 79465));
        CRepo.save(new Cliente( "44545", "Paola", "Paola@gmail.com", 88987));
        CRepo.save(new Cliente( "48785", "Jaime", "Jaime@gmail.com", 741002));
        CRepo.save(new Cliente( "77874", "Cristian", "Cristian@gmail.com", 889800));
        CRepo.save(new Cliente( "88852", "Lorena", "Lorena@gmail.com", 874521));

        // Obtener todos los clientes
        List<Cliente> clientes = CRepo.findAll();
        int totalClientes = clientes.size();
        int clienteIndex = 0;

        // Asignar las mascotas a los clientes de manera secuencial
        for (Long mascotaId = 1L; mascotaId <= 8L; mascotaId++) { // Suponiendo que hay 8 mascotas
            Mascota mascota = MRepo.findById(mascotaId).get();
            Cliente cliente = clientes.get(clienteIndex);
            mascota.setCliente(cliente);
            MRepo.save(mascota);

            // Avanzar al siguiente cliente, y volver al primero si ya se recorrieron todos
            clienteIndex = (clienteIndex + 1) % totalClientes;
        }
    }
}
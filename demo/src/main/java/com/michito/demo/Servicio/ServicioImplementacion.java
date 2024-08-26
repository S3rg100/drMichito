package com.michito.demo.Servicio;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Login;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.LoginRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;

@Service

public class ServicioImplementacion implements Servicio{
    @Autowired
	MascotasRepositorio MRepo;
    @Autowired
    ClientesRepositorio CRepo;
    @Autowired
    LoginRepositorio LRepo;

    
    @Override
    public Mascota searchById(Long id) {
        return MRepo.findById(id).get();
    }

    @Override
    public Collection<Mascota> searchAll() {
        return MRepo.findAll();
    }

    @Override
    public void addMascota(Mascota newMascota) {
        MRepo.save(newMascota);
    }

    @Override
    public Cliente searchByIdCliente(Long id){
        return CRepo.findById(id).get();
    }

    @Override
    public Collection<Cliente> searchAllClientes() {
        return CRepo.findAll();
    }

    @Override
    public void deleteCliente(Long id) {
        CRepo.deleteById(id);
    }

    @Override
    public void updateCliente(Cliente cliente) {
        CRepo.save(cliente);
    }

    @Override
    public void addCliente(Cliente cliente) {
        CRepo.save(cliente);
    }

   
    @Override
    public void updateMascota(Mascota mascotaEditada) {
        MRepo.save(mascotaEditada);
    }

    @Override
    public void eliminarMascota(Long id) {
        MRepo.deleteById(id);
    }

    @Override
    public Cliente findByCedula(String cedula){
        return CRepo.findByCedula(cedula);
    }

    @Override
    public Collection<Login> searchAllLogins() {
        return LRepo.findAll();
    }

    @Override
    public Login SearchByUsuario(String usuario) {
        return LRepo.findByUsuario(usuario);
    }

   
}

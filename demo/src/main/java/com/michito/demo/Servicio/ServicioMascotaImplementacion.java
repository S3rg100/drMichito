package com.michito.demo.Servicio;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ServicioMascotaImplementacion implements ServicioMascota{
    @Autowired
	MascotasRepositorio mascotaRepositorio;
    @Autowired
	ClientesRepositorio clienteRepositorio;
    
    @Override
    public Mascota searchById(Long id) {
        return mascotaRepositorio.findById(id).get();
    }

    @Override
    public Collection<Mascota> searchAll() {
        return mascotaRepositorio.findAll();
    }

    @Override
    public void addMascota(Mascota newMascota) {
        mascotaRepositorio.save(newMascota);
    }




   
    @Override
    @Transactional
    public void updateMascota(Mascota mascotaEditada) {
        Cliente cliente = mascotaEditada.getCliente();
        if (cliente != null ) {
            clienteRepositorio.save(cliente);
        }
        mascotaRepositorio.save(mascotaEditada);
    }
    


    

    @Transactional
    @Override
    public void eliminarMascota(Long id) {
        Mascota mascota = mascotaRepositorio.findById(id).get();
        Cliente cliente = mascota.getCliente();
        
        if (cliente != null) {
            cliente.getMascotas().remove(mascota);
            
            clienteRepositorio.save(cliente);
        }
    
        mascotaRepositorio.deleteById(id);
    }
    

   
}

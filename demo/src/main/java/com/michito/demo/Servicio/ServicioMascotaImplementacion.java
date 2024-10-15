package com.michito.demo.Servicio;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Entidades.Tratamieneto;
import com.michito.demo.Repositorio.ClientesRepositorio;
import com.michito.demo.Repositorio.MascotasRepositorio;
import com.michito.demo.Repositorio.TratamientoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ServicioMascotaImplementacion implements ServicioMascota{
    @Autowired
	MascotasRepositorio mascotaRepositorio;
    @Autowired
	ClientesRepositorio clienteRepositorio;
    @Autowired
    TratamientoRepositorio tratamientoRepositorio;
    
    @Override
    public Mascota searchById(Long id) {
        return mascotaRepositorio.findById(id).get();
    }

    @Override
    public List<Mascota> searchAll() {
        return mascotaRepositorio.findAll();
    }

    @Override
    public void addMascota(Mascota newMascota) {
        mascotaRepositorio.save(newMascota);
    }

    @Override
    public List<Mascota> obtenerMascotasPorCliente(Long clienteId) {
        return mascotaRepositorio.findByClienteId(clienteId);
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
    
    @Override
    public List<Mascota> buscarPorNombre(String nombre) {
        return mascotaRepositorio.findByNombreContainingIgnoreCase(nombre);
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


        List<Tratamieneto> tratamientos = tratamientoRepositorio.findByMascotaId(mascota.getId());
        for (Tratamieneto tratamiento : tratamientos) {
            tratamiento.setMascota(null);
            tratamientoRepositorio.save(tratamiento); 
        }
        
        mascotaRepositorio.deleteById(id);
    }

    @Override
    public List<Mascota> searchByIdCliente(long id) {
        return mascotaRepositorio.findByClienteId(id);
    }
    

   
}

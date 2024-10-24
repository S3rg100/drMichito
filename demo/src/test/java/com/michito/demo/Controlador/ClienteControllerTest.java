package com.michito.demo.Controlador;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.michito.demo.Entidades.Cliente;
import com.michito.demo.Entidades.Mascota;
import com.michito.demo.Servicio.ServicioCliente;
import com.michito.demo.Servicio.ServicioMascota;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ClienteController.class) 
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ClienteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioCliente clienteService;

    @MockBean
    private ServicioMascota mascotaService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void ClienteController_crearCliente_Cliente() throws Exception {
        Cliente cliente = new Cliente("1231", "Fabito", "fabio@gmail.com", 31824094);

        // Simulamos el comportamiento del servicio
        when(clienteService.addCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        // Realizamos la petición con la ruta correcta
        ResultActions response = mockMvc.perform(
            post("/Clientes/agregar")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(cliente))
        );

        // Comprobamos que la respuesta es correcta
        response.andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(cliente)))
        .andExpect(jsonPath("$.nombre").value(cliente.getNombre()))
        .andExpect(jsonPath("$.cedula").value(cliente.getCedula()))
        .andExpect(jsonPath("$.correo").value(cliente.getCorreo()))
        .andExpect(jsonPath("$.celular").value(cliente.getCelular()));
    }

    @Test
    public void ClienteController_mostrarCliente_ClienteNoEncontrado() throws Exception {
        // Simulamos que el cliente no existe
        when(clienteService.searchByIdCliente(anyLong())).thenReturn(null);

        // Realizamos la petición
        ResultActions response = mockMvc.perform(
            get("/Clientes/info/{id}", 1L)
        );

        // Verificamos que la respuesta sea NOT_FOUND
        response.andExpect(status().isNotFound());
    }

    @Test
    public void ClienteController_buscarTodos_Clientes() throws Exception {
        // Simulamos el comportamiento del servicio
        List<Cliente> clientes = List.of(
            new Cliente("1231", "Fabito", "fabio@gmail.com", 31824094),
            new Cliente("1232", "Fabio", "fabio@gmail.com", 31824095)
        );
        when(clienteService.searchAllClientes()).thenReturn(clientes);

        // Realizamos la petición con la ruta correcta
        ResultActions response = mockMvc.perform(get("/Clientes/all"));

        // Comprobamos que la respuesta es correcta
        response.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(clientes)));
    }

    @Test
    public void ClienteController_buscarPorId_Cliente() throws Exception {
        // Simulamos el comportamiento del servicio
        Optional<Cliente> cliente = Optional.of(new Cliente("1231", "Fabito", "fabio@gmail.com", 31824094));
        when(clienteService.searchByIdCliente(Mockito.anyLong())).thenReturn(cliente.get());

        // Realizamos la petición con la ruta correcta
        ResultActions response = mockMvc.perform(get("/Clientes/info/{id}", 1L));

        // Comprobamos que la respuesta es correcta
        response.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(cliente.get())));
    }

    @Test
    public void ClienteController_actualizarCliente_Cliente() throws Exception {
        Cliente clienteActualizado = new Cliente("1231", "Fabito", "fabio@gmail.com", 31824094);

        // Simulamos el comportamiento del método void
        doNothing().when(clienteService).updateCliente(Mockito.any(Cliente.class));

        ResultActions response = mockMvc.perform(
            put("/Clientes/update/{id}", 1L)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(clienteActualizado))
        );

        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(clienteActualizado)));
    }


    @Test
    public void ClienteController_eliminarCliente_Cliente() throws Exception {
        // Simulamos el comportamiento del método void
        doNothing().when(clienteService).deleteCliente(Mockito.anyLong());

        ResultActions response = mockMvc.perform(delete("/Clientes/delete/{id}", 1L));

        response.andExpect(status().isNoContent());
    }


    @Test
    public void ClienteController_obtenerMascotasPorCliente() throws Exception {
        // Simulamos el comportamiento del servicio
        List<Mascota> mascotas = List.of(
            new Mascota("Laura", 19, 13, "https://chatgpt.com/c/67196b42-45a0-800c-bf2b-cbd07ac8a00b", true),
            new Mascota("Camila", 20, 13, "https://chatgpt.com/c/67196b42-800c-bf2b-cbd07ac8a00b", true)
        );
        when(mascotaService.obtenerMascotasPorCliente(1L)).thenReturn(mascotas);

        // Realizamos la petición
        ResultActions response = mockMvc.perform(get("/Clientes/cliente/{clienteId}", 1L));

        // Verificamos la respuesta
        response.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(mascotas)));
    }

    @Test
    public void ClienteController_obtenerClientePorMascotaId() throws Exception {
        // Simulamos el comportamiento del servicio
        Cliente cliente = new Cliente("1231", "Fabito", "fabio@gmail.com", 31824094);
        when(clienteService.searchByMascotaId(1L)).thenReturn(cliente);

        // Realizamos la petición
        ResultActions response = mockMvc.perform(get("/Clientes/por-mascota/{idMascota}", 1L));

        // Verificamos la respuesta
        response.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(cliente)));
    }
}

package com.manuel.workshop;

import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.repository.ClienteRepository;
import com.manuel.workshop.service.ClienteService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

public class ClienteServiceTest {

    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(this.clienteRepository);
    }

    @Test
    public void crearCliente(){
        Cliente cliente = new Cliente("Juan","Restrepo",123,"carrera10",21,"juanrestrepo@gmail.com");
        this.clienteRepository.save(cliente);

        assertTrue(cliente.getNombre().equals("Juan"));
        assertTrue(cliente.getApellido().equals("Restrepo"));
        assertTrue(cliente.getCedula().equals(123));
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteSinNombre(){
        Cliente cliente = new Cliente(null,"Restrepo",123,"carrera10",21,"juanrestrepo@gmail.com");
        this.clienteService.crear(cliente);
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteSinApellido(){
        Cliente cliente = new Cliente("Juan",null,123,"carrera10",21,"juanrestrepo@gmail.com");
        this.clienteService.crear(cliente);

    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinCedula(){
        Cliente cliente = new Cliente("Juan","Restrepo",null,"carrera10",21,"juanrestrepo@gmail.com");
        this.clienteService.crear(cliente);
    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinNombreSinApellidoSinCedula(){
        Cliente cliente = new Cliente(null,null,null,"carrera10",21,"juanrestrepo@gmail.com");
        this.clienteService.crear(cliente);
    }


}

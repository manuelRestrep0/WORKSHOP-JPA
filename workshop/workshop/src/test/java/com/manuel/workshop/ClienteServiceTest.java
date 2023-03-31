package com.manuel.workshop;

import com.manuel.workshop.dto.ClienteDTO;
import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.repository.ClienteRepository;
import com.manuel.workshop.service.ClienteService;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

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
        ClienteDTO clienteDTO = new ClienteDTO("Juan","Restrepo",123,"juanrestrepo@gmail.com","carrera10",21);

        this.clienteService.crear(clienteDTO);


        assertTrue(clienteDTO.getNombre().equals("Juan"));
        assertTrue(clienteDTO.getApellido().equals("Restrepo"));
        assertTrue(clienteDTO.getCedula().equals(123));
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteSinNombre(){
        ClienteDTO clienteDTO = new ClienteDTO(null,"Restrepo",123,"juanrestrepo@gmail.com","carrera10",21);
        this.clienteService.crear(clienteDTO);
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteSinApellido(){
        ClienteDTO clienteDTO = new ClienteDTO("Juan",null,123,"juanrestrepo@gmail.com","carrera10",21);

        this.clienteService.crear(clienteDTO);

    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinCedula(){
        ClienteDTO clienteDTO = new ClienteDTO("Juan","Restrepo",null,"juanrestrepo@gmail.com","carrera10",21);
        UUID contrasena = UUID.randomUUID();
        Cliente cliente = new Cliente(
                clienteDTO.getNombre(),
                clienteDTO.getApellido(),
                clienteDTO.getCedula(),
                clienteDTO.getDireccion(),
                clienteDTO.getEdad(),
                clienteDTO.getCorreo(),
                "si",
                contrasena.toString()
        );
        this.clienteService.crear(clienteDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinNombreSinApellidoSinCedula(){
        ClienteDTO clienteDTO = new ClienteDTO(null,null,null,"juanrestrepo@gmail.com","carrera10",21);
        UUID contrasena = UUID.randomUUID();
        Cliente cliente = new Cliente(
                clienteDTO.getNombre(),
                clienteDTO.getApellido(),
                clienteDTO.getCedula(),
                clienteDTO.getDireccion(),
                clienteDTO.getEdad(),
                clienteDTO.getCorreo(),
                "si",
                contrasena.toString()
        );
        this.clienteService.crear(clienteDTO);
    }


}

package com.manuel.workshop.service;

import com.manuel.workshop.dto.ClienteDTO;
import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public ClienteDTO crear(ClienteDTO clienteDTO){
        if(clienteDTO.getNombre()==null){
            throw new ApiRequestException("Se requiere el nombre");
        } else if(clienteDTO.getApellido()==null){
            throw new ApiRequestException("Se requiere el apellido");
        } else if(clienteDTO.getCedula()==null){
            throw new ApiRequestException("Se requiere una identificacion valida");
        }
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
        this.clienteRepository.save(cliente);
        return clienteDTO;
    }

    public void crearClientes(){
        UUID contrasena = UUID.randomUUID();
        this.clienteRepository.save(new Cliente("juan","restrepo",123,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
        this.clienteRepository.save(new Cliente("manuel","galarcio",334,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
        this.clienteRepository.save(new Cliente("sara","restrepo",888,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
        this.clienteRepository.save(new Cliente("sofia","franco",987,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
        this.clienteRepository.save(new Cliente("carlos","mancuso",456,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
        this.clienteRepository.save(new Cliente("sandra","rodriguez",992,"carrera10",21,"example@hotmail.com","si",contrasena.toString()));
    }
}

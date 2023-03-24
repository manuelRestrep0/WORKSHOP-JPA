package com.manuel.workshop.service;

import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente crear(Cliente cliente){
       /* if(cliente.getNombre()==null){
            throw new ClienteRequestException("No name", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        } else if(cliente.getApellido()==null){
            throw new RuntimeException("El cliente que ingreso no tiene apellido");
        } else{
            return this.clienteRepository.save(cliente);
        }

        */
        if(cliente.getNombre()==null){
            throw new ApiRequestException("Se requiere el nombre");
        } else if(cliente.getApellido()==null){
            throw new ApiRequestException("Se requiere el apellido");
        } else if(cliente.getCedula()==null){
            throw new ApiRequestException("Se requiere una identificacion valida");
        }
        return this.clienteRepository.save(cliente);
    }

    public void crearClientes(){
        this.clienteRepository.save(new Cliente("juan","restrepo",123,"carrera10",21,"example@hotmail.com"));
        this.clienteRepository.save(new Cliente("manuel","galarcio",334,"carrera10",21,"example@hotmail.com"));
        this.clienteRepository.save(new Cliente("sara","restrepo",888,"carrera10",21,"example@hotmail.com"));
        this.clienteRepository.save(new Cliente("sofia","franco",987,"carrera10",21,"example@hotmail.com"));
        this.clienteRepository.save(new Cliente("carlos","mancuso",456,"carrera10",21,"example@hotmail.com"));
        this.clienteRepository.save(new Cliente("sandra","rodriguez",992,"carrera10",21,"example@hotmail.com"));
    }
}

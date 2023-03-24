package com.manuel.workshop.service;

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

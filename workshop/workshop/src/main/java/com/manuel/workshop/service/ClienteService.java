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
}

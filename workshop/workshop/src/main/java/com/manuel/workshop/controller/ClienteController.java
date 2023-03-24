package com.manuel.workshop.controller;

import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public Cliente crearCliente(@RequestBody Cliente cliente){
        return this.clienteService.crear(cliente);
    }
    @PostMapping("/clientes")
    public String crearClientes(){
        this.clienteService.crearClientes();
        return "Clientes creados!";
    }

}

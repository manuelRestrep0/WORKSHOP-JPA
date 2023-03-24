package com.manuel.workshop.controller;

import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        Optional<Cliente> clienteValidation = Optional.ofNullable(this.clienteService.crear(cliente));
        if(clienteValidation != null){
            return new ResponseEntity("Cliente creado!", HttpStatus.CREATED);
        }
        return new ResponseEntity("El cliente no fue registrado",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/clientes")
    public String crearClientes(){
        this.clienteService.crearClientes();
        return "Clientes creados!";
    }

}

package com.manuel.workshop.controller;

import com.manuel.workshop.dto.HabitacionDTO;
import com.manuel.workshop.model.Cliente;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class HabitacionController {

    private HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/habitacion")
    public HabitacionDTO crearHabitacion(@RequestBody HabitacionDTO habitacion){
        return this.habitacionService.crearHabitacion(habitacion);
    }

    @PostMapping("/habitaciones")
    public ResponseEntity<Habitacion> crearHabitacion(){
        this.habitacionService.crearHabitaciones();
        return new ResponseEntity("habitaciones creadas",HttpStatus.CREATED);
    }
}

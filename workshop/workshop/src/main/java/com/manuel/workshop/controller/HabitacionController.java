package com.manuel.workshop.controller;

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
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion){
        Optional<Habitacion> validation = Optional.ofNullable(this.habitacionService.crearHabitacion(habitacion));
        if(validation != null){
            return new ResponseEntity("Habitacion creada!", HttpStatus.CREATED);
        }
        return new ResponseEntity("La habitacion no fue registrada",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/habitaciones")
    public ResponseEntity<Habitacion> crearHabitacion(){
        this.habitacionService.crearHabitaciones();
        return new ResponseEntity("habitaciones creadas",HttpStatus.CREATED);
    }
}

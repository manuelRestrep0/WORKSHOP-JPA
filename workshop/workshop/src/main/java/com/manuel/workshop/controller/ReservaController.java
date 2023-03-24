package com.manuel.workshop.controller;

import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.model.Reserva;
import com.manuel.workshop.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/disponibles/{fecha}")
    public List<Habitacion> disponiblesFecha(@PathVariable("fecha") String fecha){
        return this.reservaService.obtenerHabitacionesDisponiblesFecha(fecha);
    }


}

package com.manuel.workshop.controller;

import com.manuel.workshop.dto.HabitacionDTO;
import com.manuel.workshop.dto.ReservaDTO;
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
    public List<HabitacionDTO> disponiblesFecha(@PathVariable("fecha") String fecha){
        return this.reservaService.obtenerHabitacionesDisponiblesFecha(fecha);
    }
    @GetMapping("/disponibles/habitacion")
    public List<HabitacionDTO> disponiblesTipo(@RequestParam("tipo") String tipo, @RequestParam("fecha") String fecha){
        return this.reservaService.filtrarHabitacionesTipo(tipo,fecha);
    }

    @GetMapping("/reservas/{cedula}")
    public List<ReservaDTO> reservasCliente(@PathVariable("cedula") Integer cedula){
        return this.reservaService.obtenerReservasCliente(cedula);
    }

    @PostMapping("/reservar")
    public ReservaDTO reservar(@RequestParam("numero")Integer numeroHabitacion, @RequestParam("fecha") String fecha, @RequestParam("cedula") Integer cedula){
        return this.reservaService.crearReserva(numeroHabitacion,cedula,fecha);
    }


}

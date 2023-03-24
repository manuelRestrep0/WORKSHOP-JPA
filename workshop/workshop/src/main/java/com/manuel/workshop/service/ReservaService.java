package com.manuel.workshop.service;

import com.manuel.workshop.controller.ReservaController;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.model.Reserva;
import com.manuel.workshop.repository.ClienteRepository;
import com.manuel.workshop.repository.HabitacionRepository;
import com.manuel.workshop.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private HabitacionRepository habitacionRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository, HabitacionRepository habitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
    }

    public List<Habitacion> obtenerHabitacionesDisponiblesFecha(String fecha){
        List<Habitacion> disponibles = new ArrayList<>();
        LocalDate date = stringToDate(fecha);
        Habitacion auxHab;
        Reserva auxRe;
        Iterator<Habitacion> habitaciones = this.habitacionRepository.findAll().iterator();
        Iterator<Reserva> habitacionesReservadas = this.reservaRepository.findAll().iterator();
        while(habitaciones.hasNext()){
            auxHab = habitaciones.next();
            disponibles.add(auxHab);
            while(habitacionesReservadas.hasNext()){
                auxRe = habitacionesReservadas.next();
                if(auxRe.getHabitacion().getNumero() == auxHab.getNumero()){
                    //verificar la fecha
                    if(auxRe.getFechaReserva().equals(date)){
                        disponibles.remove(auxHab);
                    }
                }
            }
        }
        return disponibles;
    }

    public Reserva crearReserva(String fecha){
        LocalDate date = stringToDate(fecha);
        Reserva reserva = new Reserva();
        reserva.setFechaReserva(date);
        return this.reservaRepository.save(reserva);
    }

    public LocalDate stringToDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(fecha,formatter);
        return date;
    }
}

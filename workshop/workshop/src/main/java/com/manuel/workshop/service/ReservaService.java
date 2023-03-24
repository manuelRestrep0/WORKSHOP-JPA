package com.manuel.workshop.service;

import com.manuel.workshop.controller.ReservaController;
import com.manuel.workshop.model.Cliente;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
        LocalDate date = stringToDate(fecha);
        return validarDisponibilidadFecha(date);
    }
    public List<Habitacion> filtrarHabitacionesTipo(String tipo, String fecha){
        LocalDate date = stringToDate(fecha);
        List<Habitacion> disponibles = validarDisponibilidadFecha(date);
        disponibles = disponibles.stream()
                .filter(habitacion -> habitacion.getTipoHabitacion().equals(tipo))
                .collect(Collectors.toList());
        return disponibles;
    }

    public List<Habitacion> validarDisponibilidadFecha(LocalDate fecha){
        List<Habitacion> disponibles = new ArrayList<>();
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
                    if(auxRe.getFechaReserva().equals(fecha)){
                        disponibles.remove(auxHab);
                    }
                }
            }
        }
        return disponibles;
    }

    public Reserva crearReserva(Integer numHabitacion, Integer cedula, String fecha){
        Optional<Cliente> auxCliente = this.clienteRepository.findById(cedula);
        if(auxCliente.isPresent()){
            Optional<Habitacion> auxHab = this.habitacionRepository.findById(numHabitacion);
            if(auxHab.isPresent()){
                LocalDate auxFecha = stringToDate(fecha);
                List<Habitacion> disponibles = validarDisponibilidadFecha(auxFecha);
                if(disponibles.contains(auxHab.get())){
                    return this.reservaRepository.save(new Reserva(auxFecha,auxHab.get(),auxCliente.get(),auxHab.get().getPrecioBase()));
                }
            }
        }
        return null;
    }

    public List<Reserva> obtenerReservasCliente(Integer cedula){
        Iterator<Reserva> reservas = this.reservaRepository.findAll().iterator();
        Reserva auxReserva;
        List<Reserva> reservasCliente = new ArrayList<>();
        while(reservas.hasNext()){
            auxReserva = reservas.next();
            if(auxReserva.getCliente().getCedula().equals(cedula)){
                reservasCliente.add(auxReserva);
            }
        }
        return reservasCliente;
    }

    public LocalDate stringToDate(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(fecha,formatter);
        return date;
    }
}

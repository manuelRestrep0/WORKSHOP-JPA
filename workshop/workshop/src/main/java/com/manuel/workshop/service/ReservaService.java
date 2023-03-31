package com.manuel.workshop.service;

import com.manuel.workshop.controller.ReservaController;
import com.manuel.workshop.dto.HabitacionDTO;
import com.manuel.workshop.dto.ReservaDTO;
import com.manuel.workshop.exception.ApiRequestException;
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
import java.time.format.DateTimeParseException;
import java.util.*;
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

    public List<HabitacionDTO> obtenerHabitacionesDisponiblesFecha(String fecha){
        LocalDate date = stringToDate(fecha);
        fechaValida(date);
        return validarDisponibilidadFecha(date);
    }
    public List<HabitacionDTO> filtrarHabitacionesTipo(String tipo, String fecha){
        LocalDate date = stringToDate(fecha);
        fechaValida(date);
        List<HabitacionDTO> disponibles = validarDisponibilidadFecha(date);
        disponibles = disponibles.stream()
                .filter(habitacion -> habitacion.getTipoHabitacion().equals(tipo))
                .collect(Collectors.toList());
        return disponibles;
    }
    public ReservaDTO crearReserva(Integer numHabitacion, Integer cedula, String fecha){
        LocalDate auxFecha = stringToDate(fecha);
        fechaValida(auxFecha);
        Optional<Cliente> auxCliente = this.clienteRepository.findById(cedula);
        if(auxCliente.isPresent()){
            Optional<Habitacion> auxHab = this.habitacionRepository.findById(numHabitacion);
            if(auxHab.isPresent()){

                List<HabitacionDTO> disponibles = validarDisponibilidadFecha(auxFecha);
                if(disponibles.contains(auxHab.get())){
                    this.reservaRepository.save(new Reserva(auxFecha,auxHab.get(),auxCliente.get(),auxHab.get().getPrecioBase()));
                    return new ReservaDTO(auxFecha,auxHab.get(),auxCliente.get(),auxHab.get().getPrecioBase());
                } else{
                    throw new ApiRequestException("Esta habitacion no esta disponible");
                }
            } else{
                throw new ApiRequestException("Esta habitacion no se encuentra registrada");
            }
        } else{
            throw new ApiRequestException("Este cliente no esta registrado");
        }
    }
    public List<ReservaDTO> obtenerReservasCliente(Integer cedula){
        Optional<Cliente> auxCliente = this.clienteRepository.findById(cedula);
        if(!auxCliente.isPresent()){
            throw new ApiRequestException("Esta cedula no esta registrada");
        }
        List<Reserva> reservasCliente = this.reservaRepository.findAll();
        reservasCliente = reservasCliente.stream()
                .filter(reserva -> reserva.getCliente().getCedula().equals(cedula))
                .collect(Collectors.toList());
        List<ReservaDTO> reservasDTO = new ArrayList<>();
        reservasCliente.stream()
                .forEach(reserva -> reservasDTO.add(new ReservaDTO(reserva.getFechaReserva(),reserva.getHabitacion(),reserva.getCliente()
                ,reserva.getTotalPago())));
        return reservasDTO;
    }
    public List<HabitacionDTO> validarDisponibilidadFecha(LocalDate fecha){
        List<Habitacion> disponibles = this.habitacionRepository.findAll();
        List<Habitacion> habReservas = new ArrayList<>();

        List<Reserva> habitacionesReservadas = this.reservaRepository.findAll();
        habitacionesReservadas.stream()
                .filter(reserva -> reserva.getFechaReserva().equals(fecha))
                .forEach(reserva -> habReservas.add(reserva.getHabitacion()));
        disponibles = disponibles.stream()
                .filter(habitacion -> !habReservas.contains(habitacion))
                .collect(Collectors.toList());
        List<HabitacionDTO> retornoDisponibles = new ArrayList<>();
        disponibles.stream()
                .forEach(habitacion -> retornoDisponibles.add(new HabitacionDTO(habitacion.getNumero(),habitacion.getTipoHabitacion(),habitacion.getPrecioBase())));
        return retornoDisponibles;
    }
    public LocalDate stringToDate(String fecha){
        DateTimeFormatter formatter;
        try{
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        } catch (DateTimeParseException e){
            throw new ApiRequestException(e.getMessage());
        }
        LocalDate date = LocalDate.parse(fecha,formatter);
        return date;
    }
    public void fechaValida(LocalDate fecha){
        if(fecha.isBefore(LocalDate.now())){
            throw new ApiRequestException("La fecha es erronea");
        }
    }
}

package com.manuel.workshop;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.manuel.workshop.dto.HabitacionDTO;
import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.repository.ClienteRepository;
import com.manuel.workshop.repository.HabitacionRepository;
import com.manuel.workshop.repository.ReservaRepository;
import com.manuel.workshop.service.ReservaService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservaServiceTest {
    ReservaRepository reservaRepository;
    ClienteRepository clienteRepository;
    HabitacionRepository habitacionRepository;

    ReservaService reservaService;

    @Before
    public void setUp(){
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.clienteRepository = mock(ClienteRepository.class);
        this.reservaRepository = mock(ReservaRepository.class);

        this.reservaService = new ReservaService(this.reservaRepository,this.clienteRepository,this.habitacionRepository);
    }
    @Test(expected = DateTimeParseException.class)
    public void probarFechaMalFormato(){
        String fecha = "2019/12/01";
        LocalDate date = this.reservaService.stringToDate(fecha);
    }
    @Test
    public void probarFechaFormatoCorrecto(){
        String fecha = "21-07-2001";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateAux = LocalDate.parse(fecha,formatter);

        LocalDate date = this.reservaService.stringToDate(fecha);

        assertTrue(date.equals(dateAux));
    }
    @Test(expected = ApiRequestException.class)
    public void probarFechaValidaAnterior(){
        String fecha = "21-07-2001";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateAux = LocalDate.parse(fecha,formatter);

        this.reservaService.fechaValida(dateAux);
    }
    @Test
    public void probarFechaValidaDespues(){
        String fecha = "21-07-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateAux = LocalDate.parse(fecha,formatter);

        this.reservaService.fechaValida(dateAux);

        assertTrue(dateAux.isAfter(LocalDate.now()));
    }
    @Test
    public void obtenerHabitacionesDisponiblesFechaValida(){
        String fecha = "21-07-2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateAux = LocalDate.parse(fecha,formatter);
        List<Habitacion> disponibles = new ArrayList<>(Arrays.asList(
                new Habitacion(1,"estandar",100),
                new Habitacion(1,"estandar",100),
                new Habitacion(1,"estandar",100)
        ));
        when(habitacionRepository.findAll()).thenReturn(disponibles);

        List<HabitacionDTO> habitaciones = this.reservaService.obtenerHabitacionesDisponiblesFecha(fecha);
        assertTrue(!habitaciones.isEmpty());
    }

    @Test(expected = ApiRequestException.class)
    public void obtenerHabitacionesDisponiblesFechaNoValida(){
        String fecha = "21-07-2001";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateAux = LocalDate.parse(fecha,formatter);
        List<Habitacion> disponibles = new ArrayList<>(Arrays.asList(
                new Habitacion(1,"estandar",100),
                new Habitacion(1,"estandar",100),
                new Habitacion(1,"estandar",100)
        ));
        when(habitacionRepository.findAll()).thenReturn(disponibles);

        List<HabitacionDTO> habitaciones = this.reservaService.obtenerHabitacionesDisponiblesFecha(fecha);
    }
    @Test
    public void obtenerHabitacionesDisponiblesFiltradasPorTipoHabitacionExistente(){

    }

}

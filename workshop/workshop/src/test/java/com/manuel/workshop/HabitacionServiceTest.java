package com.manuel.workshop;

import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.repository.HabitacionRepository;
import com.manuel.workshop.service.HabitacionService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HabitacionServiceTest {
    HabitacionService habitacionService;
    HabitacionRepository habitacionRepository;

    @Before
    public void setUp(){
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.habitacionService = new HabitacionService(habitacionRepository);
    }

    @Test
    public void crearHabitacion(){
        Habitacion habitacion = new Habitacion(1,"premium",200);
        this.habitacionService.crearHabitacion(habitacion);

        assertTrue(habitacion.getTipoHabitacion().equals("premium"));
        assertTrue(habitacion.getNumero().equals(1));
        assertTrue(habitacion.getPrecioBase().equals(200));
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinNumero(){
        Habitacion habitacion = new Habitacion(null,"premium",200);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinTipo(){
        Habitacion habitacion = new Habitacion(1,null,200);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinPrecio(){
        Habitacion habitacion = new Habitacion(1,"premium",null);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinNada(){
        Habitacion habitacion = new Habitacion();
        this.habitacionService.crearHabitacion(habitacion);
    }

}

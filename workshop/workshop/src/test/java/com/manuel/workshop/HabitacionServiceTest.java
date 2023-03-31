package com.manuel.workshop;

import com.manuel.workshop.dto.HabitacionDTO;
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
        HabitacionDTO habitacion = new HabitacionDTO(1,"premium",200);
        this.habitacionService.crearHabitacion(habitacion);

        assertTrue(habitacion.getTipoHabitacion().equals("premium"));
        assertTrue(habitacion.getNumero().equals(1));
        assertTrue(habitacion.getPrecioBase().equals(200));
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinNumero(){
        HabitacionDTO habitacion = new HabitacionDTO(null,"premium",200);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinTipo(){
        HabitacionDTO habitacion = new HabitacionDTO(1,null,200);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinPrecio(){
        HabitacionDTO habitacion = new HabitacionDTO(1,"premium",null);
        this.habitacionService.crearHabitacion(habitacion);
    }
    @Test(expected = ApiRequestException.class)
    public void crearHabitacionSinNada(){
        HabitacionDTO habitacion = new HabitacionDTO();
        this.habitacionService.crearHabitacion(habitacion);
    }

}

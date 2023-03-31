package com.manuel.workshop.service;

import com.manuel.workshop.dto.HabitacionDTO;
import com.manuel.workshop.exception.ApiRequestException;
import com.manuel.workshop.model.Habitacion;
import com.manuel.workshop.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public HabitacionDTO crearHabitacion(HabitacionDTO habitacionDTO){
        if(habitacionDTO.getTipoHabitacion()==null){
            throw new ApiRequestException("Hace falta el tipo de habitacion (estandar/premium)");
        } else if(habitacionDTO.getPrecioBase()==null){
            throw new ApiRequestException("Hace falta el precio de la habitacion");
        } else if(habitacionDTO.getNumero()==null){
            throw new ApiRequestException("Hace falta el numero de la habitacion");
        }
        Habitacion habitacion = new Habitacion(habitacionDTO.getNumero(), habitacionDTO.getTipoHabitacion(), habitacionDTO.getPrecioBase());
        this.habitacionRepository.save(habitacion);
        return habitacionDTO;
    }
    public void crearHabitaciones(){
        this.habitacionRepository.save(new Habitacion(1,"estandar",100));
        this.habitacionRepository.save(new Habitacion(2,"estandar",100));
        this.habitacionRepository.save(new Habitacion(3,"estandar",100));
        this.habitacionRepository.save(new Habitacion(4,"estandar",100));
        this.habitacionRepository.save(new Habitacion(5,"estandar",100));
        this.habitacionRepository.save(new Habitacion(6,"premium",200));
        this.habitacionRepository.save(new Habitacion(7,"premium",200));
        this.habitacionRepository.save(new Habitacion(8,"premium",200));
        this.habitacionRepository.save(new Habitacion(9,"premium",200));
        this.habitacionRepository.save(new Habitacion(10,"premium",200));
    }
}

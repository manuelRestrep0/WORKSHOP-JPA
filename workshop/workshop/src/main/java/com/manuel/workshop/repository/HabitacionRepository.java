package com.manuel.workshop.repository;

import com.manuel.workshop.model.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends CrudRepository<Habitacion,Integer> {
}

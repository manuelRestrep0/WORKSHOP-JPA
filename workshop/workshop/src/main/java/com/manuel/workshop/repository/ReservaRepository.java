package com.manuel.workshop.repository;

import com.manuel.workshop.model.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva,Integer> {
}

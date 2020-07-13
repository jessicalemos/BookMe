package com.app.BookMe.repositories;

import com.app.BookMe.model.Processo;
import com.app.BookMe.model.Requisitante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequisitanteRep extends JpaRepository<Requisitante, Long> {
    Optional<Requisitante> findByProcessosContains(Processo p);
}
package com.app.BookMe.repositories;

import com.app.BookMe.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ResponsavelRep extends JpaRepository<Responsavel, Long> {
    @Query(" FROM Responsavel WHERE biblioteca_id = :bId")
    Responsavel findByBiblioteca(@Param("bId") long bibliotecaId);

    Optional<Responsavel> findByEmail(String email);

}

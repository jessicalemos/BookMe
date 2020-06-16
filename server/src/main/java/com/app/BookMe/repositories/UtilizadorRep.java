package com.app.BookMe.repositories;

import com.app.BookMe.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRep extends JpaRepository<Utilizador, Long> {
    Optional<Utilizador> findByEmail(String email);

}

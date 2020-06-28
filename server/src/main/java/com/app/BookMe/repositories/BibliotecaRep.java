package com.app.BookMe.repositories;

import com.app.BookMe.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BibliotecaRep extends JpaRepository<Biblioteca, Long> {
    Optional<Biblioteca> findByEmail(String email);

}

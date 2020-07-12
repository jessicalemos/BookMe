package com.app.BookMe.repositories;

import com.app.BookMe.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRep extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);

    @Query(" SELECT id,nome,email FROM Funcionario WHERE biblioteca_id = :bId")
    List<Object> findByBiblioteca(@Param("bId") long bibliotecaId);
}
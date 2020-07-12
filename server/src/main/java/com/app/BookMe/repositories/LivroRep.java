package com.app.BookMe.repositories;

import java.util.List;

import com.app.BookMe.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRep extends JpaRepository<Livro, Long> {

    @Query(" FROM Livro WHERE biblioteca_id = :bid")
    List<Livro> findByBiblioteca(@Param("bid") long bibliotecaID);
}

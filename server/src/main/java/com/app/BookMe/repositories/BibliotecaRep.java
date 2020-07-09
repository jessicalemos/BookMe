package com.app.BookMe.repositories;

import com.app.BookMe.model.Biblioteca;
import com.app.BookMe.model.Livro;
import com.app.BookMe.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BibliotecaRep extends JpaRepository<Biblioteca, Long> {
    Optional<Biblioteca> findByEmail(String email);
    Optional<Biblioteca> findByNome(String nome);
    @Query(value = "Select b.nome From biblioteca b INNER JOIN livro on b.id = livro.biblioteca_id where livro.isbn = :lIsbn", nativeQuery = true)
    List<String> findByLivroIsbn(@Param("lIsbn") String isbn);
    Optional<Biblioteca> findByProcessosContains(Processo p);
    Optional<Biblioteca> findBibliotecaByLivrosContains(Livro l);


}

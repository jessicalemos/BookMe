package com.app.BookMe.repositories;

import com.app.BookMe.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRep extends JpaRepository<Livro, Long> {
}

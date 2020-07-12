package com.app.BookMe.repositories;

import com.app.BookMe.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessoRep extends JpaRepository<Processo, Long> {
    @Query(" FROM Processo WHERE biblioteca_id = :bid AND estado = :e")
    List<Processo> findByEstadoBib(@Param("bid") long bibliotecaId, @Param("e") String estado);
}
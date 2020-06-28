package com.app.BookMe.repositories;

import com.app.BookMe.model.Processo;
import com.app.BookMe.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessoRep extends JpaRepository<Processo, Long> {
    @Query(" FROM Processo WHERE requisitante_id = :rId AND estado = :e ")
    List<Processo> findByEstadoReq(@Param("rId") long bibliotecaId, @Param("e") String estado);
}

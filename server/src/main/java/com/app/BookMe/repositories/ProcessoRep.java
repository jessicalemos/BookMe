package com.app.BookMe.repositories;

import com.app.BookMe.model.Livro;
import com.app.BookMe.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ProcessoRep extends JpaRepository<Processo, Long> {
    @Query(" FROM Processo WHERE requisitante_id = :rId AND estado = :e ")
    List<Processo> findByEstadoReq(@Param("rId") long bibliotecaId, @Param("e") String estado);
    Optional<Processo> findProcessoByLivroAndEstadoIsNotAndDataInicioLessThan(Livro l, String estado, Date now);
    List<Processo> findProcessoByLivroAndEstadoAndIDIsNot(Livro l, String estado, long id);
    List<Processo> findProcessoByLivroAndEstado(Livro l, String estado);
    List<Processo> findByDataInicioLessThanEqualAndEstado(Date now, String estado);
    List<Processo> findByDataFimGreaterThanEqualAndDataFimIsLessThanAndEstado(Date yesterday,Date now, String estado);
}

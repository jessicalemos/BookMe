package com.app.BookMe.repositories;

import com.app.BookMe.model.Administrador;
import com.app.BookMe.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRep extends JpaRepository<Notificacao, Long> {
}

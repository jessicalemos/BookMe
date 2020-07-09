package com.app.BookMe.repositories;

import com.app.BookMe.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface FuncionarioRep extends JpaRepository<Funcionario,Long> {
}

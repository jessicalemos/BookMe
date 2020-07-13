package com.app.BookMe.repositories;


import com.app.BookMe.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRep extends JpaRepository<Administrador, Long> {

}

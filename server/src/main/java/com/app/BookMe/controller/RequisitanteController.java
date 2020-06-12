package com.app.BookMe.controller;

import com.app.BookMe.model.BookMe;
import com.app.BookMe.model.Requisitante;
import com.app.BookMe.model.Utilizador;
import com.app.BookMe.repositories.UtilizadorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RequisitanteController {
    @Autowired
    private UtilizadorRep ur;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Requisitante req) {
        Optional<Utilizador> findByEmail = ur.findByEmail(req.getEmail());
        if(findByEmail.isPresent()){
            return "Username already exists" ;
        }

        Requisitante newR = new Requisitante(req.getEmail(), passwordEncoder.encode(req.getPassword()), req.getNome(),
                req.getTelemovel());

        BookMe.registarRequisitante(newR);
        return "Sucessfully registered!";
    }
}
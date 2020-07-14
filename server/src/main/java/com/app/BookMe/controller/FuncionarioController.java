package com.app.BookMe.controller;

import com.app.BookMe.model.*;

import org.springframework.web.bind.annotation.*;
import com.app.BookMe.repositories.FuncionarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRep fr;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/editarPerfil")
    public Funcionario editarPerfil(@RequestBody Funcionario f) {
        Optional<Funcionario> findByEmail = fr.findByEmail(f.getEmail());
        if(!findByEmail.isPresent()){
            return null ;
        }
        
        return BookMe.editarPerfil(f.getiD(), f.getNome(), passwordEncoder.encode(f.getPassword()), f.getEmail());
    }

    @GetMapping("/verPerfil/{funcionarioID}")
    public Optional<Funcionario> perfilFuncionario(@PathVariable long funcionarioID) {
        return fr.findById(funcionarioID);
    }

}

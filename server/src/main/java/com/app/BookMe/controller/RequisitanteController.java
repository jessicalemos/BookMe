package com.app.BookMe.controller;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.RequisitanteRep;
import com.app.BookMe.repositories.UtilizadorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RequisitanteController {
    @Autowired
    private UtilizadorRep ur;
    @Autowired
    private RequisitanteRep rr;
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

    @GetMapping("/livros")
    public List<Livro> consultaLivros(){
        return BookMe.consultaLivros();
    }

    @GetMapping("/livro/{livroID}")
    public Livro conslutarLivroId(@PathVariable long livroID) {
        return BookMe.consultarLivroId(livroID);
    }

    @GetMapping("/requisitante/{requisitanteID}")
    public Optional<Requisitante> conslutarRequisitanteId(@PathVariable long requisitanteID) {
        return rr.findById(requisitanteID);
    }

    @PostMapping("/editar")
    public Requisitante editarRequisitante(@RequestBody Requisitante r) {
        Optional<Utilizador> findByEmail = ur.findByEmail(r.getEmail());
        if(findByEmail.isPresent() && findByEmail.get().getiD()!= r.getiD()){
            return null ;
        }
        return BookMe.editarRequisitante(r.getiD(), r.getEmail(),passwordEncoder.encode(r.getPassword()),r.getNome(), r.getTelemovel());
    }

    @GetMapping("/reservados/{requisitanteID}")
    public List<Processo> getReservados(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"reservado");
    }

    @GetMapping("/devolvidos/{requisitanteID}")
    public List<Processo> getDevolvidos(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"devolvido");
    }

    @GetMapping("/requisitados/{requisitanteID}")
    public List<Processo> getRequisitados(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"requisitado");
    }

}
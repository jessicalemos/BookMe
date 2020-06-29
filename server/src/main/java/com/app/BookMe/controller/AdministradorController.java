package com.app.BookMe.controller;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.UtilizadorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AdministradorController {
    @Autowired
    private BibliotecaRep br;
    @Autowired
    private UtilizadorRep ur;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registarBiblioteca")
    public long registarBiblioteca(@RequestBody Biblioteca b) {
        Optional<Biblioteca> findByEmail = br.findByEmail(b.getEmail());
        if(findByEmail.isPresent()){
            return -1 ;
        }

        Biblioteca newB = new Biblioteca(b.getMorada(), b.getNome(), b.getTelemovel(),
                b.getEmail());

        return BookMe.registaBiblioteca(newB);
    }


    @PostMapping("/registarResponsavel")
    public String registarResponsavel(@RequestBody Responsavel r) {
        Optional<Utilizador> findByEmail = ur.findByEmail(r.getEmail());
        if(findByEmail.isPresent()){
            return "Username already exists" ;
        }

        Responsavel newR = new Responsavel(r.getEmail(), passwordEncoder.encode(r.getPassword()),r.getNome());

        BookMe.registaResponsavel(newR);
        return "Sucessfully registered!";
    }

    @PostMapping("/editarResponsavel")
    public Responsavel editarResponsavel(@RequestBody Responsavel r) {
            Optional<Utilizador> findByEmail = ur.findByEmail(r.getEmail());
            if(findByEmail.isPresent() && findByEmail.get().getiD()!= r.getiD()){
                return null ;
            }
            return BookMe.editarResponsavel(r.getiD(), r.getEmail(),passwordEncoder.encode(r.getPassword()),r.getNome());
    }

    @GetMapping("/bibliotecas")
    public List<Biblioteca> consultaBibliotecas(){
        System.out.println(BookMe.consultaBiliotecas());return BookMe.consultaBiliotecas();
    }

    @GetMapping("/biblioteca/{bibliotecaID}")
    public Biblioteca conslutarBiliotecaId(@PathVariable long bibliotecaID) {
        return BookMe.consultarBibliotecaId(bibliotecaID);
    }

    @GetMapping("/biblioteca/{bibliotecaID}/responsavel")
    public Responsavel conslutarBiliotecaResponsavel(@PathVariable long bibliotecaID) {
        System.out.println("BibliotecaID" + bibliotecaID);
        return BookMe.consultarBibliotecaResponsavel(bibliotecaID);

    }
}

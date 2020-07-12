package com.app.BookMe.controller;

import com.app.BookMe.model.*;

import org.springframework.web.bind.annotation.*;
import com.app.BookMe.repositories.FuncionarioRep;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.ResponsavelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {
    @Autowired
    private FuncionarioRep fr;
    @Autowired
    private BibliotecaRep br;
    @Autowired
    private ResponsavelRep rp;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registarFunc")
    public String registarFunc(@RequestBody Funcionario f, Authentication auth) {
        Optional<Funcionario> find = fr.findByEmail(f.getEmail());
        if(find.isPresent()){
            return "Username already exists";
        }

        Optional<Responsavel> findresp = rp.findByEmail(auth.getName());
        if(!findresp.isPresent()){
            return "Sem permissão";
        }
        Responsavel r = findresp.get();

        Optional<Biblioteca> findb = br.findById(r.getBiblioteca().getID());
        if(!findb.isPresent()){
            return "Biblioteca não existe";
        }
        Biblioteca bib = findb.get();
        
        Funcionario func = new Funcionario(f.getEmail(), passwordEncoder.encode(f.getPassword()), f.getNome(), bib);

        BookMe.registarFunc(func);
        return "Funcionário registado com sucesso";
    }


    @PostMapping("/removerFunc/{funcionarioID}")
    public String removerFunc(@PathVariable long funcionarioID, Authentication auth) {
        Optional<Funcionario> find = fr.findById(funcionarioID);
        if(!find.isPresent()){
            return "Funcionário não existe";
        }

        Optional<Responsavel> findresp = rp.findByEmail(auth.getName());
        Responsavel r = findresp.get();
        if(!find.isPresent()){
            return "Sem permissão";
        }

        Funcionario f = find.get();
        if(f.getBiblioteca().getID() != r.getBiblioteca().getID()){
            return "Sem permissão";
        }

        BookMe.removerFunc(funcionarioID);
        return "Funcionário removido com sucesso";
    }

    @GetMapping("/consultarFunc/{responsavelID}")
    public List<Object> consultarFunc(@PathVariable long responsavelID) {
        Optional<Responsavel> find = rp.findById(responsavelID);
        Responsavel r = find.get();

        return fr.findByBiblioteca(r.getBiblioteca().getID());
    }

    @PostMapping("/editarBiblioteca")
    public Biblioteca editarBiblioteca(@RequestBody Biblioteca b, Authentication auth) {
        Optional<Responsavel> find = rp.findByEmail(auth.getName());
        System.out.println(auth.getName());
        if(!find.isPresent()){
            return null;
        }
        
        return BookMe.editarBiblioteca(find.get().getBiblioteca().getID(), 
                            b.getMorada(), b.getNome(), b.getTelemovel(), b.getEmail());
    }
}

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

    @PostMapping("/registarFunc/{responsavelID}")
    public String registarFunc(@RequestBody Funcionario f, @PathVariable long responsavelID) {
        Optional<Funcionario> find = fr.findByEmail(f.getEmail());
        if(find.isPresent()){
            return "Username already exists";
        }

        Optional<Responsavel> findresp = rp.findById(responsavelID);
        if(!findresp.isPresent()){
            return "Sem permissão";
        }
        Responsavel r = findresp.get();

        Optional<Biblioteca> findb = br.findByResponsavel(r);
        if(!findb.isPresent()){
            return "Biblioteca não existe";
        }
        Biblioteca bib = findb.get();
        
        Funcionario func = new Funcionario(f.getEmail(), passwordEncoder.encode(f.getPassword()), f.getNome());
        func.setBiblioteca(bib);

        BookMe.registarFunc(func);
        return "Funcionário registado com sucesso";
    }


    @PostMapping("/removerFunc/{funcionarioID}")
    public String removerFunc(@PathVariable long funcionarioID) {
        Optional<Funcionario> find = fr.findById(funcionarioID);
        if(!find.isPresent()){
            return "Funcionário não existe";
        }

        BookMe.removerFunc(funcionarioID);
        return "Funcionário removido com sucesso";
    }

    @GetMapping("/consultarFunc/{responsavelID}")
    public List<Object> consultarFunc(@PathVariable long responsavelID) {
        Optional<Responsavel> find = rp.findById(responsavelID);
        Responsavel r = find.get();

        Optional<Biblioteca> findb = br.findByResponsavel(r);

        return fr.findByBiblioteca(findb.get().getID());
    }

    @PostMapping("/editarBiblioteca/{responsavelID}")
    public Biblioteca editarBiblioteca(@RequestBody Biblioteca b, @PathVariable long responsavelID) {
        Optional<Responsavel> find = rp.findById(responsavelID);
        Responsavel r = find.get();
        Optional<Biblioteca> findb = br.findByResponsavel(r);
        
        return BookMe.editarBiblioteca(findb.get().getID(), 
                            b.getMorada(), b.getNome(), b.getTelemovel(), b.getEmail());
    }
}

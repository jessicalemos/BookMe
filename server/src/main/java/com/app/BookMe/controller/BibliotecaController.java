package com.app.BookMe.controller;

import com.app.BookMe.model.*;

import org.springframework.web.bind.annotation.*;

import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.FuncionarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {
    @Autowired
    private FuncionarioRep fr;
    @Autowired
    private BibliotecaRep br;

    @PostMapping("/registaLivro")
    public Object registarLivro(@RequestBody Livro l, Authentication auth) {
        Optional<Funcionario> f = fr.findByEmail(auth.getName());
        Funcionario func = f.get();
        Optional<Biblioteca> b = br.findById(func.getBiblioteca().getID());
        Biblioteca biblioteca = b.get();
        //return func.getBiblioteca().getID();
 
        Livro livro = new Livro(l.getTitulo(), l.getCategoria(), l.getDescricao(), l.getAutor(),
                        l.getEdicao(), l.getEditor(), l.getAno(), l.getIsbn(),
                        l.getDisponibilidade(), l.getImagem());



        return BookMe.registaLivro(biblioteca, livro);
    }

    @PostMapping("/editaLivro")
    public Livro editarLivro(@RequestBody Livro l) {
        return BookMe.editarLivro(l.getID(), l.getTitulo(), l.getCategoria(), l.getDescricao(), l.getAutor(),
                                    l.getEdicao(), l.getEditor(), l.getAno(), l.getIsbn(), l.getDisponibilidade(), l.getImagem());
    }

    @GetMapping("/livros")
    public List<Livro> consultarLivrosBiblioteca(Authentication auth) {
        Optional<Funcionario> f = fr.findByEmail(auth.getName());
        Funcionario func = f.get();
        return BookMe.consultarLivrosBiblioteca(func.getBiblioteca().getID());
    }


    @PostMapping("/requisita/{processoID}")
    public String requisita(@PathVariable long processoID) {
        BookMe.requisita(processoID);
        return "Livro requisitado com sucesso";
    }

    @PostMapping("/devolve/{processoID}")
    public String devolve(@PathVariable long processoID) {
        BookMe.devolve(processoID);
        return "Livro devolvido com sucesso";
    }

    @GetMapping("/requisitados")
    public List<Processo> requisitados(Authentication auth) {
        Optional<Funcionario> f = fr.findByEmail(auth.getName());
        Funcionario func = f.get();
        return BookMe.getProcessosBib(func.getBiblioteca().getID(),"requisitado");
    }

    @GetMapping("/devolvidos")
    public List<Processo> devolvidos(Authentication auth) {
        Optional<Funcionario> f = fr.findByEmail(auth.getName());
        Funcionario func = f.get();
        return BookMe.getProcessosBib(func.getBiblioteca().getID(),"devolvido");
    }

    @GetMapping("/reservados")
    public List<Processo> reservados(Authentication auth) {
        Optional<Funcionario> f = fr.findByEmail(auth.getName());
        Funcionario func = f.get();
        return BookMe.getProcessosBib(func.getBiblioteca().getID(),"reservado");
    }
}

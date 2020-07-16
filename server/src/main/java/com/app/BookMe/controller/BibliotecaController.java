package com.app.BookMe.controller;

import com.app.BookMe.model.*;

import org.springframework.web.bind.annotation.*;

import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.FuncionarioRep;
import com.app.BookMe.repositories.RequisitanteRep;
import com.app.BookMe.repositories.ResponsavelRep;
import com.app.BookMe.controller.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {
    @Autowired
    private FuncionarioRep fr;
    @Autowired
    private BibliotecaRep br;
    @Autowired
    private ResponsavelRep rr;
    @Autowired
    private RequisitanteRep rqr;

    @PostMapping("/registaLivro")
    public Object registarLivro(@RequestBody Livro l, Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
 
        Livro livro = new Livro(l.getTitulo(), l.getCategoria(), l.getDescricao(), l.getAutor(),
                        l.getEdicao(), l.getEditor(), l.getAno(), l.getIsbn(),
                        l.getDisponibilidade(), l.getImagem());

        return BookMe.registaLivro(b, livro);
    }

    @PostMapping("/editaLivro")
    public Livro editarLivro(@RequestBody Livro l) {
        return BookMe.editarLivro(l.getID(), l.getTitulo(), l.getCategoria(), l.getDescricao(), l.getAutor(),
                                    l.getEdicao(), l.getEditor(), l.getAno(), l.getIsbn(), l.getDisponibilidade(), l.getImagem());
    }

    @GetMapping("/consultar")
    public Biblioteca consultarBiblioteca(Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        return b;
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
    public List<ProcReq> requisitados(@RequestParam("email") String email,Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        List<Processo> processos = BookMe.getProcessosBib(b,"requisitado");
        List<ProcReq> procreq = new ArrayList<>();
        for(Processo p : processos){
            Requisitante r = rqr.findByProcessosContains(p).get();
            if(email.equals("") || r.getEmail().equals(email)){
                ProcReq pr = new ProcReq(r,p);
                procreq.add(pr);
            }
        }
        return procreq;
    }

    @GetMapping("/devolvidos")
    public List<ProcReq> devolvidos(@RequestParam("email") String email,Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        List<Processo> processos = BookMe.getProcessosBib(b,"devolvido");
        List<ProcReq> procreq = new ArrayList<>();
        for(Processo p : processos){
            Requisitante r = rqr.findByProcessosContains(p).get();
            if(email.equals("") || r.getEmail().equals(email)){
                ProcReq pr = new ProcReq(r,p);
                procreq.add(pr);
            }
        }
        return procreq;
    }

    @GetMapping("/reservados")
    public List<ProcReq> reservados(@RequestParam("email") String email, Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        List<Processo> processos = BookMe.getProcessosBib(b,"reservado");
        List<ProcReq> procreq = new ArrayList<>();
        for(Processo p : processos){
            Requisitante r = rqr.findByProcessosContains(p).get();
            if(email.equals("") || r.getEmail().equals(email)){
                ProcReq pr = new ProcReq(r,p);
                procreq.add(pr);
            }
        }
        return procreq;
    }

    @GetMapping("/livros")
    public Set<Livro> consultarLivrosBiblioteca(Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        return BookMe.consultarLivrosBiblioteca(b);
    }

    @GetMapping("/livros/autores")
    public List<String> consultaAutores(Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        return BookMe.consultaBibliotecaAutores(b);
    }

    @GetMapping("/livros/editores")
    public List<String> consultaEditores(Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        return BookMe.consultaBibliotecaEditores(b);
    }

    @GetMapping("/livros/titulo/{titulo}")
    public List<Livro> consultaBibliotecaLivroTitulo(@PathVariable String titulo, Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
        return BookMe.consultaBibliotecaLivroTitulo(titulo, b);
    }

    @GetMapping("/livros/filtro")
    public List<Livro> consultaBibliotecaLivrosFiltro(@RequestBody Filtro f, Authentication auth) {
        Biblioteca b = getBiblioteca(auth);
        if(b == null) 
            return null;
            
        return BookMe.consultaBibliotecaLivrosFiltro(f.getAutores(), f.getEditores(), b);
    }



    private Biblioteca getBiblioteca(Authentication auth){
        String email = auth.getName();
        Optional<Responsavel> r = rr.findByEmail(email);
        Optional<Biblioteca> b;
        
        if(r.isPresent()){
            Responsavel resp = r.get();
            b = br.findByResponsavel(resp);
        }
        else{
            Optional<Funcionario> f = fr.findByEmail(email);
            if(f.isPresent()){
                Funcionario func = f.get();
                b = br.findBibliotecaByFuncionariosContains(func);
            }
            else
                return null;
        }
        if(!b.isPresent())
            return null;

        return b.get();
    }
}

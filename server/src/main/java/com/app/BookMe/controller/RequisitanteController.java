package com.app.BookMe.controller;

import com.app.BookMe.controller.model.Filtro;
import com.app.BookMe.controller.model.Historico;
import com.app.BookMe.model.*;
import com.app.BookMe.repositories.RequisitanteRep;
import com.app.BookMe.repositories.UtilizadorRep;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/processos/requisitante/{requisitanteID}")
    public List<Historico> conslutarProcessos(@PathVariable long requisitanteID) {
        List<Processo> processos = BookMe.conslutarProcessos(requisitanteID);
        List<Historico> historico = new ArrayList<>();
        for(Processo p : processos){
            Biblioteca b = BookMe.conslutarBibliotecaProcesso(p.getID());
            Historico h = new Historico(b,p);
            historico.add(h);
        }
        return historico;

    }

    @GetMapping("/reservados/{requisitanteID}")
    public List<Historico> getReservados(@PathVariable long requisitanteID) {
        List<Processo> processos = BookMe.getProcessosEstado(requisitanteID,"reservado");
        List<Historico> historico = new ArrayList<>();
        for(Processo p : processos){
            Biblioteca b = BookMe.conslutarBibliotecaProcesso(p.getID());
            Historico h = new Historico(b,p);
            historico.add(h);
        }
        return historico;
    }

    @GetMapping("/devolvidos/{requisitanteID}")
    public List<Historico> getDevolvidos(@PathVariable long requisitanteID) {
        List<Processo> processos = BookMe.getProcessosEstado(requisitanteID,"devolvido");
        List<Historico> historico = new ArrayList<>();
        for(Processo p : processos){
            Biblioteca b = BookMe.conslutarBibliotecaProcesso(p.getID());
            Historico h = new Historico(b,p);
            historico.add(h);
        }
        return historico;
    }

    @GetMapping("/atrasados/{requisitanteID}")
    public List<Historico> getAtrasados(@PathVariable long requisitanteID) {
        List<Processo> processos = BookMe.getProcessosEstado(requisitanteID,"atrasado");
        List<Historico> historico = new ArrayList<>();
        for(Processo p : processos){
            Biblioteca b = BookMe.conslutarBibliotecaProcesso(p.getID());
            Historico h = new Historico(b,p);
            historico.add(h);
        }
        return historico;
    }

    @GetMapping("/requisitados/{requisitanteID}")
    public List<Historico> getRequisitados(@PathVariable long requisitanteID) {
        List<Processo> processos = BookMe.getProcessosEstado(requisitanteID,"requisitado");
        List<Historico> historico = new ArrayList<>();
        for(Processo p : processos){
            Biblioteca b = BookMe.conslutarBibliotecaProcesso(p.getID());
            Historico h = new Historico(b,p);
            historico.add(h);
        }
        return historico;
    }

    @GetMapping("/processo/biblioteca/{processoID}")
    public Biblioteca conslutarBibliotecaProcesso(@PathVariable long processoID) {
        return BookMe.conslutarBibliotecaProcesso(processoID);
    }

    @GetMapping("/consulta/bibliotecas")
    public List<Biblioteca> getBibliotecas(){
        return BookMe.getBiliotecas();
    }

    @PostMapping("/reservar/{requisitanteID}")
    public String reservar(@PathVariable long requisitanteID,@RequestBody Processo p){
        return BookMe.reservaLivro(requisitanteID, p);
    }

    @GetMapping("/bibliotecas/livro/{isbn}")
    public List<String> consultarBibliotecasLivro(@PathVariable String isbn){
        return BookMe.consultarBibliotecasLivro(isbn);
    }

    @PostMapping("/disponibilidade/reservar")
    public Processo conslutarDisponibilidadeReserva(@RequestBody ObjectNode info) {
        String nome = info.get("biblioteca").asText();
        String livro = info.get("livro").asText();
        return BookMe.conslutarDisponibilidadeReserva(nome, livro);
    }

    @PostMapping("/renovar/reserva")
    public String renovar(@RequestBody ObjectNode info){
        long idReq = info.get("idRequisitante").asLong();
        long idProcesso = info.get("idProcesso").asLong();
        return BookMe.renovaReserva(idReq, idProcesso);
    }

    @PostMapping("/cancelar/reserva")
    public String cancelarReserva(@RequestBody ObjectNode info){
        long idReq = info.get("idRequisitante").asLong();
        long idProc = info.get("idProcesso").asLong();
        String biblioteca = info.get("biblioteca").asText();
        return BookMe.cancelarReserva(idReq, idProc, biblioteca);
    }

    @GetMapping("/notificacoes/requisitante/{requisitanteID}")
    public List<Notificacao> conslutarNotificacoes(@PathVariable long requisitanteID) {
        return BookMe.conslutarNotificacoes(requisitanteID);
    }

    @GetMapping("/Autores")
    public List<String> consultaAutores() {
        return BookMe.consultaAutores();
    }

    @GetMapping("/Editores")
    public List<String> consultaEditores() {
        return BookMe.consultaEditores();
    }

    @GetMapping("/livros/{titulo}")
    public List<Livro> consultaLivroTitulo(@PathVariable String titulo) {
        return BookMe.consultaLivroTitulo(titulo);
    }

    @PostMapping("/livros/filtro")
    public List<Livro> consultaLivrosFiltro(@RequestBody Filtro f) {
        return BookMe.consultaLivrosFiltro(f.getAutores(), f.getEditores(), f.getBibliotecas());
    }

    @GetMapping("/search/bibliotecas/{nome}")
    public List<Biblioteca> searchBibliotecaNome(@PathVariable String nome) {
        return BookMe.searchBibliotecaNome(nome);
    }

}
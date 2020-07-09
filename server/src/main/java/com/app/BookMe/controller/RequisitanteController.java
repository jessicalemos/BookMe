package com.app.BookMe.controller;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.RequisitanteRep;
import com.app.BookMe.repositories.UtilizadorRep;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public List<Processo> conslutarProcessos(@PathVariable long requisitanteID) {
        return BookMe.conslutarProcessos(requisitanteID);

    }

    @GetMapping("/reservados/{requisitanteID}")
    public List<Processo> getReservados(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"reservado");
    }

    @GetMapping("/devolvidos/{requisitanteID}")
    public List<Processo> getDevolvidos(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"devolvido");
    }

    @GetMapping("/atrasados/{requisitanteID}")
    public List<Processo> getAtrasados(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"atrasado");
    }

    @GetMapping("/requisitados/{requisitanteID}")
    public List<Processo> getRequisitados(@PathVariable long requisitanteID) {
        return BookMe.getProcessosEstado(requisitanteID,"requisitado");
    }

    @GetMapping("/processo/biblioteca/{processoID}")
    public Biblioteca conslutarBibliotecaProcesso(@PathVariable long processoID) {
        return BookMe.conslutarBibliotecaProcesso(processoID);
    }

    @PostMapping("/reservar/{requisitanteID}")
    public String reservar(@PathVariable long requisitanteID,@RequestBody Processo p){
        return BookMe.reservaLivro(requisitanteID, p);
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


}
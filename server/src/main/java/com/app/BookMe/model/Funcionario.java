package com.app.BookMe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Funcionario extends Utilizador{
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
    @JsonIgnore
    private Biblioteca biblioteca ;
    
    public Funcionario(){
        super();
    }

    public Funcionario(String email, String password, String nome, Biblioteca biblioteca) {
        super(email, password);
        this.nome = nome;
        this.biblioteca = biblioteca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}


package com.app.BookMe.model;

import javax.persistence.Entity;


@Entity
public class Funcionario extends Utilizador{
    private String nome;
    public Funcionario(){
        super();
    }
    public Funcionario(String email, String password, String nome) {
        super(email, password);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
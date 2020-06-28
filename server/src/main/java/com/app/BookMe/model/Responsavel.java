package com.app.BookMe.model;

import javax.persistence.Entity;

@Entity
public class Responsavel extends Funcionario {

	public Responsavel(){
		super();
	}

	public Responsavel(String email, String password, String nome) {
		super(email, password, nome);
	}

	public void registarFunc(String nome, String password, String email, String telemovel) {
		throw new UnsupportedOperationException();
	}
}
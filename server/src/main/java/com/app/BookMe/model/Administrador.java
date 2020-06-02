package com.app.BookMe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int iD;
	private String nome;
	private String password;

	public Biblioteca removeBiblioteca(String nome) {
		throw new UnsupportedOperationException();
	}

	public void registaBiblioteca(String nome, String email, String password, String telemovel, String morada) {
		throw new UnsupportedOperationException();
	}

	public List<Biblioteca> consultaBibliotecas() {
		throw new UnsupportedOperationException();
	}
}
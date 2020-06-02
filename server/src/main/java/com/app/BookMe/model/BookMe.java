package com.app.BookMe.model;

import java.util.HashSet;
import java.util.List;

public class BookMe {
	public Administrador admin;
	public HashSet<Biblioteca> bibliotecas = new HashSet<Biblioteca>();
	public HashSet<Requisitante> requisitantes = new HashSet<Requisitante>();

	public Biblioteca removeBiblioteca(String nome) {
		throw new UnsupportedOperationException();
	}

	public void registaBiblioteca(String nome, String email, String password, String telemovel, String morada) {
		throw new UnsupportedOperationException();
	}

	public void reservaLivro(Requisitante r, int idLivro, String biblioteca) {
		throw new UnsupportedOperationException();
	}

	public void cancelarReserva(Requisitante r, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void renovaReserva(Requisitante r, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public List<Processo> consultaProcessos(Utilizador u) {
		throw new UnsupportedOperationException();
	}

	public void editarPerfil(Utilizador u, String nome, String password, String telemovel) {
		throw new UnsupportedOperationException();
	}

	public void requisita(Biblioteca b, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void devolve(Biblioteca b, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public Livro removeLivro(Biblioteca b, int idlLivro) {
		throw new UnsupportedOperationException();
	}
}
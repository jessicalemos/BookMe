package com.app.BookMe.model;

import com.app.BookMe.beans.RequisitanteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookMe {
	private static RequisitanteBean rb;
	public Administrador admin;
	public HashSet<Biblioteca> bibliotecas = new HashSet<Biblioteca>();
	public HashSet<Requisitante> requisitantes = new HashSet<Requisitante>();

	@Autowired
	public BookMe(RequisitanteBean reqb){
		this.rb = reqb;
	}

    /**
     * Regista requisitante no sistema
     * @param r Requisitante
     */
    public static void registarRequisitante(Requisitante r) {
        rb.registarRequisitante(r);
    }

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
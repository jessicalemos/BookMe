package com.app.BookMe.model;

import com.app.BookMe.beans.RequisitanteBean;
import com.app.BookMe.beans.BibliotecaBean;
import com.app.BookMe.beans.FuncionarioBean;
import com.app.BookMe.beans.ResponsavelBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookMe {
	private static RequisitanteBean rb;
	private static BibliotecaBean bb;
	private static ResponsavelBean respb;
	private static FuncionarioBean fb;
	public Administrador admin;
	public HashSet<Biblioteca> bibliotecas = new HashSet<Biblioteca>();
	public HashSet<Requisitante> requisitantes = new HashSet<Requisitante>();

	@Autowired
	public BookMe(RequisitanteBean reqb, BibliotecaBean bibb, ResponsavelBean resb, FuncionarioBean fb){
		this.rb = reqb;
		this.bb = bibb;
		this.respb = resb;
		this.fb = fb;
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


	//------------Responsavel---------//
	public static void registarFunc(Funcionario f){
		respb.registarFunc(f);
	}

	public static void removerFunc(long id){
		respb.removerFunc(id);
	}

	public static Biblioteca editarBiblioteca(long id, String morada, String nome, String telemovel, String email){
		return respb.editarBiblioteca(id, morada, nome, telemovel, email);
	}

	//------------Funcionario--------//
	public static Funcionario editarPerfil(long id, String nome, String password, String email){
		return fb.editarPerfil(id, nome, password, email);
	}

	//------------Biblioteca---------//
	public static long registaLivro(Biblioteca b, Livro l){
		return bb.registaLivro(b,l);
	}

	public static Livro editarLivro(long id, String titulo, String categoria, String descricao, String autor, String edicao, String editor, int ano, String isbn, Boolean disponibilidade, String imagem){
		return bb.editarLivro(id, titulo, categoria, descricao, autor, edicao, editor, ano, isbn, disponibilidade, imagem);
	}

	public static List<Livro> consultarLivrosBiblioteca(long id){
		return bb.consultarLivrosBiblioteca(id);
	}

	public static Biblioteca consultarBiblioteca(long id){
		return bb.consultarBiblioteca(id);
	}

	public static void requisita(long idProcesso) {
		bb.requisita(idProcesso);
	}

	public static void devolve(long idProcesso) {
		bb.devolve(idProcesso);
	}

	public static List<Processo> getProcessosBib(long bibliotecaID, String estado) {
		return bb.getProcessosBib(bibliotecaID,estado);
	}


}
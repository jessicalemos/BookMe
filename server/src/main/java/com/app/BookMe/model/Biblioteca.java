package com.app.BookMe.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Biblioteca {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int ID;
	private String morada;
	private String nome;
	private String telemovel;
	private String email;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	private Set<Processo> processos;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	private Set<Funcionario> funcionarios ;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	private Set<Livro> livros;

	public void registarFunc(String nome, String password, String email, String telemovel) {
		throw new UnsupportedOperationException();
	}

	public void registaLivro(String titulo, String categoria, String descricao, String autor, String edicao, String editor, int ano, String isbn) {
		throw new UnsupportedOperationException();
	}

	public void requisita(int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void devolve(int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void removeLivro(int idLivro) {
		throw new UnsupportedOperationException();
	}
}
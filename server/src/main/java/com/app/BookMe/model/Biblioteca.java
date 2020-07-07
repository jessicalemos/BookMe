package com.app.BookMe.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Biblioteca {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long ID;
	private String morada;
	private String nome;
	private String telemovel;
	private String email;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	@JsonIgnore
	private Set<Processo> processos;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	@JsonIgnore
	private Set<Funcionario> funcionarios ;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "biblioteca_id")
	@JsonIgnore
	private Set<Livro> livros;

	public Biblioteca(){

	}

	public Biblioteca(String morada, String nome, String telemovel, String email) {
		this.morada = morada;
		this.nome = nome;
		this.telemovel = telemovel;
		this.email = email;
		this.processos = new HashSet<>();
		this.funcionarios = new HashSet<>();
		this.livros = new HashSet<>();
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(String telemovel) {
		this.telemovel = telemovel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<Processo> processos) {
		this.processos = processos;
	}

	public void addProcesso(Processo p){
		processos.add(p);
	}

	public void removeProcesso(Processo p){
		processos.remove(p);
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}

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
package com.app.BookMe.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long ID;
	private String titulo;
	private String categoria;
	@Column(columnDefinition="TEXT")
	private String descricao;
	private String autor;
	private String edicao;
	private String editor;
	private int ano;
	private String isbn;
	private Boolean disponibilidade;
	private String imagem;


    public Livro(){}

    public Livro(String titulo, String categoria, String descricao, String autor, String edicao, String editor, int ano, String isbn, Boolean disponibilidade, String imagem) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.autor = autor;
        this.edicao = edicao;
        this.editor = editor;
        this.ano = ano;
        this.isbn = isbn;
        this.disponibilidade = disponibilidade;
        this.imagem = imagem;
    }

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}



}
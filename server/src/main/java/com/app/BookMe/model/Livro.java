package com.app.BookMe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int iD;
	private String titulo;
	private String categoria;
	private String descricao;
	private String autor;
	private String edicao;
	private String editor;
	private int ano;
	private String isbn;
	private Boolean disponibilidade;
}
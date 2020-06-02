package com.app.BookMe.model;

import com.app.BookMe.model.Livro;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Processo {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int iD;
	private String estado;
	private Date dataInicio;
	private Date dataFim;
    @OneToOne
	public Livro livro;
}
package com.app.BookMe.model;

import com.app.BookMe.model.Livro;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Processo {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long ID;
	private String estado;
	private Date dataInicio;
	private Date dataFim;
    @OneToOne
	public Livro livro;


    public Processo(){}

    public Processo(String estado, Date dataInicio, Date dataFim, Livro livro) {
        this.estado = estado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.livro = livro;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
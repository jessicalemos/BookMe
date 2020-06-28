package com.app.BookMe.model;

import com.app.BookMe.model.Livro;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Processo {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private long ID;
	private String estado;
	private Date dataInicio;
	private Date dataFim;
    @OneToOne
	public Livro livro;

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
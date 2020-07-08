package com.app.BookMe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Requisitante extends Utilizador{
	private String nome;
	private String telemovel;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "requisitante_id")
    @JsonIgnore
	private List<Processo> processos ;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "requisitante_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Notificacao> notificacoes;



    public Requisitante() {
        super();
        this.processos = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
    }

    public Requisitante(String email, String password, String nome, String telemovel){
        super(email, password);
        this.nome = nome;
        this.telemovel = telemovel;
        this.processos = new ArrayList<>();
        this.notificacoes = new ArrayList<>();
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

    public List<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    public void addProcesso(Processo p){
        processos.add(p);
    }

    public void removeProcesso(Processo p){
        processos.remove(p);
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public void addNotificacao(Notificacao n){
        notificacoes.add(n);
    }

    public void reservaLivro(int idLivro, String biliboteca) {
		throw new UnsupportedOperationException();
	}

	public void cancelarReserva(int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void renovaReserva(int idProcesso) {
		throw new UnsupportedOperationException();
	}
}
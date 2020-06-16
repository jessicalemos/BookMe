package com.app.BookMe.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Requisitante extends Utilizador{
	private String nome;
	private String telemovel;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "requisitante_id")
	private Set<Processo> processos ;

    public Requisitante() {
        super();
        this.processos = new HashSet<>();
    }

    public Requisitante(String email, String password, String nome, String telemovel){
        super(email, password);
        this.nome = nome;
        this.telemovel = telemovel;
        this.processos = new HashSet<>();
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
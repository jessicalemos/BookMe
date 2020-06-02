package com.app.BookMe.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Requisitante extends Utilizador{
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "requisitante_id")
	private Set<Processo> processos ;

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
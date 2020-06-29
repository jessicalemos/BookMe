package com.app.BookMe.model;

import com.app.BookMe.beans.AdministradorBean;
import com.app.BookMe.beans.RequisitanteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookMe {
	private static RequisitanteBean rb;
	private static AdministradorBean ab;
	public Administrador admin;
	public HashSet<Biblioteca> bibliotecas = new HashSet<Biblioteca>();
	public HashSet<Requisitante> requisitantes = new HashSet<Requisitante>();

	@Autowired
	public BookMe(RequisitanteBean reqb, AdministradorBean adminb){
		this.rb = reqb;
		this.ab = adminb;
	}

    /**
     * Regista requisitante no sistema
     * @param r Requisitante
     */
    public static void registarRequisitante(Requisitante r) {
        rb.registarRequisitante(r);
    }

	public Biblioteca removeBiblioteca(String nome) {
		throw new UnsupportedOperationException();
	}

	public static long registaBiblioteca(Biblioteca b) {
		return ab.registaBiblioteca(b);
	}

	public static void registaResponsavel(Responsavel r) {ab.registaResponsavel(r);}

	public void reservaLivro(Requisitante r, int idLivro, String biblioteca) {
		throw new UnsupportedOperationException();
	}

	public void cancelarReserva(Requisitante r, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void renovaReserva(Requisitante r, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public static List<Processo> getProcessosEstado(long requisitanteID, String estado){return rb.getProcessosEstado(requisitanteID,estado);}

	public void editarPerfil(Utilizador u, String nome, String password, String telemovel) {
		throw new UnsupportedOperationException();
	}

	public static Requisitante editarRequisitante(long id, String email, String password, String nome, String telemovel) {return rb.editarRequisitante(id,email,password,nome, telemovel);}

	public static Responsavel editarResponsavel(long id, String email, String password, String nome) {return ab.editarResponsavel(id,email,password,nome);}

	public void requisita(Biblioteca b, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public void devolve(Biblioteca b, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public Livro removeLivro(Biblioteca b, int idlLivro) {
		throw new UnsupportedOperationException();
	}

	public static List<Livro> consultaLivros(){return rb.consultaLivros();}

	public static Livro consultarLivroId(long idLivro) {return rb.consultarLivroId(idLivro);}

    public static List<Biblioteca> consultaBiliotecas(){return ab.consultaBibliotecas();}

	public static Biblioteca consultarBibliotecaId(long idBiblioteca) {return ab.consultarBibliotecaId(idBiblioteca);}

	public static Responsavel consultarBibliotecaResponsavel(long idBiblioteca) {return ab.consultarBibliotecaResponsavel(idBiblioteca);}
}
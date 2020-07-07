package com.app.BookMe.model;

import com.app.BookMe.beans.AdministradorBean;
import com.app.BookMe.beans.BibliotecaBean;
import com.app.BookMe.beans.RequisitanteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookMe {
	private static RequisitanteBean rb;
	private static AdministradorBean ab;
	private static BibliotecaBean bb;
	public Administrador admin;
	public HashSet<Biblioteca> bibliotecas = new HashSet<Biblioteca>();
	public HashSet<Requisitante> requisitantes = new HashSet<Requisitante>();

	@Autowired
	public BookMe(RequisitanteBean reqb, AdministradorBean adminb, BibliotecaBean bibliob){
		this.rb = reqb;
		this.ab = adminb;
		this.bb = bibliob;
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

	public static String reservaLivro(long idReq, String biblioteca, String livro) {
		return rb.reservaLivro(idReq, biblioteca, livro);
	}

	public static String cancelarReserva(long idReq, long idProc, String biblioteca) {
		return rb.cancelarReserva(idReq,idProc,biblioteca);
	}

	public void renovaReserva(Requisitante r, int idProcesso) {
		throw new UnsupportedOperationException();
	}

	public static List<Processo> getProcessosEstado(long requisitanteID, String estado){return rb.getProcessosEstado(requisitanteID,estado);}

	public static List<Processo> conslutarProcessos(long requisitanteID) {return rb.conslutarProcessos(requisitanteID);}

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

    public static Biblioteca conslutarBibliotecaProcesso(long processoID) {
        return rb.conslutarBibliotecaProcesso(processoID);
    }

	public static List<Notificacao> conslutarNotificacoes(long requisitanteID){
		return rb.conslutarNotificacoes(requisitanteID);
	}

    @Scheduled(cron="0 0 0 * * *")
    public void vericaReservas(){
        bb.cancelaReservaAutomatica();
    }

    @Scheduled(cron="0 0 0 * * *")
    public void verificaAtrasos(){
        bb.verificaAtrasos();
    }

}
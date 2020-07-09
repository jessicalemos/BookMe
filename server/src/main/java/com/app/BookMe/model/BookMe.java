package com.app.BookMe.model;

import com.app.BookMe.beans.AdministradorBean;
import com.app.BookMe.beans.BibliotecaBean;
import com.app.BookMe.beans.RequisitanteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

	public static Biblioteca removeBiblioteca(long idBiblioteca) {
		return ab.removeBiblioteca(idBiblioteca);
	}

    /**
     * Regista biblioteca no sistema
     * @param b Biblioteca
     * @return id da biblioteca
     */
	public static long registaBiblioteca(Biblioteca b) {
		return ab.registaBiblioteca(b);
	}

    /**
     * Regista responsavel de biblioteca no sistema
     * @param r Responsavel
     * @param bibliotecaID Biblioteca
     */
	public static void registaResponsavel(Responsavel r, long bibliotecaID) {ab.registaResponsavel(r,bibliotecaID);}

    /**
     * Efetuar a reserva de um livro
     * @param idReq Requisitante id
     * @param p Processo
     * @return
     */
	public static String reservaLivro(long idReq, Processo p) {
		return rb.reservaLivro(idReq, p);
	}

    /**
     * Obter a disponibilidade para a reserva de um livro numa biblioteca.
     * @param nome Nome da biblioteca
     * @Param livro Isbn referente ao Livro
     * @return
     */
	public static Processo conslutarDisponibilidadeReserva(String nome, String livro){
        return rb.conslutarDisponibilidadeReserva(nome, livro);
    }

    /**
     * Cancelar uma reserva
     * @param idReq Requisitante id
     * @param idProc Processo id
     * @param biblioteca Biblioteca nome
     * @return
     */
	public static String cancelarReserva(long idReq, long idProc, String biblioteca) {
		return rb.cancelarReserva(idReq,idProc,biblioteca);
	}

    /**
     * Efetuar a renovação de um livro
     * @param idReq Requisitante id
     * @param idProcesso Processo id
     * @return
     */
	public static String renovaReserva(long idReq, long idProcesso) {
		return rb.renovaReserva(idReq, idProcesso);
	}

    /**
     * Obter os processos do requisitante por estado, isto é, devolvidos, reservados ou requisitados
     * @param requisitanteID Requisitante id
     * @param estado Estado do processo
     * @return Processos
     */
	public static List<Processo> getProcessosEstado(long requisitanteID, String estado){return rb.getProcessosEstado(requisitanteID,estado);}

    /**
     * Obter os processos do requisitante ordenado
     * @param requisitanteID Requisitante id
     * @return Processos ordenados
     */
	public static List<Processo> conslutarProcessos(long requisitanteID) {return rb.conslutarProcessos(requisitanteID);}

	public void editarPerfil(Utilizador u, String nome, String password, String telemovel) {
		throw new UnsupportedOperationException();
	}

    /**
     * Editar as informações do requisitante
     * @param id
     * @param email
     * @param password
     * @param nome
     * @param telemovel
     * @return Requisitante
     */
	public static Requisitante editarRequisitante(long id, String email, String password, String nome, String telemovel) {return rb.editarRequisitante(id,email,password,nome, telemovel);}

    /**
     * Edita as informações de um responsavel
     * @param id
     * @param email
     * @param password
     * @param nome
     * @return Responsavel
     */
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

    /**
     * Consulta os livros do sistema
     * @return Livros
     */
	public static List<Livro> consultaLivros(){return rb.consultaLivros();}

    /**
     * Obter um determinado livro
     * @param idLivro Livro id
     * @return Livro
     */
	public static Livro consultarLivroId(long idLivro) {return rb.consultarLivroId(idLivro);}

    /**
     * Consulta as bibliotecas do sistema
     * @return Biliotecas
     */
    public static List<Biblioteca> consultaBiliotecas(){return ab.consultaBibliotecas();}

    /**
     * Obter um determinada biblioteca
     * @param idBiblioteca Biblioteca id
     * @return Biblioteca
     */
	public static Biblioteca consultarBibliotecaId(long idBiblioteca) {return ab.consultarBibliotecaId(idBiblioteca);}

    /**
     * Obtém o responsavel de uma determinada biblioteca
     * @param idBiblioteca Biblioteca id
     * @return Responsavel
     */
	public static Responsavel consultarBibliotecaResponsavel(long idBiblioteca) {return ab.consultarBibliotecaResponsavel(idBiblioteca);}

    public static List<Biblioteca> consultaBibliotecaNome(String nome) {
        return ab.consultaBibliotecaNome(nome);
    }

    /**
     * Obter a biblioteca onde um processo foi efetuado
     * @param processoID Processo id
     * @return Biblioteca
     */
    public static Biblioteca conslutarBibliotecaProcesso(long processoID) {
        return rb.conslutarBibliotecaProcesso(processoID);
    }

    public static List<String> consultarBibliotecasLivro(String isbn){
        return rb.consultarBibliotecasLivro(isbn);
    }

    /**
     * Obter as notificações de um requisitante ordenadas
     * @param requisitanteID Requisitante id
     * @return Notificacoes ordenadas
     */
	public static List<Notificacao> conslutarNotificacoes(long requisitanteID){
		return rb.conslutarNotificacoes(requisitanteID);
	}

    public static List<String> consultaAutores() {
        return rb.consultaAutores();
    }

    public static List<String> consultaEditores() {
        return rb.consultaEditores();
    }

    public static List<Livro> consultaLivroTitulo(String titulo) {
        return rb.consultaLivroTitulo(titulo);
    }

    public static List<Livro> consultaLivrosFiltro(List<String> autores, List<String> editores, List<Long> bibliotecas) {
        return rb.consultaLivrosFiltro(autores, editores, bibliotecas);
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
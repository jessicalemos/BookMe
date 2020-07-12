package com.app.BookMe.beans;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.LivroRep;
import com.app.BookMe.repositories.ProcessoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BibliotecaBean {
    private LivroRep lr;
    private BibliotecaRep br;
    private ProcessoRep pr;

    @Autowired
    public BibliotecaBean(LivroRep lrep, BibliotecaRep brep, ProcessoRep prep) {
        this.lr = lrep;
        this.br = brep;
        this.pr = prep;
    }

    /**
     * Regista livro
     * @param l
     */
    public long registaLivro(Biblioteca b, Livro l) {
        l.setBiblioteca(b);
        lr.save(l);
        return l.getID();
    }

    /**
     * Edita informações de um livro
     * @param id
     * @param email
     * @param password
     * @param nome
     */
    public Livro editarLivro(long id, String titulo, String categoria, String descricao, String autor, String edicao, String editor, int ano, String isbn, Boolean disponibilidade, String imagem) {
        Optional<Livro> l = lr.findById(id);
        Livro livro = l.get();
        livro.setTitulo(titulo);
        livro.setCategoria(categoria);
        livro.setDescricao(descricao);
        livro.setAutor(autor);
        livro.setEdicao(edicao);
        livro.setEditor(editor);
        livro.setAno(ano);
        livro.setIsbn(isbn);
        livro.setDisponibilidade(disponibilidade);
        lr.save(livro);
        return livro;
    }

    /**
     * Consultar livros de uma biblioteca
     * @param id
     * @return
     */
    public List<Livro> consultarLivrosBiblioteca(long id){
        return lr.findByBiblioteca(id);
    }

    /**
     * Retorna a informação de uma biblioteca
     * @param id
     * @return
     */
    public Biblioteca consultarBiblioteca(long id) {
        Optional<Biblioteca> b = br.findById(id);
        Biblioteca biblioteca = b.get();
        return biblioteca;
    }

    /**
     * Muda o estado de um processo para requisitado
     * @param id
     */
    public void requisita(long id) {
        Optional<Processo> p = pr.findById(id);
        Processo processo = p.get();
        processo.setEstado("requisitado");
        pr.save(processo);
    }

    /**
     * Muda o estado de um processo para devolvido
     * @param id
     */
    public void devolve(long id) {
        Optional<Processo> p = pr.findById(id);
        Processo processo = p.get();
        processo.setEstado("devolvido");
        pr.save(processo);
    }

    /**
     * Retorna todos os processos da biblioteca com o estado especificado
     * @param bibliotecaID
     * @param estado
     * @return
     */
    public List<Processo> getProcessosBib(long bibliotecaID, String estado){
        System.out.println(bibliotecaID + estado);
        return pr.findByEstadoBib(bibliotecaID,estado);
    }
}

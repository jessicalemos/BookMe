package com.app.BookMe.beans;


import com.app.BookMe.model.*;
import com.app.BookMe.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BibliotecaBean {

    private LivroRep lr;
    private RequisitanteRep rp;
    private BibliotecaRep br;
    private ProcessoRep pr;
    private NotificacaoRep nr;

    @Autowired
    public BibliotecaBean(RequisitanteRep rr, BibliotecaRep bp, ProcessoRep pp, NotificacaoRep np, LivroRep lrep){
        this.rp = rr;
        this.br = bp;
        this.pr = pp;
        this.nr = np;
        this.lr = lrep;
    }

    /**
     * Regista livro
     * @param l
     */
    public long registaLivro(Biblioteca b, Livro l) {
        lr.save(l);
        b.addLivro(l);        
        br.save(b);
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
    public Set<Livro> consultarLivrosBiblioteca(Biblioteca b){
        return b.getLivros();
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
        
        Livro l = processo.getLivro();
        List<Processo> lproc = pr.findProcessoByLivroAndEstado(l, "reservado");

        if(lproc.size()==0){
            l.setDisponibilidade(true);
            lr.save(l);
        }
        else{
            notificaAgendamentos(lproc.get(0));
        }
        
    }

    /**
     * Retorna todos os processos da biblioteca com o estado especificado
     * @param bibliotecaID
     * @param estado
     * @return
     */
    public List<Processo> getProcessosBib(Biblioteca b, String estado){
        
        return pr.findByEstadoBib(b.getID(),estado);
    }

    public List<String> consultaBibliotecaAutores(Biblioteca b){
        return lr.findDistinctAutorInBiblioteca(b.getID());
    }

    public List<String> consultaBibliotecaEditores(Biblioteca b){
        return lr.findDistinctEditorInBiblioteca(b.getID());
    }

    public List<Livro> consultaBibliotecaLivroTitulo(String titulo, Biblioteca b){
        List<Livro> ll = lr.findByBiblioteca(b.getID());
        List<Long> r = new ArrayList<>();

        for(Livro l : ll)
            r.add(l.getID());
        
        return lr.findLivrosByIDInAndTituloContains(r,titulo);
    }

    public List<Livro> consultaBibliotecaLivrosFiltro(List<String> autores, List<String> editores, Biblioteca b){
        List<Livro> ll = lr.findByBiblioteca(b.getID());
        List<Long> ids = new ArrayList<>();
        for(Livro l: ll)
            ids.add(l.getID());

        if(!autores.isEmpty() && !editores.isEmpty())
            return lr.findLivrosByAutorInAndEditorInAndIDIn(autores, editores, ids);
        else if(!autores.isEmpty())
            return lr.findLivrosByAutorInAndIDIn(autores,ids);
        else if (!editores.isEmpty()) 
            return lr.findLivrosByEditorInAndIDIn(editores,ids);

        return lr.findLivrosByIDIn(ids);
    }



    public void notificaAgendamentos(Processo n){
        Requisitante r = rp.findByProcessosContains(n).get();
        long milis = System.currentTimeMillis();
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date data_inicio = new Date(milis);
        Date data_fim = new Date(System.currentTimeMillis() + (15 * DAY_IN_MS));
        n.setDataInicio(data_inicio);
        n.setDataFim(data_fim);
        String aviso = "O livro " + n.getLivro().getTitulo() +
                " que reservou, está disponível para levantamento, tem 4 dias para o fazer.";
        Notificacao notificacao = new Notificacao(aviso,data_inicio);
        r.addNotificacao(notificacao);
        nr.save(notificacao);
        rp.save(r);
        pr.save(n);
    }

    public void cancelaReservaAutomatica() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date limite = new Date(System.currentTimeMillis() - (4 * DAY_IN_MS));
        List<Processo> processos = pr.findByDataInicioLessThanEqualAndEstado(limite, "reservado");
        for (Processo p : processos) {
            Requisitante r = rp.findByProcessosContains(p).get();
            Biblioteca b = br.findByProcessosContains(p).get();
            r.removeProcesso(p);
            b.removeProcesso(p);
            String aviso = "A sua reserva relativa ao livro " + p.getLivro().getTitulo() + "realizada na biblioteca " + b.getNome() + "foi cancelada pelo não levantamento no prazo indicado.";
            Date envio = new Date(System.currentTimeMillis());
            Notificacao n = new Notificacao(aviso, envio);
            r.addNotificacao(n);
            nr.save(n);
            br.save(b);
            rp.save(r);
            List<Processo> agendado = pr.findProcessoByLivroAndEstadoAndIDIsNot(p.getLivro(), "reservado", p.getID());
            if (agendado.size() > 0) {
                notificaAgendamentos(agendado.get(0));
            }
            else p.getLivro().setDisponibilidade(true);
            pr.delete(p);
        }
    }

    public void verificaAtrasos() {
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Date now = new Date(System.currentTimeMillis());
        Date limite = new Date(System.currentTimeMillis() - (DAY_IN_MS));
        List<Processo> atrasados = pr.findByDataFimGreaterThanEqualAndDataFimIsLessThanAndEstado(limite, now,"requisitado");
        for(Processo p: atrasados) {
            String aviso = "A data de entrega da requisição do livro " + p.getLivro().getTitulo() + "já foi ultrapassada, por favor regulariza a situação";
            Date envio = new Date(System.currentTimeMillis());
            Notificacao n = new Notificacao(aviso, envio);
            Requisitante r = rp.findByProcessosContains(p).get();
            r.addNotificacao(n);
            p.setEstado("atrasado");
            nr.save(n);
            rp.save(r);
            pr.save(p);
        }
    }
}

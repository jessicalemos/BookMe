package com.app.BookMe.beans;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequisitanteBean {
    private RequisitanteRep rp;
    private LivroRep lr;
    private BibliotecaRep br;
    private ProcessoRep pr;
    private NotificacaoRep nr;

    @Autowired
    public RequisitanteBean(RequisitanteRep rr, LivroRep lp, BibliotecaRep bp, ProcessoRep pp, NotificacaoRep np) {
        this.rp = rr;
        this.lr = lp;
        this.br = bp;
        this.pr = pp;
        this.nr = np;
    }

    /**
     * Regista requisitante no sistema
     * @param r
     */
    public void registarRequisitante(Requisitante r) {
        rp.save(r);
    }

    /**
     * Consulta os livros do sistema
     * @return
     */
    public List<Livro> consultaLivros(){
        return lr.findDistinctIsbn();
    }

    /**
     * Obter um determinado livro
     * @param idLivro
     * @return
     */
    public Livro consultarLivroId(long idLivro) {
        Optional<Livro> lil = lr.findById(idLivro);
        Livro l = lil.get();
        return l;
    }

    /**
     * Editar as informações do requisitante
     * @param id
     * @param email
     * @param password
     * @param nome
     * @param telemovel
     * @return
     */
    public Requisitante editarRequisitante(long id, String email, String password, String nome, String telemovel) {
        Optional<Requisitante> req = rp.findById(id);
        Requisitante requisitante = req.get();
        requisitante.setEmail(email);
        requisitante.setPassword(password);
        requisitante.setNome(nome);
        requisitante.setTelemovel(telemovel);
        rp.save(requisitante);
        return requisitante;
    }

    /**
     * Obter os processos do requisitante por estado, isto é, devolvidos, reservados ou requisitados
     * @param requisitanteID
     * @param estado
     * @return
     */
    public List<Processo> getProcessosEstado(long requisitanteID, String estado){
        return pr.findByEstadoReq(requisitanteID,estado);
    }

    /**
     * Obter os processos do requisitante ordenado
     * @param requisitanteID
     * @return
     */
    public List<Processo> conslutarProcessos(long requisitanteID) {
        Requisitante r = rp.findById(requisitanteID).get();
        List <Processo> p = r.getProcessos();
        Collections.reverse(p);
        return p;
    }

    /**
     * Obter a biblioteca onde um processo foi efetuado
     * @param processoID
     * @return
     */
    public Biblioteca conslutarBibliotecaProcesso(long processoID) {
        Processo p = pr.findById(processoID).get();
        Biblioteca b = br.findByProcessosContains(p).get();
        return b;
    }

    public String reservaLivro(long idReq, String biblioteca, String livro) {
        Biblioteca biblio = br.findByNome(biblioteca).get();
        Requisitante req = rp.findById(idReq).get();
        if(pr.findByEstadoReq(req.getiD(),"atrasado").size()<1) {
            Livro l = lr.findByTituloAndBiblioteca(livro, biblio.getID()).get();
            long milis = System.currentTimeMillis();
            Date data_inicio = new Date(milis);
            long DAY_IN_MS = 1000 * 60 * 60 * 24;
            if (l.getDisponibilidade()) {
                Date data_fim = new Date(System.currentTimeMillis() + (15 * DAY_IN_MS));
                Processo p = new Processo("reservado", data_inicio, data_fim, l);
                req.addProcesso(p);
                biblio.addProcesso(p);
                l.setDisponibilidade(false);
                pr.save(p);
                lr.save(l);
                rp.save(req);
                br.save(biblio);
                return "Livro reservado";
            } else if(pr.findProcessoByLivroAndEstado(l,"reservado").size()<2) {
                Processo e = pr.findProcessoByLivroAndEstadoIsNotAndDataInicioLessThan(l, "devolvido", data_inicio).get();
                if(rp.findByProcessosContains(e).get().getiD() != req.getiD()) {
                    Processo p = new Processo("reservado", e.getDataFim(), null, l);
                    req.addProcesso(p);
                    biblio.addProcesso(p);
                    pr.save(p);
                    lr.save(l);
                    rp.save(req);
                    br.save(biblio);
                    return "Pre reserva realizada";
                }
                else return "Já tem uma reserva feita para este livro";
            }
            else return "Este livro não estará disponível em breve";
        }
        return "Para requistar tem de regularizar os livros em atraso";
    }

    public String renovaReserva(long idReq, long idProcesso) {
        Requisitante req = rp.findById(idReq).get();
        if(pr.findByEstadoReq(req.getiD(),"atrasado").size()<1) {
            Processo p = pr.findById(idProcesso).get();
            Livro l = p.getLivro();
            if(pr.findProcessoByLivroAndEstado(p.getLivro(), "reservado").isEmpty()){
                Date now = new Date(System.currentTimeMillis());
                p.setDataInicio(now);
                long DAY_IN_MS = 1000 * 60 * 60 * 24;
                Date data_fim = new Date(System.currentTimeMillis() + (15 * DAY_IN_MS));
                p.setDataFim(data_fim);
                pr.save(p);
                return "A renovação foi efetuada com sucesso";
            }
            return "Não é possível renovar este livro";
        }
        return "Para renovar tem de regularizar os livros em atraso";
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

    public String cancelarReserva(long idReq, long idProc, String biblioteca) {
        Processo p = pr.findById(idProc).get();
        Requisitante r = rp.findById(idReq).get();
        Biblioteca b = br.findByNome(biblioteca).get();
        r.removeProcesso(p);
        b.removeProcesso(p);
        rp.save(r);
        br.save(b);
        if(p.getDataFim()!=null) {
            List<Processo> agendado = pr.findProcessoByLivroAndEstadoAndIDIsNot(p.getLivro(), "reservado", p.getID());
            if (agendado.size() > 0) {
                notificaAgendamentos(agendado.get(0));
            }
            else p.getLivro().setDisponibilidade(true);
        }
        pr.delete(p);
        return "Reserva cancelada com sucesso";
    }

    public List<Notificacao> conslutarNotificacoes(long requisitanteID){
        Requisitante r = rp.findById(requisitanteID).get();
        List<Notificacao> n = r.getNotificacoes();
        Collections.reverse(n);
        return n;
    }
}

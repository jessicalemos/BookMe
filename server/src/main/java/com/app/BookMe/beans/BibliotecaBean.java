package com.app.BookMe.beans;

import com.app.BookMe.model.Biblioteca;
import com.app.BookMe.model.Notificacao;
import com.app.BookMe.model.Processo;
import com.app.BookMe.model.Requisitante;
import com.app.BookMe.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BibliotecaBean {
    private RequisitanteRep rp;
    private BibliotecaRep br;
    private ProcessoRep pr;
    private NotificacaoRep nr;

    @Autowired
    public BibliotecaBean(RequisitanteRep rr, BibliotecaRep bp, ProcessoRep pp, NotificacaoRep np){
        this.rp = rr;
        this.br = bp;
        this.pr = pp;
        this.nr = np;
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

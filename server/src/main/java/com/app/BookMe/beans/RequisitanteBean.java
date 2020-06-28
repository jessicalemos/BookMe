package com.app.BookMe.beans;

import com.app.BookMe.model.Livro;
import com.app.BookMe.model.Processo;
import com.app.BookMe.model.Requisitante;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.LivroRep;
import com.app.BookMe.repositories.ProcessoRep;
import com.app.BookMe.repositories.RequisitanteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequisitanteBean {
    private RequisitanteRep rp;
    private LivroRep lr;
    private BibliotecaRep br;
    private ProcessoRep pr;

    @Autowired
    public RequisitanteBean(RequisitanteRep rr, LivroRep lp, BibliotecaRep bp, ProcessoRep pp) {
        this.rp = rr;
        this.lr = lp;
        this.br = bp;
        this.pr = pp;
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
        return lr.findAll();
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

}

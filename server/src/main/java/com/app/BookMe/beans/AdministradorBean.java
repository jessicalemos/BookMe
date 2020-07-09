package com.app.BookMe.beans;

import com.app.BookMe.model.*;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.FuncionarioRep;
import com.app.BookMe.repositories.ResponsavelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AdministradorBean {
    private BibliotecaRep br;
    private ResponsavelRep rr;
    private FuncionarioRep fr;

    @Autowired
    public AdministradorBean(BibliotecaRep bibliorep, ResponsavelRep respr, FuncionarioRep funcr){
        this.br = bibliorep;
        this.rr = respr;
        this.fr = funcr;
    }

    /**
     * Regista biblioteca no sistema
     * @param b
     * @return
     */
    public long registaBiblioteca(Biblioteca b) {
        br.save(b);
        return b.getID();
    }

    /**
     * Regista responsavel de biblioteca no sistema
     * @param r
     * @param bibliotecaID
     */
    public void registaResponsavel(Responsavel r, long bibliotecaID) {
        Biblioteca b = br.findById(bibliotecaID).get();
        b.setResponsavel(r);
        rr.save(r);
        br.save(b);
    }


    /**
     * Coloca uma biblioteca inativa, removendo os seus funcionários.
     * @param bibliotecaID
     * @return
     */
    public Biblioteca removeBiblioteca(long bibliotecaID){
        Biblioteca b = br.findById(bibliotecaID).get();
        Responsavel r = b.getResponsavel();
        b.setResponsavel(null);
        rr.delete(r);
        Set<Funcionario> funcionarios = b.getFuncionarios();
        for(Funcionario f: funcionarios) {
            System.out.println(f.getiD());
            b.removeFuncionario(f);
            fr.delete(f);
        }
        b.setAtiva(false);
        br.save(b);
        System.out.println(b.getFuncionarios());
        return b;
    }

    /**
     * Edita as informações de um responsavel
     * @param id
     * @param email
     * @param password
     * @param nome
     * @return
     */
    public Responsavel editarResponsavel(long id, String email, String password, String nome) {
        Optional<Responsavel> resp = rr.findById(id);
        Responsavel responsavel = resp.get();
        responsavel.setEmail(email);
        responsavel.setPassword(password);
        responsavel.setNome(nome);
        rr.save(responsavel);
        return responsavel;
    }

    public List<Biblioteca> consultaBibliotecas(){
        return br.findBibliotecasByAtiva(true);
    }

    /**
     * Obtém uma determinada biblioteca
     * @param idBiblioteca
     * @return
     */
    public Biblioteca consultarBibliotecaId(long idBiblioteca) {
        Optional<Biblioteca> bib = br.findById(idBiblioteca);
        Biblioteca b = bib.get();
        return b;
    }

    /**
     * Obtém o responsavel de uma determinada biblioteca
     * @param idBiblioteca
     * @return
     */
    public Responsavel consultarBibliotecaResponsavel(long idBiblioteca){
        Biblioteca b = br.findById(idBiblioteca).get();
        return b.getResponsavel();
    }


    /**
     * Filtra as bilbiotecas pelo nome
     * @param nome
     * @return
     */
    public List<Biblioteca> consultaBibliotecaNome(String nome) {
        return br.findBibliotecasByNomeContainsAndAtivaTrue(nome);
    }
}

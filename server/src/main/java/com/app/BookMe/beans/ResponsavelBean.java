package com.app.BookMe.beans;

import java.util.Optional;

import com.app.BookMe.model.Biblioteca;
import com.app.BookMe.model.Funcionario;
import com.app.BookMe.repositories.BibliotecaRep;
import com.app.BookMe.repositories.FuncionarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ResponsavelBean {
    private FuncionarioRep fr;
    private BibliotecaRep br;

    @Autowired
    public ResponsavelBean(FuncionarioRep fr, BibliotecaRep br) {
        this.fr = fr;
        this.br = br;
    }

    /**
     * Regista funcionário.
     * @param f
     */
    public void registarFunc(Funcionario f) {
        fr.save(f);
    }

    /**
     * Eliminar funcionário.
     * @param id
     */
    public void removerFunc(long id){
        fr.deleteById(id);
    }


    /**
     * Edita os campos da biblioteca.
     * @param id
     * @param morada
     * @param nome
     * @param telemovel
     * @param email
     * @return
     */
    public Biblioteca editarBiblioteca(long id, String morada, String nome, String telemovel, String email){
        Optional<Biblioteca> b = br.findById(id);
        Biblioteca biblioteca = b.get();
        biblioteca.setMorada(morada);
        biblioteca.setNome(nome);
        biblioteca.setTelemovel(telemovel);
        biblioteca.setEmail(email);
        br.save(biblioteca);
        return biblioteca;
    }
}
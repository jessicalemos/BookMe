package com.app.BookMe.beans;

import java.util.Optional;

import com.app.BookMe.model.Funcionario;
import com.app.BookMe.repositories.FuncionarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FuncionarioBean {
    private FuncionarioRep fr;

    @Autowired
    public FuncionarioBean(FuncionarioRep fr) {
        this.fr = fr;
    }
    
    /**
     * Edita os campos do funcionário.
     * @param id
     * @param nome
     * @param password
     * @param email
     * @return
     */
    public Funcionario editarPerfil(long id, String nome, String password, String email){
        Optional<Funcionario> f = fr.findById(id);
        Funcionario funcionario = f.get();
        funcionario.setNome(nome);
        funcionario.setPassword(password);
        funcionario.setEmail(email);
        fr.save(funcionario);
        return funcionario;
    }
}
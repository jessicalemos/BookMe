package com.app.BookMe.beans;

import com.app.BookMe.model.Requisitante;
import com.app.BookMe.repositories.RequisitanteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequisitanteBean {
    private RequisitanteRep rp;

    @Autowired
    public RequisitanteBean(RequisitanteRep rr) {
        this.rp = rr;
    }

    /**
     * Regista requisitante no sistema
     * @param r
     */
    public void registarRequisitante(Requisitante r) {
        rp.save(r);
    }
}

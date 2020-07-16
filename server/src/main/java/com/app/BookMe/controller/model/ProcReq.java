package com.app.BookMe.controller.model;

import com.app.BookMe.model.Processo;
import com.app.BookMe.model.Requisitante;

public class ProcReq {
    private Requisitante requisitante;
    private Processo processo;

    public ProcReq() {
    }

    public ProcReq(Requisitante r, Processo p) {
        this.requisitante = r;
        this.processo = p;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante r) {
        this.requisitante = r;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo p) {
        this.processo = p;
    }
}
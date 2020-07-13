package com.app.BookMe.controller.model;

import com.app.BookMe.model.Biblioteca;
import com.app.BookMe.model.Processo;

public class Historico {
    private Biblioteca biblioteca;
    private Processo processo;

    public Historico() {
    }

    public Historico(Biblioteca b, Processo p) {
        this.biblioteca = b;
        this.processo = p;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca b) {
        this.biblioteca = b;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo p) {
        this.processo = p;
    }
}

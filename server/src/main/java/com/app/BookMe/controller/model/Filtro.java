package com.app.BookMe.controller.model;

import java.util.List;

public class Filtro {
    List<String> autores;
    List<String> editores;
    List<Long> bibliotecas;

    public Filtro() {
    }

    public Filtro(List<String> autores, List<String> editores, List<Long> bibliotecas) {
        this.autores = autores;
        this.editores = editores;
        this.bibliotecas = bibliotecas;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getEditores() {
        return editores;
    }

    public void setEditores(List<String> editores) {
        this.editores = editores;
    }

    public List<Long> getBibliotecas() {
        return bibliotecas;
    }

    public void setBibliotecas(List<Long> bibliotecas) {
        this.bibliotecas = bibliotecas;
    }
}

package com.app.BookMe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String aviso;
    private Date data;

    public Notificacao(){}
    public Notificacao(String aviso, Date data) {
        this.aviso = aviso;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

package br.edu.ladoss.simpifladoss.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rennan on 13/11/17.
 */

public class Evento implements Serializable{

    public static final String BUNDLE = "eventoBundle";

    private int id;
    private String nome;
    private boolean isDeleted;
    private Date inicio;
    private Date fim;

    public Evento(int id, String nome, boolean isDeleted, Date inicio, Date fim) {
        this.id = id;
        this.nome = nome;
        this.isDeleted = isDeleted;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFim() {
        return fim;
    }
}

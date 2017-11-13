package br.edu.ladoss.simpifladoss.models;

import java.util.Date;

/**
 * Created by Rennan on 13/11/17.
 */

public class Cronograma {

    private int id;
    private String nome;
    private Evento evento;
    private boolean isDeleted;
    private Date dataRealizacao;
    private String horaInicio;
    private String horaFim;

    public Cronograma(int id, String nome, Evento evento, boolean isDeleted, Date dataRealizacao, String horaInicio, String horaFim) {
        this.id = id;
        this.nome = nome;
        this.evento = evento;
        this.isDeleted = isDeleted;
        this.dataRealizacao = dataRealizacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Evento getEvento() {
        return evento;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }
}

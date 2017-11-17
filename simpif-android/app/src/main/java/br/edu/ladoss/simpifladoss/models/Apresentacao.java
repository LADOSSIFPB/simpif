package br.edu.ladoss.simpifladoss.models;

import java.util.List;

public class Apresentacao {

    public Boolean isDeleted;
    public Integer id;
    public String horaInicio;
    public Trilha trilha;
    public Cronograma cronograma;
    public String titulo;
    public List<Autor> autores = null;
    public String horaFim;
    public List<Participante> participantes = null;
    public Sala sala;

    public Apresentacao(Boolean isDeleted, Integer id, String horaInicio, Trilha trilha, Cronograma cronograma, String titulo, List<Autor> autores, String horaFim, List<Participante> participantes, Sala sala) {
        this.isDeleted = isDeleted;
        this.id = id;
        this.horaInicio = horaInicio;
        this.trilha = trilha;
        this.cronograma = cronograma;
        this.titulo = titulo;
        this.autores = autores;
        this.horaFim = horaFim;
        this.participantes = participantes;
        this.sala = sala;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public Trilha getTrilha() {
        return trilha;
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public Sala getSala() {
        return sala;
    }
}
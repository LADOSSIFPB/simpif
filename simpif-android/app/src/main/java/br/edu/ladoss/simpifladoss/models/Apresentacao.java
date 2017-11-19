package br.edu.ladoss.simpifladoss.models;

import android.support.annotation.NonNull;

import java.util.List;

public class Apresentacao implements Comparable<Apresentacao>{
    private String horaInicio;
    private Trilha trilha;
    private Cronograma cronograma;
    private String titulo;
    private List<Autor> autores = null;
    private String horaFim;
    private Sala sala;

    public Apresentacao(String horaInicio, Trilha trilha, Cronograma cronograma, String titulo, List<Autor> autores, String horaFim, Sala sala) {
        this.horaInicio = horaInicio;
        this.trilha = trilha;
        this.cronograma = cronograma;
        this.titulo = titulo;
        this.autores = autores;
        this.horaFim = horaFim;
        this.sala = sala;
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

    public Sala getSala() {
        return sala;
    }

    @Override
    public int compareTo(@NonNull Apresentacao o) {
        int result = this.cronograma.compareTo(o.getCronograma());
        if (result == 0) {
            result = horaInicio.compareTo(o.getHoraInicio());
            if(result == 0) {
                result = titulo.compareTo(o.getTitulo());
            }
        }
        return result;
    }
}
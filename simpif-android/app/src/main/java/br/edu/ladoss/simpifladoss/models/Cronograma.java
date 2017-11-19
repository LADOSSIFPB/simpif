package br.edu.ladoss.simpifladoss.models;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Rennan on 13/11/17.
 */

public class Cronograma implements Comparable<Cronograma>{
    private String nome;

    public Cronograma(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cronograma that = (Cronograma) o;

        return nome != null ? nome.equals(that.nome) : that.nome == null;
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int compareTo(@NonNull Cronograma o) {
        return nome.compareTo(o.getNome());
    }
}

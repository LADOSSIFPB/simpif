package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 13/11/17.
 */

public class Trilha {

    private int id;
    private String nome;
    private boolean isDeleted;

    public Trilha(int id, String nome, boolean isDeleted) {
        this.id = id;
        this.nome = nome;
        this.isDeleted = isDeleted;
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
}

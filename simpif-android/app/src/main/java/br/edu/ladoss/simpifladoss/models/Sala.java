package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 17/11/17.
 */

public class Sala {

    public String nome;
    public String descricao;

    public Sala(String nome, String descricao) {
        super();
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
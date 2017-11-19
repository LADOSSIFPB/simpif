package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 17/11/17.
 */

public class Sala {

    public String nome;
    public String descricao;
    public String cor;

    public Sala(String nome, String descricao, String cor) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCor() {
        return cor;
    }

}
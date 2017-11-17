package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 17/11/17.
 */

public class Sala {

    public String nome;
    public Boolean isDeleted;
    public Integer id;
    public String descricao;
    public String cor;
    public Integer capacidade;

    public Sala(String nome, Boolean isDeleted, Integer id, String descricao, String cor, Integer capacidade) {
        super();
        this.nome = nome;
        this.isDeleted = isDeleted;
        this.id = id;
        this.descricao = descricao;
        this.cor = cor;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCor() {
        return cor;
    }

    public Integer getCapacidade() {
        return capacidade;
    }
}
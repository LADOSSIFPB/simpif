package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 17/11/17.
 */

public class Autor {
    private String nome;
    private Integer id;
    private Boolean isDeleted;
    private String email;

    public Autor(String nome, Integer id, Boolean isDeleted, String email) {
        super();
        this.nome = nome;
        this.id = id;
        this.isDeleted = isDeleted;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public String getEmail() {
        return email;
    }
}

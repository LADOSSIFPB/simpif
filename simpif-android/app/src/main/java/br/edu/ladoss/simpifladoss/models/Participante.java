package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 17/11/17.
 */

public class Participante {

    public String nome;
    public int osParticipanteId;
    public Integer id;
    public Boolean isDeleted;
    public String cpf;
    public String email;


    public Participante(String nome, int osParticipanteId, Integer id, Boolean isDeleted, String cpf, String email) {
        super();
        this.nome = nome;
        this.osParticipanteId = osParticipanteId;
        this.id = id;
        this.isDeleted = isDeleted;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public int getOsParticipanteId() {
        return osParticipanteId;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}